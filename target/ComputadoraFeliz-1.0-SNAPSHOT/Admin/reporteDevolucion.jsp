<%@page import="Dto.ReporteDevolucionDTO"%>
<%@page import="Dto.DetalleProductoVentaDTO"%>
<%@page import="Dto.ReporteVentaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reporte de Ventas</title>
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

            
            .detalle-productos {
                background-color: #f8f9fa;
            }

            .detalle-productos td {
                padding-left: 30px;
            }

            .detalle-productos table {
                background-color: #fff;
                box-shadow: none;
                margin: 0;
                border-radius: 8px;
            }

            .detalle-productos th {
                background-color: #6c757d;
                color: white;
            }

            .detalle-productos tr:hover {
                background-color: #e9ecef;
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

           
            td {
                font-size: 14px;
                color: #495057;
            }

          
            @media (max-width: 768px) {
                th, td {
                    padding: 10px;
                }

                h2 {
                    font-size: 24px;
                }

                .container {
                    padding: 10px;
                }
            }
        </style>
    </head>
    <body>
        <%@ include file="../Header.jsp" %>
        <form action="/ComputadoraFeliz/ReporteVentaServlet" method="get">>
            <input type="Date" name="fechaInicio" > 
            <input type="Date" name="fechaFin">
            <button type="submit" > FILTRAR</button>
        </form>
        <div class="container">
            <h2>Reporte de Devolucion</h2>

            <% List<ReporteDevolucionDTO> ventas = (List<ReporteDevolucionDTO>) request.getAttribute("reportes"); %>
            <% if (ventas != null && !ventas.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Fecha Venta</th>
                        <th>Nombre cliente</th>
                        <th>Nombre computadora</th>
                        <th>Fecha Devolucion</th>
                        <th>Perdida</th>
                    </tr>
                </thead>
                <% for (ReporteDevolucionDTO venta : ventas) {%>

                <tbody>
                    <tr>
                        <td><%= venta.getFechaDevolucion()%></td>
                        <td><%= venta.getNombreCliente()%></td>
                       <td><%= venta.getNombreComputadora()%></td>
                        <td><%= venta.getFechaVenta()%></td>
                        <td><%= venta.getPerdida()%></td>
                    </tr>


                    <% } %>
                </tbody>
            </table>
            <br>
            <% } else { %>
            <div class="no-ventas">
                No hay devoluciones en el período seleccionado.
            </div>
            <% }%>
        </div>
    </body>
</html>