package DatosDB;

import Dto.DetalleProductoVentaDTO;
import Dto.ProductoGananciaDTO;
import Dto.ReporteComputadoraVendidaDTO;
import Dto.ReporteDevolucionDTO;
import Dto.ReporteGananciaDTO;
import Dto.ReporteUsuarioGananciasDTO;
import Dto.ReporteUsuarioVentasDTO;
import Dto.ReporteVentaDTO;
import Dto.VentaComputadoraDTO;
import Modelos.Coneccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class QueryReportes {

    public List<ReporteVentaDTO> obtenerReporteVentas(Date fechaInicio, Date fechaFin) {
        Connection conn = Coneccion.getConnection();
        List<ReporteVentaDTO> reporteVentas = new ArrayList<>();

        String sql = "SELECT v.id_venta, v.fecha_venta, c.nombre, c.nit, v.total_venta "
                + "FROM venta v "
                + "JOIN cliente c ON v.id_cliente = c.id_cliente ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "ORDER BY v.fecha_venta DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idVenta = rs.getInt("id_venta");
                    Date fechaVenta = rs.getDate("fecha_venta");
                    String nombreCliente = rs.getString("nombre");
                    String nitCliente = rs.getString("nit");
                    double totalVenta = rs.getDouble("total_venta");

                    ReporteVentaDTO venta = new ReporteVentaDTO(idVenta, fechaVenta, nombreCliente, nitCliente,
                            totalVenta);

                    obtenerProductosVenta(venta);

                    reporteVentas.add(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporteVentas;
    }

    private void obtenerProductosVenta(ReporteVentaDTO venta) {
        Connection conn = Coneccion.getConnection();
        String sql = "SELECT ce.id_ensamblado, c.nombre, ce.costo_total, c.precio_venta, dv.cantidad "
                + "FROM detalle_venta dv "
                + "JOIN computadora_ensamblada ce ON dv.id_ensamblado = ce.id_ensamblado "
                + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                + "WHERE dv.id_venta = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getIdVenta());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEnsamblado = rs.getInt("id_ensamblado");
                    String nombreComputadora = rs.getString("nombre");
                    double precioUnitario = rs.getDouble("precio_venta");
                    int cantidad = rs.getInt("cantidad");

                    DetalleProductoVentaDTO producto = new DetalleProductoVentaDTO(
                            idEnsamblado, nombreComputadora, precioUnitario, cantidad);

                    venta.addProducto(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ReporteDevolucionDTO> obtenerReporteDevoluciones(Date fechaInicio, Date fechaFin) {
        List<ReporteDevolucionDTO> reporteDevoluciones = new ArrayList<>();
        Connection conn = Coneccion.getConnection();
        String sql = "SELECT d.id_devolucion, d.id_venta, v.fecha_venta, d.fecha_devolucion, "
                + "c.nombre, c.nit, v.total_venta, d.perdida, d.id_ensamblaje, comp.nombre as nombre_computadora "
                + "FROM devolucion d "
                + "JOIN venta v ON d.id_venta = v.id_venta "
                + "JOIN cliente c ON v.id_cliente = c.id_cliente "
                + "JOIN computadora_ensamblada ce ON d.id_ensamblaje = ce.id_ensamblado "
                + "JOIN computadora comp ON ce.id_computadora = comp.id_computadora ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "d.fecha_devolucion >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "d.fecha_devolucion <= ? ";
            }
        }

        sql += "ORDER BY d.fecha_devolucion DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idDevolucion = rs.getInt("id_devolucion");
                    int idVenta = rs.getInt("id_venta");
                    Date fechaVenta = rs.getDate("fecha_venta");
                    Date fechaDevolucion = rs.getDate("fecha_devolucion");
                    String nombreCliente = rs.getString("nombre");
                    String nitCliente = rs.getString("nit");
                    double totalVenta = rs.getDouble("total_venta");
                    double perdida = rs.getDouble("perdida");
                    int idEnsamblado = rs.getInt("id_ensamblaje");
                    String nombreComputadora = rs.getString("nombre_computadora");

                    ReporteDevolucionDTO devolucion = new ReporteDevolucionDTO(
                            idDevolucion, idVenta, fechaVenta, fechaDevolucion,
                            nombreCliente, nitCliente, totalVenta, perdida,
                            idEnsamblado, nombreComputadora);

                    reporteDevoluciones.add(devolucion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporteDevoluciones;
    }

    public ReporteGananciaDTO obtenerReporteGanancias(Date fechaInicio, Date fechaFin) {
        ReporteGananciaDTO reporte = new ReporteGananciaDTO();
        Connection conn = Coneccion.getConnection();
        String sql = "SELECT ce.id_ensamblado, c.nombre, ce.costo_total, c.precio_venta, SUM(dv.cantidad) as cantidad_vendida "
                + "FROM detalle_venta dv "
                + "JOIN venta v ON dv.id_venta = v.id_venta "
                + "JOIN computadora_ensamblada ce ON dv.id_ensamblado = ce.id_ensamblado "
                + "JOIN computadora c ON ce.id_computadora = c.id_computadora ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "GROUP BY ce.id_ensamblado, c.nombre, ce.costo_total, c.precio_venta "
                + "ORDER BY cantidad_vendida DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEnsamblado = rs.getInt("id_ensamblado");
                    String nombreComputadora = rs.getString("nombre");
                    double costoEnsamblaje = rs.getDouble("costo_total");
                    double precioVenta = rs.getDouble("precio_venta");
                    int cantidadVendida = rs.getInt("cantidad_vendida");

                    ProductoGananciaDTO producto = new ProductoGananciaDTO(
                            idEnsamblado, nombreComputadora, costoEnsamblaje,
                            precioVenta, cantidadVendida);

                    reporte.addProducto(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporte;
    }

    public ReporteUsuarioVentasDTO obtenerUsuarioMasVentas(Date fechaInicio, Date fechaFin) {
        ReporteUsuarioVentasDTO usuarioMasVentas = null;
        Connection conn = Coneccion.getConnection();
        String sql = "SELECT u.id_usuario, u.nombre_usuario, COUNT(v.id_venta) as total_ventas "
                + "FROM usuario u "
                + "JOIN computadora_ensamblada ce ON u.id_usuario = ce.id_usuario "
                + "JOIN detalle_venta dv ON ce.id_ensamblado = dv.id_ensamblado "
                + "JOIN venta v ON dv.id_venta = v.id_venta ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "GROUP BY u.id_usuario, u.nombre_usuario "
                + "ORDER BY total_ventas DESC "
                + "LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nombreUsuario = rs.getString("nombre_usuario");

                    usuarioMasVentas = new ReporteUsuarioVentasDTO(idUsuario, nombreUsuario);

                    obtenerVentasUsuario(usuarioMasVentas, fechaInicio, fechaFin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioMasVentas;
    }

    private void obtenerVentasUsuario(ReporteUsuarioVentasDTO usuario, Date fechaInicio, Date fechaFin) {
        String sql = "SELECT v.id_venta, v.fecha_venta, c.nombre, c.nit, v.total_venta "
                + "FROM venta v "
                + "JOIN cliente c ON v.id_cliente = c.id_cliente "
                + "JOIN detalle_venta dv ON v.id_venta = dv.id_venta "
                + "JOIN computadora_ensamblada ce ON dv.id_ensamblado = ce.id_ensamblado "
                + "WHERE ce.id_usuario = ? ";

        if (fechaInicio != null) {
            sql += "AND v.fecha_venta >= ? ";
        }
        if (fechaFin != null) {
            sql += "AND v.fecha_venta <= ? ";
        }

        sql += "GROUP BY v.id_venta, v.fecha_venta, c.nombre, c.nit, v.total_venta "
                + "ORDER BY v.fecha_venta DESC";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, usuario.getIdUsuario());

            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idVenta = rs.getInt("id_venta");
                    Date fechaVenta = rs.getDate("fecha_venta");
                    String nombreCliente = rs.getString("nombre");
                    String nitCliente = rs.getString("nit");
                    double totalVenta = rs.getDouble("total_venta");

                    ReporteVentaDTO venta = new ReporteVentaDTO(idVenta, fechaVenta, nombreCliente, nitCliente,
                            totalVenta);

                    obtenerProductosVenta(venta);

                    usuario.addVenta(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ReporteUsuarioGananciasDTO obtenerUsuarioMasGanancias(Date fechaInicio, Date fechaFin) {
        ReporteUsuarioGananciasDTO usuarioMasGanancias = null;

        String sql = "SELECT u.id_usuario, u.nombre_usuario, "
                + "SUM((c.precio_venta - ce.costo_total) * dv.cantidad) as total_ganancia "
                + "FROM usuario u "
                + "JOIN computadora_ensamblada ce ON u.id_usuario = ce.id_usuario "
                + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                + "JOIN detalle_venta dv ON ce.id_ensamblado = dv.id_ensamblado "
                + "JOIN venta v ON dv.id_venta = v.id_venta ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "GROUP BY u.id_usuario, u.nombre_usuario "
                + "ORDER BY total_ganancia DESC "
                + "LIMIT 1";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nombreUsuario = rs.getString("nombre_usuario");

                    usuarioMasGanancias = new ReporteUsuarioGananciasDTO(idUsuario, nombreUsuario);

                    ReporteGananciaDTO reporteGanancia = obtenerGananciasUsuario(idUsuario, fechaInicio, fechaFin);
                    usuarioMasGanancias.setReporteGanancia(reporteGanancia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioMasGanancias;
    }

    private ReporteGananciaDTO obtenerGananciasUsuario(int idUsuario, Date fechaInicio, Date fechaFin) {
        ReporteGananciaDTO reporte = new ReporteGananciaDTO();

        String sql = "SELECT ce.id_ensamblado, c.nombre, ce.costo_total, c.precio_venta, SUM(dv.cantidad) as cantidad_vendida "
                + "FROM detalle_venta dv "
                + "JOIN venta v ON dv.id_venta = v.id_venta "
                + "JOIN computadora_ensamblada ce ON dv.id_ensamblado = ce.id_ensamblado "
                + "JOIN computadora c ON ce.id_computadora = c.id_computadora "
                + "WHERE ce.id_usuario = ? ";

        if (fechaInicio != null) {
            sql += "AND v.fecha_venta >= ? ";
        }
        if (fechaFin != null) {
            sql += "AND v.fecha_venta <= ? ";
        }

        sql += "GROUP BY ce.id_ensamblado, c.nombre, ce.costo_total, c.precio_venta "
                + "ORDER BY cantidad_vendida DESC";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, idUsuario);

            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEnsamblado = rs.getInt("id_ensamblado");
                    String nombreComputadora = rs.getString("nombre");
                    double costoEnsamblaje = rs.getDouble("costo_total");
                    double precioVenta = rs.getDouble("precio_venta");
                    int cantidadVendida = rs.getInt("cantidad_vendida");

                    ProductoGananciaDTO producto = new ProductoGananciaDTO(
                            idEnsamblado, nombreComputadora, costoEnsamblaje,
                            precioVenta, cantidadVendida);

                    reporte.addProducto(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporte;
    }

    public ReporteComputadoraVendidaDTO obtenerComputadoraMasVendida(Date fechaInicio, Date fechaFin) {
        ReporteComputadoraVendidaDTO computadoraMasVendida = null;

        String sql = "SELECT c.id_computadora, c.nombre, SUM(dv.cantidad) as total_vendido "
                + "FROM computadora c "
                + "JOIN computadora_ensamblada ce ON c.id_computadora = ce.id_computadora "
                + "JOIN detalle_venta dv ON ce.id_ensamblado = dv.id_ensamblado "
                + "JOIN venta v ON dv.id_venta = v.id_venta ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "GROUP BY c.id_computadora, c.nombre "
                + "ORDER BY total_vendido DESC "
                + "LIMIT 1";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idComputadora = rs.getInt("id_computadora");
                    String nombreComputadora = rs.getString("nombre");

                    computadoraMasVendida = new ReporteComputadoraVendidaDTO(idComputadora, nombreComputadora);

                    obtenerVentasComputadora(computadoraMasVendida, fechaInicio, fechaFin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return computadoraMasVendida;
    }

    public ReporteComputadoraVendidaDTO obtenerComputadoraMenosVendida(Date fechaInicio, Date fechaFin) {
        ReporteComputadoraVendidaDTO computadoraMenosVendida = null;

        String sql = "SELECT c.id_computadora, c.nombre, SUM(dv.cantidad) as total_vendido "
                + "FROM computadora c "
                + "JOIN computadora_ensamblada ce ON c.id_computadora = ce.id_computadora "
                + "JOIN detalle_venta dv ON ce.id_ensamblado = dv.id_ensamblado "
                + "JOIN venta v ON dv.id_venta = v.id_venta ";

        if (fechaInicio != null || fechaFin != null) {
            sql += "WHERE ";
            if (fechaInicio != null) {
                sql += "v.fecha_venta >= ? ";
                if (fechaFin != null) {
                    sql += "AND ";
                }
            }
            if (fechaFin != null) {
                sql += "v.fecha_venta <= ? ";
            }
        }

        sql += "GROUP BY c.id_computadora, c.nombre "
                + "ORDER BY total_vendido ASC "
                + "LIMIT 1";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idComputadora = rs.getInt("id_computadora");
                    String nombreComputadora = rs.getString("nombre");

                    computadoraMenosVendida = new ReporteComputadoraVendidaDTO(idComputadora, nombreComputadora);

                    obtenerVentasComputadora(computadoraMenosVendida, fechaInicio, fechaFin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return computadoraMenosVendida;
    }

    private void obtenerVentasComputadora(ReporteComputadoraVendidaDTO computadora, Date fechaInicio, Date fechaFin) {
        String sql = "SELECT v.id_venta, v.fecha_venta, c.nombre as nombre_cliente, "
                + "dv.cantidad, ce.costo_total as precio_unitario "
                + "FROM venta v "
                + "JOIN cliente c ON v.id_cliente = c.id_cliente "
                + "JOIN detalle_venta dv ON v.id_venta = dv.id_venta "
                + "JOIN computadora_ensamblada ce ON dv.id_ensamblado = ce.id_ensamblado "
                + "WHERE ce.id_computadora = ? ";

        if (fechaInicio != null) {
            sql += "AND v.fecha_venta >= ? ";
        }
        if (fechaFin != null) {
            sql += "AND v.fecha_venta <= ? ";
        }

        sql += "ORDER BY v.fecha_venta DESC";
        Connection conn = Coneccion.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, computadora.getIdComputadora());

            if (fechaInicio != null) {
                stmt.setDate(paramIndex++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(paramIndex, new java.sql.Date(fechaFin.getTime()));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idVenta = rs.getInt("id_venta");
                    Date fechaVenta = rs.getDate("fecha_venta");
                    String nombreCliente = rs.getString("nombre_cliente");
                    int cantidad = rs.getInt("cantidad");
                    double precioUnitario = rs.getDouble("precio_unitario");

                    VentaComputadoraDTO venta = new VentaComputadoraDTO(
                            idVenta, fechaVenta, nombreCliente, cantidad, precioUnitario);

                    computadora.addVenta(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
