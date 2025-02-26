<%-- 
    Document   : RegistroVentas.jsp
    Created on : 24 feb 2025, 16:49:42
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Ventas</title>
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
        .form-group input, .form-group select {
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
    </style>
</head>
<body>
    <div class="container">
        <h2>Registro de Ventas</h2>
        <form action="procesarVenta.jsp" method="post">
            <div class="form-group">
                <label for="computadora">Selección de Computadoras:</label>
                <select id="computadora" name="computadora" required>
                    <option value="1">Computadora 1</option>
                    <option value="2">Computadora 2</option>
                    <!-- Agrega más opciones según sea necesario -->
                </select>
            </div>
            <div class="form-group">
                <label for="nit">NIT del Cliente:</label>
                <input type="text" id="nit" name="nit" required>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre del Cliente:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" required>
            </div>
            <div class="form-group">
                <button type="submit">Generar Factura en PDF</button>
            </div>
        </form>
    </div>
</body>
</html>