<%-- 
    Document   : ConsultaComputadorasDisponibles
    Created on : 24 feb 2025, 17:13:35
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Computadoras Disponibles</title>
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
        <h2>Consulta de Computadoras Disponibles</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Modelo</th>
                    <th>Marca</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se mostrarían los resultados de la consulta -->
                <tr>
                    <td>1</td>
                    <td>Modelo A</td>
                    <td>Marca X</td>
                    <td>$1000</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Modelo B</td>
                    <td>Marca Y</td>
                    <td>$1200</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
