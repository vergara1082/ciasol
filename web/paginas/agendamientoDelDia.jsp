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
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Agendamiento del Dia.</title>
    </head>
    <body>
        <h3>Consultar Curso del Día</h3>
        <form id="agendamiento" method="post" action="<%=request.getContextPath()%>/AgendamientoDelDia">
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
                    <input type="submit" value="Consultar" class="btn btn-outline-success" />
                </div>
            </div>
            <div class="col-md-12">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre Persona</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>Elkin Madrid</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </form>
    </body>
</html>
