<%-- 
    Document   : menuPrincipal.jsp
    Created on : 24 feb 2025, 17:29:56
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Principal</title>
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
        .menu {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .menu a {
            text-decoration: none;
            color: #007bff;
            font-size: 18px;
        }
        .menu a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Menú Principal</h2>
        <div class="menu">
            <%
                String rol = (String) session.getAttribute("rol");
                if (rol != null) {
                    if (rol.equals("1")) { // Rol de Ensamblaje
            %>
                        <a href="ensamblaje/registroComponentes.jsp">Registro de Componentes</a>
                        <a href="ensamblaje/ensamblarComputadora.jsp">Ensamblar Computadora</a>
                        <a href="ensamblaje/consultarComponentes.jsp">Consultar Componentes</a>
            <%
                    } else if (rol.equals("2")) { // Rol de Ventas
            %>
                        <a href="ventas/registroVentas.jsp">Registro de Ventas</a>
                        <a href="ventas/registroDevoluciones.jsp">Registro de Devoluciones</a>
                        <a href="ventas/consultaCompras.jsp">Consultar Compras</a>
                        <a href="ventas/consultaDevoluciones.jsp">Consultar Devoluciones</a>
            <%
                    } else if (rol.equals("3")) { // Rol de Administrador
            %>
                        <a href="admin/reporteVentas.jsp">Reporte de Ventas</a>
                        <a href="admin/reporteDevoluciones.jsp">Reporte de Devoluciones</a>
                        <a href="admin/reporteGanancias.jsp">Reporte de Ganancias</a>
                        <a href="admin/crearUsuario.jsp">Crear Usuario</a>
                        <a href="admin/cargaDatos.jsp">Carga de Datos</a>
            <%
                    }
                }
            %>
        </div>
    </div>
</body>
</html>