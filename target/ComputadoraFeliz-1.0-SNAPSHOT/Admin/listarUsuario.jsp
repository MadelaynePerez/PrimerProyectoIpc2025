<%-- 
    Document   : listarUsuario
    Created on : 8/03/2025, 11:30:18
    Author     : Ana
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../Header.jsp" %>
        <div class="container mt-5">
            <h2>Lista de Usuarios</h2>
            <%@ include file="../mensajes.jsp" %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nombre del Usuario</th>
                        <th>Nombre del Rol</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.getNombreUsuario()}</td>
                            <td>${usuario.getRol().getNombreRol()}</td>
                            <td>

                                <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#cambiarRolModal${usuario.getIdUsuario()}">
                                    Cambiar Rol
                                </button>

                                <form action="/ComputadoraFeliz/ListarUsuarioServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="inactivar">
                                    <input type="hidden" name="usuario" value="${usuario.getIdUsuario()}">
                                    <button type="submit" class="btn btn-danger btn-sm">Inactivar</button>
                                </form>
                            </td>
                        </tr>

                    <div class="modal fade" id="cambiarRolModal${usuario.getIdUsuario()}" tabindex="-1" aria-labelledby="cambiarRolModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="cambiarRolModalLabel">Cambiar Rol de ${usuario.getNombreUsuario()}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/ComputadoraFeliz/ListarUsuarioServlet" method="post">
                                        <input type="hidden" name="action" value="cambiarRol">
                                        <input type="hidden" name="usuario" value="${usuario.getIdUsuario()}">
                                        <div class="mb-3">
                                            <label for="nuevoRol${usuario.getIdUsuario()}" class="form-label">Seleccione un nuevo rol:</label>
                                            <select class="form-select" id="nuevoRol${usuario.getIdUsuario()}" name="nuevoRol">
                                                <option value="1">Fabrica</option>
                                                <option value="2">Punto de venta</option>
                                                <option value="3">Financiero</option>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>