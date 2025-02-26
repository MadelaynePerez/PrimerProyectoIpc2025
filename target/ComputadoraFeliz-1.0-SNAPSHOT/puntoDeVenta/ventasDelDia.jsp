<%-- 
    Document   : ventasDelDia
    Created on : 24 feb 2025, 17:14:37
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ventas del Día</title>
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
        <h2>Ventas del Día</h2>
        <table>
            <thead>
                <tr>
                    <th>Número de Factura</th>
                    <th>Cliente</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se mostrarían los resultados de la consulta -->
                <tr>
                    <td>001</td>
                    <td>Cliente A</td>
                    <td>$1000</td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>Cliente B</td>
                    <td>$1500</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
