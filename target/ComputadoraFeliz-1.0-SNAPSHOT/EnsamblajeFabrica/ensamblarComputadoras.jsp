<%-- 
    Document   : listarComputadoras
    Created on : 8/03/2025, 11:30:18
    Author     : Ana
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Computadoras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="../Header.jsp" %>
    <div class="container mt-5">
        <%-- Mostrar mensaje de confirmación --%>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <h2>Lista de Computadoras</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nombre de la Computadora</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="computadora" items="${computadoras}">
                    <tr>
                        <td>${computadora.getNombre()}</td>
                        <td>${computadora.getPrecioVenta()}</td>
                        <td>
                           
                            <form action="/ComputadoraFeliz/ListarComputadorasServlet" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="ensamblar">
                                <input type="hidden" name="id" value="${computadora.getIdComputadora()}">
                                <button type="submit" class="btn btn-primary btn-sm">Ensamblar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>