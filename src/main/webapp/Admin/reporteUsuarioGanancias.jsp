<%@page import="Dto.ReporteUsuarioGananciasDTO"%>
<%@page import="Dto.ProductoGananciaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Usuario con Más Ganancias</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #333;
            line-height: 1.6;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 30px;
            font-size: 28px;
            text-align: center;
            font-weight: 700;
            padding-top: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            margin-bottom: 20px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #e9ecef;
        }

        th {
            background-color: #007bff;
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .no-ventas {
            text-align: center;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            color: #6c757d;
            font-size: 16px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="../Header.jsp" %>
    <form action="<%= request.getContextPath() %>/ReporteUsuarioGananciaServlet" method="get">
        <input type="date" name="fechaInicio" required>
        <input type="date" name="fechaFin" required>
        <button type="submit">FILTRAR</button>
    </form>
    <div class="container">
        <h2>Usuario con Más Ganancias</h2>

        <% ReporteUsuarioGananciasDTO reporte = (ReporteUsuarioGananciasDTO) request.getAttribute("reportes"); %>
        <% if (reporte != null) { %>
            <h3>Usuario: <%= reporte.getNombreUsuario() %></h3>
            <h3>Total de Ganancias: <%= reporte.getTotalGanancias() %></h3>

            <table>
                <thead>
                    <tr>
                        <th>Nombre Computadora</th>
                        <th>Costo Ensamblaje</th>
                        <th>Precio Venta</th>
                        <th>Ganancia</th>
                        <th>Cantidad Vendida</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ProductoGananciaDTO producto : reporte.getReporteGanancia().getProductos()) { %>
                        <tr>
                            <td><%= producto.getNombreComputadora() %></td>
                            <td><%= producto.getCostoEnsamblaje() %></td>
                            <td><%= producto.getPrecioVenta() %></td>
                            <td><%= producto.getGanancia() %></td>
                            <td><%= producto.getCantidadVendida() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <div class="no-ventas">
                No hay datos de ganancias en el período seleccionado.
            </div>
        <% } %>
    </div>
</body>
</html>
