<%-- 
    Document   : inicio
    Created on : 30/11/2018, 05:04:27 PM
    Author     : jcarreno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="https://getbootstrap.com/favicon.ico">
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap-reboot.css" rel="stylesheet"/>
        <title>Cia Viral</title>
        <script
            src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
        <!-- Bootstrap core CSS -->
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap-grid.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap-grid.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <script type="text/javascript">
            function guardar() {
                var nombre = $('#txtNombres').val();
                var Doc = $('#txtDocumento').val();
                var tipDoc = $('#cmbTipoDocumento').val();
                var Apellido = $('#txtApellidos').val();
                var factura = $('#txtInfFactura').val();
                var code = $('#txtInfCodigo').val();
                var numero = $('#txtInfNumero').val();
                var curso = $('#txtCurTipo').val();
                if(tipDoc.value === 0  )){
                    alert("Escoja tipo documento");
                }
                if(nombre.value === 0  )){
                    alert("Digite nombre(s) de la persona");
                }

                $.post('<%=request.getContextPath()%>/addCurso', {
                    txtNombres: nombre
                    , txtDocumento: Doc
                    , txtApellidos: Apellido
                    , cmbTipoDocumento: tipDoc
                    , txtInfFactura: factura
                    , txtInfCodigo: code
                    , txtInfNumero: numero
                    , txtCurTipo: curso

                }).success(function () {
                    $("#res")
                 };



                /*     $.ajax({
                 method: "POST",
                 url: "/addCurso",
                 data: {username: "username", password: "password"}
                 })
                 .success(function () {
                 //do success stuff
                 })
                 .error(function () {
                 console.log("error");
                 });
                 });*/


            }
        </script>

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
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/singIn">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="<%=request.getContextPath()%>/asingInf">Asignar Curso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#">Disabled</a>
                        </li>
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </header>

        <!-- Begin page content -->
        <br/><br/><br/><br/>
        <div class="container">
            <div class="col-xs-4">
                <label for ="cmbTipoDocumento">Tipo Documento</label>
                <select id="cmbTipoDocumento" name="cmbTipoDocumento" class="form-control">
                    <option value="0" selected=""> select tipo Documento </option>
                    <option value="1" > Cedula</option>
                    <option value="2" > Nit </option>
                </select>
            </div>
            <div class="col-xs-4">
                <input type="text" name="txtDocumento" id="txtDocumento" placeholder="Documento" class="form-control" />
            </div>
            <div class="col-xs-4">
                <input type="text" name="txtNombres" id="txtNombres" placeholder="Nombres" class="form-control" />
            </div>
            <br/>
            <div class="col-xs-4">
                <input type="text" name="txtApellidos" id="txtApellidos" placeholder="Apellidos" class="form-control" />
            </div>
            <div class="col-xs-4">
                <input type="text" name="txtInfFactura" id="txtInfFactura" placeholder="Factura" class="form-control" />
            </div>
            <div class="col-xs-4">
                <input type="text" name="txtInfCodigo" id="txtInfCodigo" placeholder="Codigo Infraccion" class="form-control" />
            </div>
            <div class="col-xs-4">
                <input type="text" name="txtInfNumero" id="txtInfNumero" placeholder="Numero Comparendo" class="form-control" />
            </div>
            

            <div class="col-xs-4">
                <label for ="txtCurTipo">Tipo Documento</label>
                <select id="txtCurTipo" name="txtCurTipo" class="form-control">
                    <option value="1" selected=""> 08:00 - 09:30 </option>
                    <option value="2" > 10:00 - 11:30</option>
                    <option value="3" > 01:30 - 03:00 </option>
                </select>
            </div>
            <div class="col-xs-4">
                <input type="button" onclick="guardar()" id="Guardar" value="Guardar" name="Guardar" />
            </div>
        </div>
        <div id="res">
            
        </div>


        <footer class="footer">
            <div class="container">
                <span class="text-muted">Place sticky footer content here.</span>
            </div>
        </footer>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->

    </body>
</html>
