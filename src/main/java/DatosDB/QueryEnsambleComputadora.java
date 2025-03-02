/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.ComputadoraEnsamblada;
import Modelos.Coneccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String sql = "INSERT INTO Computadora_Ensamblada (id_computadora, id_usuario, fecha_ensamblaje, costo_total) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, ensamblado.getComputadora().getIdComputadora());
            pstmt.setInt(2, ensamblado.getUsuario().getIdUsuario());
            pstmt.setDate(3, new Date(ensamblado.getFechaEnsamblaje().getTime())); // Convertir java.util.Date a java.sql.Date
            pstmt.setDouble(4, ensamblado.getCostoTotal());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } finally {
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        }
    }
}
