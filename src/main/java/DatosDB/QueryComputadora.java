/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Computadora;
import Modelos.Coneccion;
import java.sql.Connection;
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
public class QueryComputadora {

    public boolean crear(Computadora computadora) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Computadora computadoraExiistente =encontrarPorNombre(computadora.getNombre());
        try {
            
            if (computadoraExiistente != null) {
                return false;
            }
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Computadora (nombre, precio_venta) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, computadora.getNombre());
            pstmt.setDouble(2, computadora.getPrecioVenta());

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

    public Computadora encontrarPorNombre(String nombre) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();

            String sql = "SELECT id_computadora, nombre, precio_venta FROM Computadora WHERE nombre = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, nombre);

            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                int idComputadora = resultado.getInt("id_computadora");
                String nombreComputadora = resultado.getString("nombre");
                double precioVenta = resultado.getDouble("precio_venta");

                return new Computadora(idComputadora, nombreComputadora, precioVenta);
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

    public List<Computadora> listarComputadoras() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Computadora> computadoras = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            
            String sql = "SELECT c.id_computadora, c.nombre, c.precio_venta FROM computadora c";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            
            while (rs.next()) {
                Computadora computadora = new Computadora();
                computadora.setIdComputadora(rs.getInt("id_computadora"));
                computadora.setNombre(rs.getString("nombre"));
                computadora.setPrecioVenta(rs.getDouble("precio_venta"));
                computadoras.add(computadora);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryComputadora.class.getName()).log(Level.SEVERE, null, ex);
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

        return computadoras;
    }

    public List<Computadora> listarComputadorasParaEnsamblar() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        QueryEnsamblajePieza queryEnsamblajePieza = new QueryEnsamblajePieza();
        ResultSet rs = null;
        List<Computadora> computadoras = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

           
            String sql = "SELECT c.id_computadora, c.nombre, c.precio_venta FROM computadora c";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

           
            while (rs.next()) {
                if (queryEnsamblajePieza.validarSiTieneReceta(rs.getInt("id_computadora"))) {
                    Computadora computadora = new Computadora();
                    computadora.setIdComputadora(rs.getInt("id_computadora"));
                    computadora.setNombre(rs.getString("nombre"));
                    computadora.setPrecioVenta(rs.getDouble("precio_venta"));
                    computadoras.add(computadora);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryComputadora.class.getName()).log(Level.SEVERE, null, ex);
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

        return computadoras;
    }

    public Computadora encontrarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();

            String sql = "SELECT id_computadora, nombre, precio_venta FROM Computadora WHERE id_computadora = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                int idComputadora = resultado.getInt("id_computadora");
                String nombreComputadora = resultado.getString("nombre");
                double precioVenta = resultado.getDouble("precio_venta");

                return new Computadora(idComputadora, nombreComputadora, precioVenta);
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
