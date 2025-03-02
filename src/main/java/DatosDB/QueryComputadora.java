/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Computadora;
import Modelos.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author DELL
 */
public class QueryComputadora {
    public boolean crear(Computadora computadora) throws SQLException {
    Connection connection = null;
    PreparedStatement pstmt = null;

    try {
        connection = Coneccion.getConnection();
        String sql = "INSERT INTO Computadora (nombre, precio_venta) VALUES (?, ?)";
        pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, computadora.getNombre());
        pstmt.setDouble(2, computadora.getPrecioVenta());

        int filasInsertadas = pstmt.executeUpdate();
        return filasInsertadas > 0;
    } finally {
        if (pstmt != null) pstmt.close();
        if (connection != null) connection.close();
    }
}

}
