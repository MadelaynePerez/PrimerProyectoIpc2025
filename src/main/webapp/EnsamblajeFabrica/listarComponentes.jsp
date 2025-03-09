<%-- 
    Document   : listarComponentes
    Created on : 8/03/2025, 11:30:18
    Author     : Ana
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Componentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="../Header.jsp" %>
    <div class="container mt-5">
        <%@ include file="../mensajes.jsp" %>
        <%-- Mostrar mensaje de confirmación --%>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <h2>Lista de Componentes</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nombre del Componente</th>
                    <th>Stock</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="componente" items="${componentes}">
                    <tr>
                        <td>${componente.getNombre()}</td>
                        <td>${componente.getCosto()}</td>
                        <td>${componente.getCantidadStock()}</td>
                        
                        <td>
                            
                            <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#modificarComponenteModal${componente.getIdComponente()}">
                                Modificar
                            </button>

                         
                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#eliminarComponenteModal${componente.getIdComponente()}">
                                Eliminar
                            </button>
                        </td>
                    </tr>

                    
                    <div class="modal fade" id="modificarComponenteModal${componente.getIdComponente()}" tabindex="-1" aria-labelledby="modificarComponenteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modificarComponenteModalLabel">Modificar Componente: ${componente.getNombre()}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/ComputadoraFeliz/ListarComponentesServlet" method="post">
                                        <input type="hidden" name="action" value="modificar">
                                        <input type="hidden" name="id" value="${componente.getIdComponente()}">
                                        <div class="mb-3">
                                            <label for="nombre${componente.getIdComponente()}" class="form-label">Nombre:</label>
                                            <input type="text" class="form-control" id="nombre${componente.getIdComponente()}" name="nombre" value="${componente.getNombre()}" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="stock${componente.getIdComponente()}" class="form-label">Stock:</label>
                                            <input type="number" class="form-control" id="stock${componente.getIdComponente()}" name="stock" value="${componente.getCantidadStock()}" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="precio${componente.getIdComponente()}" class="form-label">Precio:</label>
                                            <input type="number" step="0.01" class="form-control" id="precio${componente.getIdComponente()}" name="precio" value="${componente.getCosto()}" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                   
                    <div class="modal fade" id="eliminarComponenteModal${componente.getIdComponente()}" tabindex="-1" aria-labelledby="eliminarComponenteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="eliminarComponenteModalLabel">Eliminar Componente: ${componente.getNombre()}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>¿Estás seguro de que deseas eliminar este componente?</p>
                                    <form action="/ComputadoraFeliz/ListarComponentesServlet" method="post">
                                        <input type="hidden" name="action" value="eliminar">
                                        <input type="hidden" name="id" value="${componente.getIdComponente()}">
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
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