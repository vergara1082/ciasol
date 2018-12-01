<%-- 
    Document   : reporte
    Created on : 1/12/2018, 01:55:35 PM
    Author     : Desarrollo1
--%>

<%@page import="net.sf.jasperreports.export.SimpleOutputStreamExporterOutput"%>
<%@page import="net.sf.jasperreports.export.SimpleExporterInput"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.export.SimpleExporterInputItem"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="java.io.File"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.export.ExporterInputItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="org.apache.jasper.Constants"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="com.cia.utils.Cifrado.Reportes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Reportes resport = (Reportes) session.getAttribute("reporte");
            BufferedOutputStream output = null;
            response.setHeader("Content-Type", "application/pdf");
            //response.setHeader("Content-Length", String.valueOf(bts.length));
            response.setHeader("Content-Disposition", "inline;attachment; filename=\"" + "Reporte" + "\"");
            try {
                output = new BufferedOutputStream(response.getOutputStream(), Constants.DEFAULT_BUFFER_SIZE);
                JasperPrint print;
                List<ExporterInputItem> items = new ArrayList<>();
                for (Reportes bt : resport.getListReporte()) {
                    print = JasperFillManager.fillReport(resport.getRootPdf(), bt.getParams());
                    File fl = new File("D:/Archivos");
                    if (!fl.exists()) {
                        fl.mkdirs();
                    }
                    JasperExportManager.exportReportToPdfFile(print, fl.getPath() + File.separator + "Certificados " + bt.getParams().get("numero_documento") + ".pdf");
                    ExporterInputItem input = new SimpleExporterInputItem(print);
                    items.add(input);
                } 
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(items));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
                exporter.exportReport();
            } catch (Exception ex) {
                ex.printStackTrace();
                ex.getCause();
            } 

        %>
    </body>
</html>
