<!-- ensamblaje_computadoras.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ensamblaje de Computadoras</title>
    <style>
        title {color: activeborder}
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; margin: auto; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Ensamblaje de Computadoras</h2>
    <form>
        <label>Tipo de Computadora:</label>
        <select required>
            <option value="">Seleccione...</option>
        </select>
        <label>Selección de Componentes:</label>
        <select required>
            <option value="">Seleccione...</option>
        </select>
        <label>Cantidad de Componentes:</label>
        <input type="number" required>
        <label>Fecha de Ensamblaje:</label>
        <input type="date" required>
        <button type="submit">Ensamblar</button>
    </form>
</body>
</html>
