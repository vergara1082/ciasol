<%-- 
    Document   : agendamientoDelDia
    Created on : 30/11/2018, 03:16:36 PM
    Author     : emadrid
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/paginas/js/agendamientoDelDia.js" type="text/javascript"></script>
        <title>Agendamiento del Dia.</title>
    </head>
    <body>

        <header>
            <!-- Fixed navbar -->
            <nav class="navbar navbar-expand-md navbar-dark
                 fixed-top bg-dark">
                <a class="navbar-brand" href="#">Cia Vial</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="<%=request.getContextPath()%>/singIn">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/asingInf">Asignar Curso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/paginas/agendamientoDelDia.jsp">Agendar Curso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/asistencia/asistencia.jsp">Registrar Asistencia</a>
                        </li>
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </header>
        <br/>
        <h3>Consultar Curso del Día</h3>
        <form id="agendamiento" action="javascript:consultar()">
            <h3>Consultar Curso del Día</h3>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Seleccione un curso.</label>
                        <select id="selectHorario" name="selectHorario" class="form-control col-lg-12" >
                            <option selected="true" >Seleccione.</option>
                            <option value="1" >8:00 - 9:30</option>
                            <option value="2" >10:00 - 11:30</option>
                            <option value="3" >1:30 - 3:00</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2">
                    <label>&nbsp;</label>
                    <input id="submit" type="submit" value="Consultar" class="btn btn-outline-success form-control" />
                </div>
            </div>
            <div class="col-md-12">
                <div class="container">
                    <div id="tabla">

                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
