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
        line-height: 1.6; 
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
        letter-spacing: 1px; 
    }
    .menu {
        display: flex;
        justify-content: space-around;
        margin-top: 20px;
        background-color: #000080; 
        padding: 10px;
        border-radius: 5px;
    }
    .menu a {
        text-decoration: none;
        color: #ffffff; 
        font-size: 18px;
        margin: 0 15px;
        letter-spacing: 1px;
    }
    .menu a:hover {
        color: #dcdcdc; 
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
        letter-spacing: 0.5px;
    }
    .dropdown-content a:hover {
        background-color: #555;
    }
    .dropdown:hover .dropdown-content {
        display: block;
    }
  
    .logout-btn {
        margin-top: 20px;
        display: block;
        width: 100%;
        text-align: center;
    }
    .logout-btn button {
        background-color: #d9534f; 
        color: white;
        border: none;
        padding: 10px 20px;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
    }
    .logout-btn button:hover {
        background-color: #c9302c; 
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
         



            <a href="/ComputadoraFeliz/ListarComputadorasServlet">Ensamblar Computadora</a>
            <a href="/ComputadoraFeliz/ListarComputadorasEnsambladasServlet">Consultar Computadoras Ensambladas</a>
            <a href="/ComputadoraFeliz/ListarComponentesServlet">Consultar Componentes</a>
            <a href="/ComputadoraFeliz/EnsamblajeFabrica/crearComponente.jsp">Crear Componente/pieza</a>
            <a href="/ComputadoraFeliz/AlertaStockServlet">Alerta Stock</a>
            <div class="logout-btn">
                <form action="/ComputadoraFeliz/VistasGenerales/loginVista.jsp" method="post">
                    <button type="submit">Cerrar Sesión</button>
                </form>
            </div>
            <%
            } else if (rol.equals("2")) { // Rol de Ventas
            %>
            <a href="/ComputadoraFeliz/BuscarClienteServlet">Registro de Ventas</a>
            <a href="/ComputadoraFeliz/puntoDeVenta/RegistroDevolucion.jsp">Registro de Devoluciones</a>
            <a href="ventas/consultaCompras.jsp">Consultar Compras</a>
            <a href="/ComputadoraFeliz/puntoDeVenta/ConsultaDeFacturas.jsp">Consultar Facturas</a>
            <a href="ventas/consultaDevoluciones.jsp">Consultar Devoluciones</a>
            <div class="logout-btn">
                <form action="/ComputadoraFeliz/VistasGenerales/loginVista.jsp" method="post">
                    <button type="submit">Cerrar Sesión</button>
                </form>
            </div>
            <%
            } else if (rol.equals("3")) { // Rol de Administrador
            %>
            <div class="dropdown">
                <a href="#">Reportes</a>
                <div class="dropdown-content">
                    <a href="/ComputadoraFeliz/ReporteVentaServlet">Reporte de Ventas</a>
                    <a href="/ComputadoraFeliz/ReporteDevolucionServlet">Reporte de Devoluciones</a>
                    <a href="/ComputadoraFeliz/ReporteGananciaServlet">Reporte de Ganancias</a>
                    <a href="/ComputadoraFeliz/ReporteUsuarioVentasServlet">Usuario con Más Ventas</a>
                    <a href="/ComputadoraFeliz/ReporteUsuarioGananciaServlet">Usuario con Más Ganancias</a>
                    <a href="/ComputadoraFeliz/ReporteComputadoraMasVendidaServlet">Computadora Más Vendida</a>
                    <a href="admin/reporteComputadoraMenosVendida.jsp">Computadora Menos Vendida</a>
                </div>
            </div>
            <a href="/ComputadoraFeliz/Admin/crearUsuario.jsp">Crear Usuario</a>
            <a href="/ComputadoraFeliz/ListarUsuarioServlet">Listar Usuario</a>
            <a href="/ComputadoraFeliz/Admin/crearComputadora.jsp">Crear Computadoras</a>
            <a href="/ComputadoraFeliz/VistasGenerales/CargaDatos.jsp">Carga de Datos</a>
            <div class="logout-btn">
                <form action="/ComputadoraFeliz/VistasGenerales/loginVista.jsp" method="post">
                    <button type="submit">Cerrar Sesión</button>
                </form>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
