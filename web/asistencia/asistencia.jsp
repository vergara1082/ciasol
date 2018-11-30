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
