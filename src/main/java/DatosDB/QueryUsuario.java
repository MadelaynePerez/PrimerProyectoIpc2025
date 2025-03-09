/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Coneccion;
import Modelos.Rol;
import Modelos.Usuario;
import Utils.EncriptarContrasenia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class QueryUsuario {

    public boolean crear(Usuario usuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        EncriptarContrasenia encriptado = new EncriptarContrasenia();

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Usuario (nombre_usuario, password, id_rol) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, usuario.getNombreUsuario());
            pstmt.setString(2, encriptado.contraseniaEncriptada(usuario.getPassword()));
            pstmt.setInt(3, usuario.getRol().getIdRol()); // Guardar ID del rol

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (Exception e) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Usuario encontrarPorNombre(String nombreUsuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT id_usuario, nombre_usuario, id_rol, password FROM Usuario WHERE nombre_usuario = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nombreUsuario);
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                int idUsuario = resultado.getInt("id_usuario");
                String nombre = resultado.getString("nombre_usuario");
                int idRol = resultado.getInt("id_rol");
                String password = resultado.getString("password");

                return new Usuario(idUsuario, nombre, password, new Rol(idRol, ""));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Usuario login(String username, String password) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT r.id_rol,u.id_usuario FROM usuario u "
                    + "JOIN rol r ON u.id_rol = r.id_rol "
                    + "WHERE u.nombre_usuario = ? AND u.password = ? AND u.activo = true";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id_usuario"), username, "", new Rol(rs.getInt("id_rol"), ""));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean inactivarUsuario(int id_usuario) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE usuario SET activo = false WHERE id_usuario = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id_usuario);

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT id_usuario, nombre_usuario, activo, usuario.id_rol, rol.nombre_rol FROM usuario\n"
                    + "inner join rol on usuario.id_rol = rol.id_rol";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setRol(new Rol(rs.getInt("id_rol"), rs.getString("rol.nombre_rol")));
                usuarios.add(usuario);
            }

            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return new ArrayList<>();
    }

    public boolean CambiarRol(int idUsuario, int nuevoIdRol) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE usuario SET  id_rol = ? WHERE id_usuario = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, nuevoIdRol);
            pstmt.setInt(2, idUsuario);

            int filasActualizadas = pstmt.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT u.id_usuario, u.nombre_usuario, u.id_rol, u.activo, r.nombre_rol "
                    + "FROM usuario u "
                    + "JOIN rol r ON u.id_rol = r.id_rol "
                    + "WHERE u.id_usuario = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setRol(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
                usuario.setActivo(rs.getBoolean("activo"));
            }

            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

}
