/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Dto.ComputadoraVenta;
import Modelos.Computadora;
import Modelos.ComputadoraEnsamblada;
import Modelos.Coneccion;
import Modelos.Rol;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.Date;
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
public class QueryEnsambleComputadora {

    public boolean crear(ComputadoraEnsamblada ensamblado) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO computadora_Ensamblada (id_computadora, id_usuario, fecha_ensamblaje, costo_total) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, ensamblado.getComputadora().getIdComputadora());
            pstmt.setInt(2, ensamblado.getUsuario().getIdUsuario());
            pstmt.setDate(3, new Date(ensamblado.getFechaEnsamblaje().getTime())); // Convertir java.util.Date a
            // java.sql.Date
            pstmt.setDouble(4, ensamblado.getCostoTotal());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean stockNecesarioParaEnsamblar(int idComputadora) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT ep.id_componente, ep.cantidad, c.cantidad_stock "
                    + "FROM ensamblaje_pieza ep "
                    + "JOIN componente c ON ep.id_componente = c.id_componente "
                    + "WHERE ep.id_computadora = ?";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idComputadora);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int cantidadRequerida = rs.getInt("cantidad");
                int cantidadDisponible = rs.getInt("cantidad_stock");

                if (cantidadDisponible < cantidadRequerida) {
                    return false;
                }
            }

            return true;
        } catch (Exception ex) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean ensamblarComputadora(int idComputadora, int idUsuario, Date fechaEnsamblaje) {
        Connection connection = null;
        PreparedStatement pstmtUpdate = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            connection.setAutoCommit(false);

            if (!stockNecesarioParaEnsamblar(idComputadora)) {
                return false;
            }

            String sqlStockUpdate = "UPDATE componente c "
                    + "JOIN ensamblaje_pieza ep ON c.id_componente = ep.id_componente "
                    + "SET c.cantidad_stock = c.cantidad_stock - ep.cantidad "
                    + "WHERE ep.id_computadora = ?";

            pstmtUpdate = connection.prepareStatement(sqlStockUpdate);
            pstmtUpdate.setInt(1, idComputadora);
            pstmtUpdate.executeUpdate();

            String sqlInsert = "INSERT INTO computadora_ensamblada (id_computadora, id_usuario, fecha_ensamblaje, costo_total) "
                    + "VALUES (?, ?, ?, "
                    + "(SELECT SUM(c.costo * ep.cantidad) FROM ensamblaje_pieza ep "
                    + " JOIN componente c ON ep.id_componente = c.id_componente "
                    + " WHERE ep.id_computadora = ?))";

            pstmtInsert = connection.prepareStatement(sqlInsert);
            pstmtInsert.setInt(1, idComputadora);
            pstmtInsert.setInt(2, idUsuario);
            pstmtInsert.setDate(3, fechaEnsamblaje);
            pstmtInsert.setInt(4, idComputadora);

            int filasInsertadas = pstmtInsert.executeUpdate();

            if (filasInsertadas > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmtUpdate != null) {
                try {
                    pstmtUpdate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmtInsert != null) {
                try {
                    pstmtInsert.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryEnsambleComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public List<ComputadoraEnsamblada> listarComputadorasEnsambladas(boolean esAsc) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ComputadoraEnsamblada> computadorasEnsambladas = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            String orden = esAsc ? "ASC" : "DESC";

            String sql = "SELECT ce.id_ensamblado, c.nombre AS nombre_computadora, c.precio_venta, u.nombre_usuario, ce.fecha_ensamblaje, ce.costo_total "
                    + "FROM computadora_ensamblada ce "
                    + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                    + "JOIN usuario u ON ce.id_usuario = u.id_usuario WHERE ce.vendido = false "
                    + "ORDER BY ce.fecha_ensamblaje " + orden;

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ComputadoraEnsamblada computadoraEnsamblada = new ComputadoraEnsamblada();
                computadoraEnsamblada.setIdEnsamblado(rs.getInt("id_ensamblado"));
                computadoraEnsamblada.setComputadora(
                        new Computadora(0, rs.getString("nombre_computadora"), rs.getDouble("precio_venta")));
                computadoraEnsamblada.setUsuario(new Usuario(0, rs.getString("nombre_usuario"), "", new Rol()));
                computadoraEnsamblada.setFechaEnsamblaje(rs.getDate("fecha_ensamblaje"));
                computadoraEnsamblada.setCostoTotal(rs.getDouble("costo_total"));
                computadorasEnsambladas.add(computadoraEnsamblada);
            }

        } catch (Exception ex) {
            int x = 0;
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

        return computadorasEnsambladas;

    }

    public ComputadoraEnsamblada encontrarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();

            String sql = "SELECT ec.id_ensamblado, c.id_computadora, c.precio_venta, c.nombre AS nombre_computadora, ec.nuevo_precio, "
                    + "u.id_usuario, u.nombre_usuario, ec.fecha_ensamblaje, ec.costo_total, ec.vendido "
                    + "FROM computadora_ensamblada ec "
                    + "JOIN computadora c ON ec.id_Computadora = c.id_computadora "
                    + "JOIN usuario u ON ec.id_usuario = u.id_usuario "
                    + "WHERE ec.id_ensamblado = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ComputadoraEnsamblada ensamblado = new ComputadoraEnsamblada();
                ensamblado.setIdEnsamblado(rs.getInt("id_ensamblado"));
                ensamblado.setNuevoPrecio(rs.getDouble("ec.nuevo_precio"));

                Computadora computadora = new Computadora();
                computadora.setIdComputadora(rs.getInt("id_computadora"));
                computadora.setNombre(rs.getString("nombre_computadora"));
                computadora.setPrecioVenta(rs.getDouble("precio_venta"));
                ensamblado.setComputadora(computadora);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                ensamblado.setUsuario(usuario);

                ensamblado.setFechaEnsamblaje(rs.getDate("fecha_ensamblaje"));
                ensamblado.setCostoTotal(rs.getDouble("costo_total"));

                return ensamblado;
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

    public List<ComputadoraVenta> listarComputadorasParaVenta() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ComputadoraVenta> computadorasEnsambladas = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT c.nombre AS nombre_computadora, c.precio_venta, COUNT(*) AS total, c.id_computadora, ce.nuevo_precio "
                    + "FROM computadora_ensamblada ce "
                    + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                    + "WHERE ce.vendido = false "
                    + "GROUP BY c.nombre, c.precio_venta, c.id_computadora, ce.nuevo_precio";

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ComputadoraVenta computadoraventa = new ComputadoraVenta(rs.getInt("c.id_computadora"), rs.getDouble("c.precio_venta") - rs.getDouble("ce.nuevo_precio"), rs.getInt("total"), rs.getString("nombre_computadora"));
                computadorasEnsambladas.add(computadoraventa);

            }

        } catch (Exception ex) {
            int x = 0;
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

        return computadorasEnsambladas;
    }

    public List<ComputadoraEnsamblada> encontrarPorIdComputadora(int idComputadora, int limite) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ComputadoraEnsamblada> computadorasEnsamblada = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT id_ensamblado,c.id_computadora, id_usuario, fecha_ensamblaje, costo_total, vendido, c.precio_venta, nuevo_precio "
                    + "FROM computadora_ensamblada"
                    + " INNER JOIN computadora c on c.id_computadora = computadora_ensamblada.id_computadora"
                    + " WHERE c.id_computadora = ? And vendido = false limit ?";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idComputadora);
            pstmt.setInt(2, limite);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ComputadoraEnsamblada computadoraEnsamble = new ComputadoraEnsamblada(
                        rs.getInt("id_ensamblado"),
                        new Computadora(rs.getInt("c.id_computadora"), "", rs.getDouble("c.precio_venta")),
                        new Usuario(rs.getInt("id_usuario"), "", "", new Rol(0, "")),
                        rs.getDate("fecha_ensamblaje"),
                        rs.getInt("costo_total"),
                        rs.getBoolean("vendido"),
                        rs.getDouble("nuevo_precio"));
                computadorasEnsamblada.add(computadoraEnsamble);

            }

        } catch (Exception ex) {
            int x = 0;
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
        return computadorasEnsamblada;
    }

    public void actualizarVendido(List<Integer> idsEnsamblados) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "UPDATE computadora_ensamblada SET vendido = true WHERE id_ensamblado = ?";

            pstmt = connection.prepareStatement(sql);

            for (int idEnsamblado : idsEnsamblados) {
                pstmt.setInt(1, idEnsamblado);
                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
