<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio de Sesión</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .login-container {
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 300px;
                text-align: center;
            }
            h2 {
                margin-bottom: 20px;
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
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            .form-group button:hover {
                background-color: #0056b3;
            }
            .error-message {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Inicio de Sesión</h2>
            <form action="/ComputadoraFeliz/LoginServlet" method="post">
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <button type="submit">Ingresar</button>
                </div>
                <% if (request.getParameter("error") != null) { %>
                <div class="error-message">Usuario o contraseña incorrectos.</div>
                <% }%>
                <%@ include file="../mensajes.jsp" %>
            </form>
            <a href="/ComputadoraFeliz/index.html" class="back-btn">Regresar</a>
        </div>
    </body>
</html>