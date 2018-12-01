<%-- 
    Document   : asistencia
    Created on : 30/11/2018, 11:14:05 AM
    Author     : Desarrollo1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cia.persistencia.CiaCursos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
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

        <div id="app">
            <form method="post" action="">
                {{message}}
                <select>
                    <%
                        List<CiaCursos> ciaCursos = (List<CiaCursos>) request.getAttribute("listaCurso");
                        for (CiaCursos elem : ciaCursos) {
                    %> 
                    <option value="<%=elem.getCurId().toString()%>"><%= elem.getCiaHorarios().getHorTiempo()%> </option>
                    <%
                        }
                    %>
                </select>

                <input type="submit" value="consultar" class="btn btn-success" />

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Asistencias</th>
                            <th>nombres</th>
                            <th>Apellidos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>    
                            <td></td>
                            <td></td>
                        </tr>

                    </tbody>
                </table>
            </form>
        </div>

        <script>
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})
        </script>
    </body>
</html>
