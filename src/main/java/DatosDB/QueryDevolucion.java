package DatosDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelos.ComputadoraEnsamblada;
import Modelos.Coneccion;
import Modelos.Devolucion;

public class QueryDevolucion {
     public boolean crear(Devolucion devolucion) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        QueryEnsambleComputadora queryEnsambleComputadora = new QueryEnsambleComputadora();

        try {

            ComputadoraEnsamblada computadoraEnsamblada = queryEnsambleComputadora.encontrarPorId(devolucion.getComputadoraEnsamblada().getIdEnsamblado());

            connection = Coneccion.getConnection();
            String sqlInsertDevolucion = "INSERT INTO devolucion (id_venta, id_ensamblaje, perdida, fecha) VALUES (?, ?, ?, now())";
            pstmt = connection.prepareStatement(sqlInsertDevolucion);
            pstmt.setInt(1, devolucion.getVenta().getIdVenta());
            pstmt.setInt(2, devolucion.getComputadoraEnsamblada().getIdEnsamblado());
            pstmt.setDouble(3, computadoraEnsamblada.getComputadora().getPrecioVenta() / 3);
            pstmt.executeUpdate();
            int filasInsertadas = pstmt.executeUpdate();
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

}
