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
import com.cia.persistencia.CiaInfracciones;
import com.cia.persistencia.CiaPersonas;
import com.cia.utils.Cifrado.Reportes;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Desarrollo1
 */
@WebServlet(name = "Actualizar", urlPatterns = {"/actualizar"})
public class Actualizar extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("action") != null) {
                if (request.getParameter("action").equals("consultar")) {
                    buscarInfraciones(request, response);
                } else if (request.getParameter("action").equals("update")) {
                     response.setContentType("aplication/json");
                    String data = request.getParameter("tipo_curso");
                    JSONObject jSONObject = new JSONObject(data);
                    Conexion con = new Conexion();
                    Actulizaciones actulizaciones = new Actulizaciones();
                    Inserciones in = new Inserciones();
                    con.conectar();
                    String numeroDocumento = jSONObject.get("numero_documento").toString();
                    //String horario = jSONObject.get("horario").toString();
                    String tipoDocumento = jSONObject.get("tipo_documento").toString();
                    String nombre_persona = jSONObject.get("nombres_persona").toString();
                    String apellido_persona = jSONObject.get("apellido_persona").toString();
                    String numero_infracion = jSONObject.get("numero_infracion").toString();
                    String numero_factura = jSONObject.get("numero_factura").toString();
                    String fecha_factura = jSONObject.get("fecha_factura").toString();
                    String valor_curso = jSONObject.get("valor_curso").toString();
                    String numero_comparendo = jSONObject.get("numero_comparendo").toString();
                    String fecha_comparendo = jSONObject.get("fecha_comparendo").toString();
                    boolean personaupdate =actulizaciones.updatePersona(con.getCon(), nombre_persona, apellido_persona, tipoDocumento, numeroDocumento);
                    boolean infracionesupdate = actulizaciones.updateInfraciones(con.getCon(), numero_factura, fecha_factura, valor_curso, numero_infracion, numero_comparendo, fecha_comparendo);
                    if (infracionesupdate && personaupdate) {
                        out.print("datos actualizados correctamente");
                    }else{
                         out.print("error contacte al aministrador del sistema");
                    }
                }

                } else {
                    request.getRequestDispatcher("paginas/actualizarInf.jsp").forward(request, response);
                }
            }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    

    protected void buscarInfraciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("aplication/json");
            Conexion conexion = new Conexion();
            conexion.conectar();
            Consultas consultas = new Consultas();
            String numero = request.getParameter("numero_documento");
            PrintWriter out = response.getWriter();
            CiaPersonas ciaPersonas = consultas.ConsultaPersonaPorDocumento(conexion.getCon(), numero, BigDecimal.ONE);
            if (ciaPersonas != null) {
                List<CiaInfracciones> listInfraciones = consultas.infraccionesByPersona(conexion.getCon(), ciaPersonas.getPerId().intValue());
                if (listInfraciones.size() > 0) {
                    JSONArray jsonArray = new JSONArray();
                    for (CiaInfracciones hashMap : listInfraciones) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("drc_id", hashMap.getInfNumero());
                        jSONObject.put("per_nombres", ciaPersonas.getPerNombres());
                        jSONObject.put("per_apellidos", ciaPersonas.getPerApellidos());
                        jSONObject.put("per_documetos", ciaPersonas.getPerDocumento());
                        jSONObject.put("per_tipo_documento", ciaPersonas.getPerTpDocumento().toString());
                        jSONObject.put("numero_factura", hashMap.getInfFactura());
                        jSONObject.put("fecha_factura", hashMap.getInfFacFecha());
                        if (hashMap.getInfvalorCurso() != null) {
                            jSONObject.put("valor_curso", hashMap.getInfvalorCurso().toString());
                        }

                        jSONObject.put("codigo_infracion", hashMap.getInfCodigo().toString());
                        jSONObject.put("fecha_comparendo", hashMap.getInfFecha());
                        jsonArray.put(jSONObject);

                    }

                    out.print(jsonArray);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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
