/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Coneccion;
import Modelos.EnsamblajePieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class QueryEnsamblajePieza {
       public boolean crear(EnsamblajePieza ensamblajePieza) throws SQLException {
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
        } finally {
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        }
    }
}
