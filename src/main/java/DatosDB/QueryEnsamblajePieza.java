/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Computadora;
import Modelos.Coneccion;
import Modelos.EnsamblajePieza;
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
public class QueryEnsamblajePieza {

    public boolean crear(EnsamblajePieza ensamblajePieza) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Ensamblaje_Pieza (id_computadora, id_componente, cantidad) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, ensamblajePieza.getComputadora().getIdComputadora());
            pstmt.setInt(2, ensamblajePieza.getComponente().getIdComponente());
            pstmt.setInt(3, ensamblajePieza.getCantidad());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(QueryEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QueryEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean validarSiTieneReceta(int idComputadora) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try{
             connection = Coneccion.getConnection();

            String sql = "SELECT id_computadora, id_componente, cantidad FROM ensamblaje_pieza WHERE id_computadora = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, idComputadora);

            ResultSet resultado = pstmt.executeQuery();
             
             return resultado.next();
        }catch (SQLException e) {
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
        
       return false;
    }
}
