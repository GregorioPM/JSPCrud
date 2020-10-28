<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aplicacion Gestion de Equipos</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>
<body>

 <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #03a9f4">
                    <div>
                        <a href="#" class="navbar-brand"> Gestión de Usuarios </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Equipos</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">

                        <c:if test="${equipo != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${equipo == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${equipo != null}">
                                    Editar Equipo
                                </c:if>
                                <c:if test="${equipo == null}">
                                    Agregar Nuevo Equipo
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${equipo != null}">
                            <input type="hidden" name="id" value="<c:out value='${equipo.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Nombre de Equipo</label> <input type="text" value="<c:out value='${equipo.nombre}' />" class="form-control" name="nombre" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Pais del Equipo</label> <input type="text" value="<c:out value='${equipo.pais}' />" class="form-control" name="pais">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Cantidad De Trofeos</label> <input type="text" value="<c:out value='${equipo.trofeos}' />" class="form-control" name="trofeos">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>

</body>
</html>