<%-- 
    Document   : CrearUsuario.jsp
    Created on : 5/03/2025, 17:06:27
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crear Usuario</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      min-height: 100vh;
    }
    header {
      width: 100%;
      background-color: #333;
      color: #fff;
      padding: 10px 0;
      text-align: center;
    }
    .form-container {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 300px;
      margin-top: 20px; /* Espacio entre el header y el formulario */
    }
    .form-container h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    .form-container label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    .form-container input[type="text"],
    .form-container input[type="password"],
    .form-container select {
      width: 100%;
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .form-container input[type="submit"] {
      width: 100%;
      padding: 10px;
      background-color: #28a745;
      border: none;
      border-radius: 4px;
      color: #fff;
      font-size: 16px;
      cursor: pointer;
    }
    .form-container input[type="submit"]:hover {
      background-color: #218838;
    }
  </style>
</head>
<body>
  <%@ include file="../Header.jsp" %>
  <div class="form-container">
    <h2>Crear Usuario</h2>
    <form action="/ComputadoraFeliz/CrearUsuarioServlet" method="post">
      
      <label for="usuario">Usuario:</label>
      <input type="text" id="usuario" name="usuario" required>

      <label for="password">Contraseña:</label>
      <input type="password" minlength="6" id="password" name="password" required>
      
      <label for="rol">Rol:</label>
      <select id="rol" name="rol" required>
        <option value="1">Fabrica</option>
        <option value="2">Punto de Venta</option>
        <option value="3">Financiero</option>
      </select>

      <!-- Botón para enviar el formulario -->
      <input type="submit" value="Crear Usuario">
      <%@ include file="../mensajes.jsp" %>
    </form>
    
  </div>
</body>
</html>
