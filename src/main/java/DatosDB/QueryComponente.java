/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Componente;
import Modelos.Coneccion;
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
public class QueryComponente {

    public boolean crear(Componente componente) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Componente (nombre, costo, cantidad_stock) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, componente.getNombre());
            pstmt.setDouble(2, componente.getCosto());
            pstmt.setInt(3, componente.getCantidadStock());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public Componente encontrarPorNombre(String nombre) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();

            String sql = "SELECT id_componente, nombre, costo, cantidad_stock FROM Componente WHERE nombre = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, nombre);

            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                int idComponente = resultado.getInt("id_componente");
                String nombreComponente = resultado.getString("nombre");
                double costo = resultado.getDouble("costo");
                int cantidadStock = resultado.getInt("cantidad_stock");

                return new Componente(idComponente, nombreComponente, costo, cantidadStock);
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
