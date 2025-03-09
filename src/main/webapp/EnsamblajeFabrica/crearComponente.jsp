<%-- 
    Document   : crearComponente
    Created on : 8/03/2025, 00:47:19
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crear Componente</title>
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
    .form-container input[type="number"],
    .form-container input[type="submit"] {
      width: 100%;
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .form-container input[type="submit"] {
      background-color: #28a745;
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
    <h2>Crear Componente</h2>
    <form action="/ComputadoraFeliz/CrearComponenteServlet" method="post">
      <!-- Campo para el nombre del componente -->
      <label for="nombre">Nombre del Componente:</label>
      <input type="text" id="nombre" name="nombre" required>

      <!-- Campo para el stock -->
      <label for="stock">Stock:</label>
      <input type="number" id="stock" name="stock" min="0" required>

      <!-- Campo para el precio -->
      <label for="precio">Precio:</label>
      <input type="number" id="precio" name="precio" min="0" step="0.01" required>

      <!-- Botón para enviar el formulario -->
      <input type="submit" value="Crear Componente">
    </form>
    <%@ include file="../mensajes.jsp" %>
  </div>
</body>
</html>