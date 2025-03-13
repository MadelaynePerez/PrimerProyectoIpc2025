<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Componentes en Stock</title>
    <style>
       
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #343a40;
            margin-top: 20px;
            font-size: 2.5em;
        }

       
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

       
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

     
        .alerta {
            color: #dc3545;
            font-weight: bold;
        }

        .stock-suficiente {
            color: #28a745;
            font-weight: bold;
        }

     
        .stock-suficiente {
            color: #28a745;
            font-weight: bold;
        }

      
        header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="../Header.jsp" %>
    <div class="container">
        <h1>Componentes en Stock</h1>
        <table>
            <thead>
                <tr>
                    <th>Nombre del Componente</th>
                    <th>Cantidad en Stock</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="componente" items="${componentes}">
                    <tr>
                        <td>${componente.getNombre()}</td>
                        <td>${componente.getCantidadStock()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${componente.getCantidadStock() == 0}">
                                    <span class="alerta">¡Alerta! Sin stock.</span>
                                </c:when>
                                <c:when test="${componente.getCantidadStock() > 0 && componente.getCantidadStock() < 5}">
                                    <span class="alerta">¡Alerta! Stock a punto de agotarse.</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="stock-suficiente">Stock suficiente</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>