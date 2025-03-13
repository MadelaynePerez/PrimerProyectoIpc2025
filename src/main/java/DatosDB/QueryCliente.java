/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Cliente;
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
public class QueryCliente {

    public boolean crear(Cliente cliente) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Cliente (nit, nombre, direccion) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, cliente.getNit());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getDireccion());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    public Cliente obtenerClientePorNit(String nit)  {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "SELECT id_cliente, nit, nombre, direccion FROM cliente WHERE nit = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nit);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nit"),
                        rs.getString("nombre"),
                        rs.getString("direccion")
                );
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QueryCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

}
