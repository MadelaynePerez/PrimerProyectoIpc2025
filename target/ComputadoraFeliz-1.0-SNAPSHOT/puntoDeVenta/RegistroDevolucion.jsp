<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro de Devoluciones</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 50%;
                margin: 50px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
            .form-group input, .form-group textarea {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }
            .form-group button {
                padding: 10px 15px;
                background-color: #dc3545;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            .form-group button:hover {
                background-color: #c82333;
            }
            .alert {
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid transparent;
                border-radius: 4px;
            }
            .alert-info {
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
            }
        </style>
    </head>
    <body>
        <%@ include file="../Header.jsp" %>
        <div class="container">
            <h2>Registro de Devoluciones</h2>

           >
            <c:if test="${not empty mensaje}">
                <div class="alert alert-info">${mensaje}</div>
            </c:if>

            <form action="/ComputadoraFeliz/BuscarFacturaServlet" method="post">
                <div class="form-group">
                    <label for="factura">Número de Factura:</label>
                    <input type="number" id="factura" name="factura" required>
                </div>
                <div class="form-group">
                    <button type="submit">Registrar Devolución</button>
                </div>
            </form>

           
            <c:if test="${empty detalles}">
                <p>No se encontraron detalles para la factura.</p>
            </c:if>

            <table id="tablaEnsamblajes" class="table table-bordered">
                <thead>
                    <tr>
                        <th>NOMBRE COMPUTADORA</th>
                        <th>PRECIO</th>
                        <th>ACCIÓN</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detalle" items="${detalles}">
                        <tr>
                            <td>${detalle.getNombreComputadora()}</td>
                            <td>${detalle.getPrecio()}</td>
                            <td>
                                <form action="/ComputadoraFeliz/RealizarDevolucionServlet" method="post">
                                    <input type="submit" value="Realizar devolución">
                                    <input type="hidden" name="ensamble" value="${detalle.getIdEnsamble()}">
                                    <input type="hidden" name="idVenta" value="${detalle.getIdVenta()}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>