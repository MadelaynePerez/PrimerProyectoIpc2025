/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Dto.DevolucionDto;
import Dto.VentaDto;
import Modelos.Cliente;
import Modelos.Computadora;
import Modelos.ComputadoraEnsamblada;
import Modelos.Coneccion;
import Modelos.DetalleVenta;
import Modelos.Rol;
import Modelos.Usuario;
import Modelos.Venta;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class QueryVenta {

    public boolean RegistrarVenta(Cliente clienteCompra, List<VentaDto> detalleVenta) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        QueryCliente queryCliente = new QueryCliente();

        try {
            Cliente cliente = queryCliente.obtenerClientePorNit(clienteCompra.getNit());
            if (cliente == null) {
                queryCliente.crear(clienteCompra);
                cliente = queryCliente.obtenerClientePorNit(clienteCompra.getNit());
            }
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO venta (id_cliente, fecha_venta, total_venta) VALUES (?, now(), ?)";
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            QueryEnsambleComputadora queryEnsambleComputadora = new QueryEnsambleComputadora();

            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setDouble(2, 0);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {

                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {

                    int idGenerado = rs.getInt(1);

                    String sqlDetalleVenta = "INSERT INTO `ensamblaje`.`detalle_venta`\n"
                            + "(`id_venta`,\n"
                            + "`id_ensamblado`,\n"
                            + "`cantidad`)\n"
                            + "VALUES\n";
                    List<Integer> computadorasVendidasId = new ArrayList<>();
                    double costoTotal = 0;
                    for (int i = 0; i < detalleVenta.size(); i++) {
                        VentaDto detalle = detalleVenta.get(i);
                        List<ComputadoraEnsamblada> computadorasVender = queryEnsambleComputadora.encontrarPorIdComputadora(detalle.getIdComputadora(), detalle.getCantidad());
                        for (int j = 0; j < computadorasVender.size(); j++) {
                            computadorasVendidasId.add(computadorasVender.get(j).getIdEnsamblado());
                            costoTotal += computadorasVender.get(j).getComputadora().getPrecioVenta();
                            sqlDetalleVenta += " (" + idGenerado + "," + computadorasVender.get(j).getIdEnsamblado() + ",1),";
                        }

                    }
                    sqlDetalleVenta = sqlDetalleVenta.substring(0, sqlDetalleVenta.length() - 1);
                    pstmt = connection.prepareStatement(sqlDetalleVenta);
                    pstmt.executeUpdate();
                    queryEnsambleComputadora.actualizarVendido(computadorasVendidasId);
                    actualizarCostoTotal(idGenerado, costoTotal);
                }
            }

        } catch (Exception e) {
            int x = 0;
        }

        return false;
    }

    public void actualizarCostoTotal(int idVenta, double nuevoCostoTotal) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = Coneccion.getConnection();

            String sql = "UPDATE venta SET total_venta = ? WHERE id_venta = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setDouble(1, nuevoCostoTotal);
            pstmt.setInt(2, idVenta);

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

    public List<DevolucionDto> detalleDevolucion(int idFactura) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DevolucionDto> listaFactura = new ArrayList<>();

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT ce.id_ensamblado, c.nombre AS nombre_computadora, c.precio_venta, ce.nuevo_precio "
                    + "FROM computadora_ensamblada ce "
                    + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                    + "JOIN detalle_venta u ON ce.id_ensamblado = u.id_ensamblado WHERE u.id_venta = ? "
                    + "ORDER BY ce.fecha_ensamblaje ";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idFactura);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                DevolucionDto devoluciondto = new DevolucionDto(rs.getString("nombre_computadora"), rs.getInt("ce.id_ensamblado"), rs.getDouble("c.precio_venta") - rs.getDouble("ce.nuevo_precio"), idFactura);
                listaFactura.add(devoluciondto);
            }

        } catch (Exception ex) {
            int x = 0;
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

        return listaFactura;

    }

    public Venta encontrarVentaPorId(int idVenta) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Coneccion.getConnection();

            String sql = "SELECT id_venta, fecha_venta, total_venta, id_cliente FROM venta WHERE id_venta = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idVenta);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int idVentaResultado = rs.getInt("id_venta");
                Date fechaVenta = rs.getDate("fecha_venta");
                double totalVenta = rs.getDouble("total_venta");
                int idCliente = rs.getInt("id_cliente");

                Cliente cliente = new Cliente(idCliente, null, null, null);

                return new Venta(idVentaResultado, cliente, fechaVenta, totalVenta);
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
