<%-- 
    Document   : ConsultaComputadoraEnsamblada.jsp
    Created on : 24 feb 2025, 17:18:16
    Author     : DELL
--%>

<!-- consulta_computadoras.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consulta de Computadoras Ensambladas</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f4f4f4; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Consulta de Computadoras Ensambladas</h2>
    <table id="computadorasTable">
        <thead>
            <tr>
                <th onclick="ordenarTabla(0)">ID</th>
                <th onclick="ordenarTabla(1)">Modelo</th>
                <th onclick="ordenarTabla(2)">Fecha de Ensamblaje</th>
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
