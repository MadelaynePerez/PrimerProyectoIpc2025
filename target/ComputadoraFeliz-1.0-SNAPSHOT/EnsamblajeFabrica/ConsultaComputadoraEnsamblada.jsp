<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Computadoras Ensambladas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos personalizados */
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .table thead th {
            background-color: #4CAF50;
            color: white;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .status-vendido {
            color: green;
            font-weight: bold;
        }
        .status-no-vendido {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%@ include file="../Header.jsp" %>
    <div class="container">
        <h1>Lista de Computadoras Ensambladas</h1>
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID Computadora</th>
                    <th>Nombre del Usuario</th>
                    <th>Fecha de Ensamblaje</th>
                    <th>Costo Total</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="computadora" items="${computadorasEnsambladas}">
                    <tr>
                        <td>${computadora.getIdEnsamblado()}</td>
                        <td>${computadora.getUsuario().getNombreUsuario()}</td>
                        <td>${computadora.getFechaEnsamblaje()}</td>
                        <td>$${computadora.getCostoTotal()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${computadora.isVendido()}">
                                    <span class="status-vendido">Sí</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status-no-vendido">No</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Bootstrap JS (opcional, si no necesitas JavaScript no lo incluyas) -->
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> -->
</body>
</html>