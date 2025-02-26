<%-- 
    Document   : RegistroDevolucion.jsp
    Created on : 24 feb 2025, 17:11:08
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Devoluciones</title>
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
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group button {
            padding: 10px 15px;
            background-color: #dc3545;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registro de Devoluciones</h2>
        <form action="procesarDevolucion.jsp" method="post">
            <div class="form-group">
                <label for="factura">Número de Factura:</label>
                <input type="text" id="factura" name="factura" required>
            </div>
            <div class="form-group">
                <label for="fechaCompra">Fecha de Compra:</label>
                <input type="date" id="fechaCompra" name="fechaCompra" required>
            </div>
            <div class="form-group">
                <label for="motivo">Motivo de Devolución:</label>
                <textarea id="motivo" name="motivo" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <button type="submit">Registrar Devolución</button>
            </div>
        </form>
    </div>
</body>
</html>
