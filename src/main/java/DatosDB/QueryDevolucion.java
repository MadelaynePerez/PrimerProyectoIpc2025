package DatosDB;

import Modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelos.ComputadoraEnsamblada;
import Modelos.Coneccion;
import Modelos.Devolucion;
import Modelos.Venta;
import java.sql.Date;
import java.sql.ResultSet;

public class QueryDevolucion {

    public boolean crear(int idVenta, int idEnsamblado) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        QueryEnsambleComputadora queryEnsambleComputadora = new QueryEnsambleComputadora();
        
        try {

            ComputadoraEnsamblada computadoraEnsamblada = queryEnsambleComputadora.encontrarPorId(idEnsamblado);
            if (computadoraEnsamblada.getNuevoPrecio() > 0) {
                return false;
            }
            connection = Coneccion.getConnection();
            String sqlInsertDevolucion = "INSERT INTO devolucion (id_venta, id_ensamblaje, perdida, fecha_devolucion) VALUES (?, ?, ?, now())";
            pstmt = connection.prepareStatement(sqlInsertDevolucion);
            pstmt.setInt(1, idVenta);
            pstmt.setInt(2, idEnsamblado);
            pstmt.setDouble(3, computadoraEnsamblada.getComputadora().getPrecioVenta() / 3);
                       
            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                reintegrarComputadora(idEnsamblado, computadoraEnsamblada.getComputadora().getPrecioVenta()/ 3);
            }
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryComputadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryComputadora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public void reintegrarComputadora(int idEnsamblado, double precioActual) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "UPDATE computadora_ensamblada SET vendido = false, nuevo_precio = ? WHERE id_ensamblado = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(2, idEnsamblado);
            pstmt.setDouble(1, precioActual);

            pstmt.executeUpdate();

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
    
      public Devolucion buscarPorIdventayIdEnsamblado(int idVenta, int idEnsamble) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT id_venta, id_ensamblaje, perdida, fecha_devolucion FROM devolucion WHERE id_venta = ? And id_ensamblaje = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idVenta);
            pstmt.setInt(2, idEnsamble);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int idventa = rs.getInt("id_venta");
                int idEnsamblaje = rs.getInt("id_ensamblaje");
                double perdida = rs.getDouble("perdida");
                Date fechaDevolucion = rs.getDate("fecha_devolucion");

                return new Devolucion(idventa, null, fechaDevolucion, perdida, null);
               
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
}
