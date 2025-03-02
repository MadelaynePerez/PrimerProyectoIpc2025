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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class QueryUsuario {
    public boolean crear(Usuario usuario)  {
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
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
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
        // Establecer conexión
        connection = Coneccion.getConnection();

        // Crear la sentencia SQL para encontrar un usuario por nombre de usuario
        String sql = "SELECT id_usuario, nombre_usuario, id_rol, password FROM Usuario WHERE nombre_usuario = ?";
        
        // Preparar el PreparedStatement
        pstmt = connection.prepareStatement(sql);
        
        // Establecer el valor del nombre de usuario a buscar
        pstmt.setString(1, nombreUsuario);
        
        // Ejecutar la consulta
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

}


