<%-- 
    Document   : registroComponentes.jsp
    Created on : 23 feb 2025, 1:01:51
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Componentes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Registro de Componentes</h2>
        <form action="RegistroComponentesServlet" method="post">
            <label for="tipo">Tipo de Componente:</label>
            <input type="text" id="tipo" name="tipo" required>

            <label for="costo">Costo:</label>
            <input type="number" id="costo" name="costo" step="0.01" required>

            <label for="cantidad">Cantidad en Stock:</label>
            <input type="number" id="cantidad" name="cantidad" required>

            <button type="submit">Registrar</button>
        </form>
        <%-- Mostrar mensajes de éxito o error --%>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <p style="color: green;"><%= mensaje %></p>
        <%
            }
        %>
    </div>
</body>
</html>
