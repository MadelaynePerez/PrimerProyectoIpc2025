<%-- 
    Document   : ConsultaComponentes.jsp
    Created on : 23 feb 2025, 19:11:36
    Author     : DELL
--%>

<!-- consulta_componentes.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consulta de Componentes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f4f4f4; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Consulta de Componentes</h2>
    <table id="componentesTable">
        <thead>
            <tr>
                <th onclick="ordenarTabla(0)">ID</th>
                <th onclick="ordenarTabla(1)">Tipo</th>
                <th onclick="ordenarTabla(2)">Cantidad</th>
            </tr>
        </thead>
        <tbody>
            <%-- Aquí se iterarán los registros desde la base de datos --%>
        </tbody>
    </table>
    <script>
        function ordenarTabla(columna) {
            // Implementación de ordenamiento
        }
    </script>
</body>
</html>

