<%@page import="com.cia.persistencia.CiaCursos"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Cia Sol</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bower_components/Ionicons/css/ionicons.min.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bower_components/jvectormap/jquery-jvectormap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/dist/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="<%=request.getContextPath()%>/resources/https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"/>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </head>
    <body class="hold-transition skin-green-light sidebar-mini fixed">
        <div class="wrapper" id="app">

            <header class="main-header">

                <!-- Logo -->
                <a href="<%=request.getContextPath()%>/resources/index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>A</b>LT</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Admin</b>LTE</span>
                </a>

                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
                            <!-- Notifications: style can be found in dropdown.less -->
                            <!-- Tasks: style can be found in dropdown.less -->
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="<%=request.getContextPath()%>/resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="<%=request.getContextPath()%>/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>
                                            Alexander Pierce - Web Developer
                                            <small>Member since Nov. 2012</small>
                                        </p>
                                    </li>
                                    <!-- Menu Body -->
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button 
                            <li>
                                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                            </li>-->
                        </ul>
                    </div>

                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<%=request.getContextPath()%>/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">Menú</li>
                        <li class="active treeview menu-open">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Menú</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="<%=request.getContextPath()%>/asingInf"><i class="fa fa-circle-o"></i>registro en Cursos</a></li>
                                <li class=""><a href="<%=request.getContextPath()%>/paginas/agendaDiaInf.jsp"><i class="fa fa-circle-o"></i> Agendar Curso</a></li>
                                <li class="active"><a href="<%=request.getContextPath()%>/asistencias"><i class="fa fa-circle-o"></i> Registro de Asistencia</a></li>
                                <li class=""><a href="<%=request.getContextPath()%>/historicoCert"><i class="fa fa-circle-o"></i> Historico Certificado</a></li>
                                <li class=""><a href="<%=request.getContextPath()%>/actualizar"><i class="fa fa-circle-o"></i>Actualizar </a></li>

                            </ul>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Registro Asistencias
                        <small>Version 0.0.1</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Actualizar Datos</li>
                    </ol>
                </section>

                <!-- Main content -->
                <!-- Main content -->
                <section class="content">
                    <form id="frmAsc">
                        <div class="row" >
                            <div v-if="renderDatos == false">
                                <div class="col-xs-2">
                                    <label for ="cmbTipoDocumento">Tipo Documento</label>
                                    <select id="cmbTipoDocumento" name="cmbTipoDocumento" class="form-control">
                                        <option value="0" selected=""> Seleccione ... </option>
                                        <option value="1" > Cédula</option>
                                        <option value="2" > Nit </option>
                                    </select>
                                </div>
                                <div class="col-xs-2">
                                    <label for ="txtDocumento">Documento</label>
                                    <input type="text" name="txtDocumento" id="txtDocumento" placeholder="Documento" class="form-control" />
                                </div>
                                <div class="col-xs-2">
                                    <label for >&nbsp;</label>
                                    <input type="button" class="btn btn-success form-control col-xs-6" @click="guardar" id="Guardar" value="Buscar"  />
                                </div> 
                            </div>
                            <table class="table table-striped table-hover" v-if="items.length > 0">
                                <thead>
                                    <tr>
                                        <th>Numero Documento</th>
                                        <th>Nombres</th>
                                        <th>Apellidos</th>
                                        <th>Numero infracion</th>
                                        <th>acción</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="i in items">
                                        <td>{{i.numero_documento}}</td>
                                        <td>{{i.nombres_persona}}</td>
                                        <td>{{i.apellido_persona}}</td>
                                        <td>{{i.numero_comparendo}}</td>
                                        <td>
                                            <a v-on:click="test(i)"  v-model="detalle_curso_persona_current">Editar!</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <section class="content" v-if="renderDatos == true">
                                <form id="frmAsc">
                                    <div class="row">
                                        <div class="col-xs-2">
                                            <label for ="cmbTipoDocumento">Tipo Documento</label>
                                            <select id="cmbTipoDocumento" name="cmbTipoDocumento" class="form-control">
                                                <option value="0" selected=""> Seleccione ... </option>
                                                <option value="1" > Cédula</option>
                                                <option value="2" > Nit </option>
                                            </select>
                                        </div>
                                        <div class="col-xs-2">
                                            <label for ="txtDocumento">Documento</label>
                                            <input type="text" name="txtDocumento" v-model="detalle_curso_persona_current.numero_documento" placeholder="Documento" class="form-control" />
                                        </div>
                                        <div class="col-xs-4">
                                            <label for ="txtNombres">Nombres</label>
                                            <input type="text" name="txtNombres"  v-model="detalle_curso_persona_current.nombres_persona" placeholder="Nombres" class="form-control" />
                                        </div>
                                        <div class="col-xs-4">
                                            <label for ="txtApellidos">Apellidos</label>
                                            <input type="text" name="txtApellidos" v-model="detalle_curso_persona_current.apellido_persona" placeholder="Apellidos" class="form-control" />
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for ="txtInfFactura">Número Factura</label>
                                            <input type="text" name="txtInfFactura" v-model="detalle_curso_persona_current.numero_factura" placeholder="Número Factura" class="form-control" />
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="form-group">
                                                <label>Fecha Factura</label>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <input type="text" class="form-control" placeholder="dd/MM/yyyy" name="txtFechaFac" v-model="detalle_curso_persona_current.fecha_factura"  data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
                                                    </div>
                                                    <!-- /.input group -->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for ="txtInfValorCurso">Valor Curso</label>
                                            <input type="text" name="txtInfValorCurso" id="txtInfValorCurso" placeholder="Valor Curso" class="form-control" v-model="detalle_curso_persona_current.valor_curso"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for ="txtDocumento">Codigo de Infraccion</label>
                                            <input type="text" name="txtInfCodigo" id="txtInfCodigo" placeholder="Codigo Infraccion" class="form-control" v-model="detalle_curso_persona_current.numero_infracion" />
                                        </div>
                                    </div>

                                    <br/>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for ="txtDocumento">Número Comparendo</label>
                                            <input type="text" name="txtInfNumero" id="txtInfNumero" placeholder="Número Comparendo" class="form-control"  v-model="detalle_curso_persona_current.numero_comparendo"/>
                                        </div>

                                        <div class="col-xs-3">
                                            <div class="form-group">
                                                <label>Fecha Comparendo</label>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <input type="text" placeholder="dd/MM/yyyy" class="form-control" name="txtFechaComp" id="txtFechaComp" v-model="detalle_curso_persona_current.fecha_comparendo"  data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
                                                    </div>
                                                    <!-- /.input group -->
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xs-3">
                                            <label for ="txtCurTipo">Horario</label>
                                            <select id="txtCurTipo" class="form-control">
                                                <option value="0" selected=""> Seleccione... </option>
                                                <option value="1" > 08:00 - 10:00 </option>
                                                <option value="2" > 10:00 - 12:00</option>
                                                <option value="3" > 01:30 - 03:30 </option>
                                            </select>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for >&nbsp;</label>
                                            <input type="button" class="btn btn-success form-control col-xs-6" @click="editarDatos" id="Guardar" value="Guardar" name="Guardar" />
                                        </div>
                                    </div>
                                    <div id="res">

                                    </div>
                                </form>
                            </section>
                        </div>
                    </form>
                    <script>
                        <%@include file="js/actualizar.js" %>
                    </script>
                </section>
                <!-- /.content -->
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 0.0.1
                </div>
                <strong>Copyright &copy; 2018-2019 CiaSol.</strong> All rights reserved.
            </footer>

        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="<%=request.getContextPath()%>/resources/dist/js/adminlte.min.js"></script>
        <!-- Sparkline -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
        <!-- jvectormap  -->
        <script src="<%=request.getContextPath()%>/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- SlimScroll -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- ChartJS -->
        <script src="<%=request.getContextPath()%>/resources/bower_components/chart.js/Chart.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="<%=request.getContextPath()%>/resources/dist/js/pages/dashboard2.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="<%=request.getContextPath()%>/resources/dist/js/demo.js"></script>
    </body>
</html>
