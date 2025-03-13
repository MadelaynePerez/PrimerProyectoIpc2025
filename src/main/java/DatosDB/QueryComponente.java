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
import java.util.ArrayList;
import java.util.List;
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
      Componente componenteExisiste=  encontrarPorNombre(componente.getNombre());
        try {
            if (componenteExisiste != null) {
                return false;
            }
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

    public boolean editarComponente(int idComponente, String nombre, double costo, int cantidadStock) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Coneccion.getConnection();
            String sql = "UPDATE componente SET nombre = ?, costo = ?, cantidad_stock = ? WHERE id_componente = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, nombre);
            pstmt.setDouble(2, costo);
            pstmt.setInt(3, cantidadStock);
            pstmt.setInt(4, idComponente);

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public boolean eliminarComponente(int idComponente) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "DELETE FROM componente WHERE id_componente = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idComponente);
            int filasEliminadas = pstmt.executeUpdate();
            return filasEliminadas > 0;

        } catch (SQLException ex) {
            Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    public List<Componente> listarComponentes(boolean ordenAsc) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Componente> componentes = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            String orden = ordenAsc ? "ASC" : "DESC";
            String sql = "SELECT id_componente, nombre, costo, cantidad_stock FROM componente ORDER BY cantidad_stock " + orden;
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Componente componente = new Componente();
                componente.setIdComponente(rs.getInt("id_componente"));
                componente.setNombre(rs.getString("nombre"));
                componente.setCosto(rs.getDouble("costo"));
                componente.setCantidadStock(rs.getInt("cantidad_stock"));
                componentes.add(componente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryComponente.class.getName()).log(Level.SEVERE, null, ex);
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

        return componentes;
    }

    public List<Componente> obtenerComponentesSinStock() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultado = null;
        List<Componente> componentesSinStock = new ArrayList<>();

        try {

            connection = Coneccion.getConnection();

            String sql = "SELECT id_componente, nombre, costo, cantidad_stock FROM componente WHERE cantidad_stock = 0";

            pstmt = connection.prepareStatement(sql);

            resultado = pstmt.executeQuery();

            while (resultado.next()) {
                int idComponente = resultado.getInt("id_componente");
                String nombreComponente = resultado.getString("nombre");
                double costo = resultado.getDouble("costo");
                int cantidadStock = resultado.getInt("cantidad_stock");

                Componente componente = new Componente(idComponente, nombreComponente, costo, cantidadStock);
                componentesSinStock.add(componente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultado != null) {
                    resultado.close();
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

        return componentesSinStock;
    }

}
