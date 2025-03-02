<%-- 
    Document   : CargaDatos.jsp
    Created on : 24 feb 2025, 17:29:25
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carga de Datos</title>
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
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group button {
            padding: 10px 15px;
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #218838;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Carga de Datos</h2>
        <form action="/ComputadoraFeliz/CargaArchivoServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="archivo">Seleccione el archivo de datos:</label>
                <input type="file" id="archivo" name="archivo" required>
            </div>
            <div class="form-group">
                <button type="submit">Cargar Datos</button>
            </div>
            <% if (request.getParameter("error") != null) { %>
                <div class="error-message">Error al cargar el archivo. Verifique el formato.</div>
            <% } %>
        </form>
    </div>
</body>
</html>