/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

import com.cia.db.Actulizaciones;
import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.db.Inserciones;
import com.cia.persistencia.CiaCursos;
import com.cia.utils.Cifrado.Reportes;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author root
 */
@WebServlet(name = "reasignacionCur", urlPatterns = {"/reasignacionCur"})
public class reasignacionCurso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        if (request.getParameter("action") == null) {
            Conexion conexion = new Conexion();
            conexion.conectar();
            Consultas consultas = new Consultas();
            List<CiaCursos> listaDeCursos = consultas.allCurso(conexion.getCon());
            request.setAttribute("listaCurso", listaDeCursos);
            request.getRequestDispatcher("paginas/reasignacionCursoinf.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("consultarPersonasCursos")) {
            consultarPersonaByCurso(request, response);
        } else if (request.getParameter("action").equals("ReasigarCurso")) {
            procesarCurso(request, response);

        }
    }

    protected void consultarPersonaByCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("aplication/json");
        String documento = (request.getParameter("documento"));
        String tpd = (request.getParameter("tipoDoc"));
        PrintWriter out = response.getWriter();
        Conexion conexion = new Conexion();
        conexion.conectar();
        Consultas consultas = new Consultas();
        List<HashMap> listaDetalleCurso = consultas.getlistDetalleCursoByPersonaReasignacion(conexion.getCon(), Long.valueOf(tpd), documento);
        if (listaDetalleCurso.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (HashMap hashMap : listaDetalleCurso) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dcr_id", hashMap.get("dcr_id"));
                jSONObject.put("per_nombres", hashMap.get("per_nombres"));
                jSONObject.put("per_documento", hashMap.get("per_documento"));
                jSONObject.put("per_apellidos", hashMap.get("per_apellidos"));
                jSONObject.put("inf_numero", hashMap.get("inf_numero"));
                jSONObject.put("dcr_fecha", hashMap.get("dcr_fecha"));
                jSONObject.put("inf_codigo", hashMap.get("inf_codigo"));

                jsonArray.put(jSONObject);

            }

            out.print(jsonArray);
        }

    }

    protected void procesarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        String data = request.getParameter("data");
        JSONArray jSONArray = new JSONArray(data);
        Conexion con = new Conexion();
        Actulizaciones update = new Actulizaciones();
        Inserciones in = new Inserciones();
        con.conectar();

        long curso = Long.valueOf(request.getParameter("tipo_curso"));
        String fecha = request.getParameter("fecha_curso");

        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            System.out.println(jSONObject.get("numero_documento")
                    + " " + jSONObject.getString("id_detalle_curso") + " " + jSONObject.getString("nombres_persona")
                    + " " + jSONObject.getString("apellido_persona")
            );

            long iddecur = Long.valueOf(jSONObject.getString("id_detalle_curso"));

            update.updateDetalleCurso(con.getCon(), fecha, curso, iddecur);

        }
        con.getCon().commit();
        con.getCon().close();
        //masivoEmbargos(main, main.getParams(), main.getRootPdf(), "Certificados", response);
    }

    public void masivoEmbargos(Reportes resport, Map<String, Object> params, String jasperPath, String fileName, HttpServletResponse response) throws JRException {
        BufferedOutputStream output = null;

        response.setHeader("Content-Type", "application/pdf");
        //response.setHeader("Content-Length", String.valueOf(bts.length));
        response.setHeader("Content-Disposition", "inline;attachment; filename=\"" + fileName + "\"");
        try {
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            JasperPrint print;

            List<JasperPrint> prints = new ArrayList<>();
            List<ExporterInputItem> items = new ArrayList<>();
            Conexion con = new Conexion();
            con.conectar();

            for (Reportes bt : resport.getListReporte()) {
                print = JasperFillManager.fillReport((jasperPath), bt.getParams(), con.getCon());
                File fl = new File("D:/Archivos");
                if (!fl.exists()) {
                    fl.mkdirs();
                }
                JasperExportManager.exportReportToPdfFile(print, fl.getPath() + File.separator + "Certificados " + bt.getParams().get("numero_documento") + ".pdf");

                ExporterInputItem input = new SimpleExporterInputItem(print);
                items.add(input);
            }
            con.getCon().close();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(items));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            exporter.exportReport();

        } catch (Exception ex) {
            ex.printStackTrace();
        }//if

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(reasignacionCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(reasignacionCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
