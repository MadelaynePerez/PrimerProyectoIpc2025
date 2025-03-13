<%-- 
    Document   : RegistroVentas
    Created on : 9/03/2025, 19:17:29
    Author     : Ana
--%>

<%@page import="Modelos.Cliente"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Punto de Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-container {
                max-width: 800px;
                margin: 20px auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 10px;
                background-color: #f9f9f9;
            }
            .form-container h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .form-container label {
                font-weight: bold;
            }
            .form-container table {
                width: 100%;
                margin-bottom: 20px;
            }
            .form-container table th, .form-container table td {
                padding: 10px;
                text-align: center;
            }
            .form-container table input, .form-container table select {
                width: 100%;
                padding: 5px;
            }
            .form-container .btn-add {
                margin-bottom: 20px;
            }
        </style>

    </head>
    <body>
       
         <%@ include file="../Header.jsp" %>
        <div class="container form-container">
            <h2>Punto de Venta</h2>
            <form action="/ComputadoraFeliz/BuscarClienteServlet" method ="post">
                <div class="mb-3">
                    <label for="nit" class="form-label">NIT del Cliente:</label>
                    <div class="input-group">
                        <input type="text" id="nit" name="nit" class="form-control" required>
                        <button type="submit" class="btn btn-primary" >Buscar</button>
                    </div>
                </div>
            </form>
            <form action="/ComputadoraFeliz/RegistrarVentaServlet" method="post">


                <%  Cliente resultado = (Cliente) request.getAttribute("cliente");
                    String nit = request.getAttribute("nit") != null ? request.getAttribute("nit").toString() : "";
                    if (resultado != null) {
                %>
                <div id="registroCliente">
                    <h3>Registrar Cliente</h3>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nit:</label>
                        <input type="text" id="nit" name="nit" class="form-control" disabled="true" value="<%= resultado.getNit()%>">
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" disabled="true" value="<%= resultado.getNombre()%>">
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" class="form-control" disabled="true" value="<%= resultado.getDireccion()%>">
                    </div>
                </div>
                <%
                } else {
                %>
                <div id="registroCliente">
                    <h3>Registrar Cliente</h3>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nit:</label>
                        <input type="text" id="nit" name="nit" class="form-control" required="true" value="<%= nit%>">
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" required="true">
                    </div>

                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" class="form-control" required="true">
                    </div>
                </div>
                <%
                    }
                %>


                <h3>Productos</h3>

                <table id="tablaEnsamblajes" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nombre Computadora</th>
                            <th>Cantidad</th>
                            <th>Precio Venta</th>
                            <th>Agregar</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="computadora" items="${ventas}">
                            <tr>
                                <td>${computadora.getNombreComputadora()}</td>
                                <td>${computadora.getTotal()}</td>
                                <td>${computadora.getPrecioventa()}</td>
                                <td>
                                    <input name="compra-${computadora.getIdCompotadora()}" type="number" min="0" max="${computadora.getTotal()}"value="0">
                                        
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <br><br>

                <button type="submit" class="btn btn-primary">Realizar Venta</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>