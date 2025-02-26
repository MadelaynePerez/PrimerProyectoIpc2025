<%-- 
    Document   : consultDevolucionPorCliente.jsp
    Created on : 24 feb 2025, 17:12:51
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Devoluciones por Cliente</title>
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
        <h2>Consulta de Devoluciones por Cliente</h2>
        <form action="consultarDevoluciones.jsp" method="post">
            <div class="form-group">
                <label for="nitCliente">NIT del Cliente:</label>
                <input type="text" id="nitCliente" name="nitCliente" required>
            </div>
            <div class="form-group">
                <label for="fechaInicio">Fecha de Inicio:</label>
                <input type="date" id="fechaInicio" name="fechaInicio" required>
            </div>
            <div class="form-group">
                <label for="fechaFin">Fecha de Fin:</label>
                <input type="date" id="fechaFin" name="fechaFin" required>
            </div>
            <div class="form-group">
                <button type="submit">Consultar</button>
            </div>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Número de Factura</th>
                    <th>Fecha de Devolución</th>
                    <th>Motivo</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se mostrarían los resultados de la consulta -->
                <tr>
                    <td>001</td>
                    <td>2023-10-01</td>
                    <td>Producto defectuoso</td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>2023-10-05</td>
                    <td>Cambio de modelo</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>