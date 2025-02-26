<!-- registro_componentes.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Componentes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; margin: auto; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Registro de Componentes</h2>
    <form>
        <label>Tipo de Componente:</label>
        <input type="text" required>
        <label>Costo:</label>
        <input type="number" required>
        <label>Cantidad en Stock:</label>
        <input type="number" required>
        <button type="submit">Guardar</button>
    </form>
</body>
</html>
