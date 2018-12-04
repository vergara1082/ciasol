<%-- 
    Document   : newjsp
    Created on : 1/12/2018, 04:11:05 PM
    Author     : jcarreno
--%>

<%@page import="com.cia.persistencia.CiaCursos"%>
<%@page import="java.util.List"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.cia.persistencia.CiaInfracciones"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.cia.db.Conexion"%>
<%@page import="com.cia.db.Consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Consultas consultas = new Consultas();
            Conexion c = new Conexion();
            c.conectar();
//        File excel = new File(request.getRealPath("") + "/data");
            File excel = new File("C:/Archivos/");
            if (!excel.exists()) {
                excel.mkdirs();
            }
            List<CiaCursos> lista = (List<CiaCursos>) session.getAttribute("lista");

//        excel = new File(request.getRealPath("/") + "data/ReportePersuasivoByObli.xls");
            excel = new File(excel.getAbsolutePath() + "/Reporte.xls");
            try {
                FileWriter fw = new FileWriter(excel);
                String header = "N° Documento" + ";" + "Nombres" + ";" + "N° comparendo";
                fw.write(header);
                for (int i = 0; i < lista.size(); i++) {
                    fw.write("\r\n");
                    CiaInfracciones ci = consultas.infraccionesByPer(c.getCon(), lista.get(i).getCiaPersonas().getPerId().intValue());
                    String nombres = lista.get(i).getCiaPersonas().getPerNombres() + " " + lista.get(i).getCiaPersonas().getPerApellidos();
                    String row = lista.get(i).getCiaPersonas().getPerDocumento() + ";" + nombres + ";" + (ci == null ? "N/A" : ci.getInfNumero());
                    fw.write(row);
                }
                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            FileInputStream fis = new FileInputStream(excel);
            byte[] bytes = new byte[1000];
            int read;
            String fileName = excel.getName();
            String contentType = "application/vnd.ms-excel";
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            try {
                ServletOutputStream stream = response.getOutputStream();
                while ((read = fis.read(bytes)) != -1) {
                    stream.write(bytes, 0, read);
                }
                stream.flush();
            } catch (Exception ex) {
            }


        %>
    </body>
</html>
