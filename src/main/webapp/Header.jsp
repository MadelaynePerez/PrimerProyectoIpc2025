<%-- 
    Document   : Header
    Created on : 5/03/2025, 17:11:29
    Author     : Ana
--%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #333;
            min-width: 200px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }
        .dropdown-content a {
            color: #fff;
            padding: 12px 16px;
            display: block;
            text-align: left;
        }
        .dropdown-content a:hover {
            background-color: #555;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Men� Principal</h2>
        <div class="menu">
            <%
                String rol = (String) session.getAttribute("rol");
                if (rol != null) {
                    if (rol.equals("1")) { // Rol de Ensamblaje
            %>
                        <a href="ensamblaje/registroComponentes.jsp">Registro de Componentes</a>
                        <a href="/ComputadoraFeliz/ListarComputadorasServlet">Ensamblar Computadora</a>
                        <a href="ensamblaje/consultarComponentes.jsp">Consultar Componentes</a>
                        <a href="/ComputadoraFeliz/ListarComponentesServlet">Listar Componentes</a>
                        <a href="/ComputadoraFeliz/EnsamblajeFabrica/crearComponente.jsp">Crear Componente/pieza</a>
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
                        <div class="dropdown">
                            <a href="#">Reportes</a>
                            <div class="dropdown-content">
                                <a href="admin/reporteVentas.jsp">Reporte de Ventas</a>
                                <a href="admin/reporteDevoluciones.jsp">Reporte de Devoluciones</a>
                                <a href="admin/reporteGanancias.jsp">Reporte de Ganancias</a>
                                <a href="admin/reporteUsuarioMasVentas.jsp">Usuario con M�s Ventas</a>
                                <a href="admin/reporteUsuarioMasGanancias.jsp">Usuario con M�s Ganancias</a>
                                <a href="admin/reporteComputadoraMasVendida.jsp">Computadora M�s Vendida</a>
                                <a href="admin/reporteComputadoraMenosVendida.jsp">Computadora Menos Vendida</a>
                            </div>
                        </div>
                        <a href="/ComputadoraFeliz/Admin/crearUsuario.jsp">Crear Usuario</a>
                        <a href="/ComputadoraFeliz/ListarUsuarioServlet">Listar Usuario</a>
                        <a href="/ComputadoraFeliz/Admin/crearComputadora.jsp">Crear Computadoras</a>
                        <a href="admin/cargaDatos.jsp">Carga de Datos</a>
                        
            <%
                    }
                }
            %>
        </div>
    </div>
</body>
</html>