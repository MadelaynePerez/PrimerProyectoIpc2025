<%-- 
    Document   : ConsultaDeFacturas.jsp
    Created on : 24 feb 2025, 17:14:15
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Facturas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
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
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Consulta de Facturas</h2>
        <form action="consultarFactura.jsp" method="post">
            <div class="form-group">
                <label for="numeroFactura">Número de Factura:</label>
                <input type="text" id="numeroFactura" name="numeroFactura" required>
            </div>
            <div class="form-group">
                <button type="submit">Consultar</button>
            </div>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Número de Factura</th>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se mostrarían los resultados de la consulta -->
                <tr>
                    <td>001</td>
                    <td>2023-10-01</td>
                    <td>Cliente A</td>
                    <td>$1000</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
