/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaInfracciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author emadrid
 */
public class AgendamientoDelDia extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html; charset=iso-8859-1");
        HttpSession misession = request.getSession(true);
        PrintWriter out = response.getWriter();
        Conexion c = new Conexion();
        List<CiaCursos> list = new ArrayList<>();
        try {
            String tipo = request.getParameter("tipo");

            c.conectar();
            Consultas consultas = new Consultas();
            list = consultas.cursoPorHorario(c.getCon(), Integer.valueOf(tipo));
            if (!list.isEmpty()) {

                misession.setAttribute("lista", list);
                out.println("<table class=\"table table-hover\">");
                out.println("<thead class=\"thead-dark\">");
                out.println("<tr>");
                out.println("<td scope=\"col\" >#</td>");
                out.println("<td scope=\"col\" >N documento</td>");
                out.println("<td scope=\"col\">Nombres</td>");
                out.println("<td scope=\"col\" >N° Comparendo</td>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody id=\"tbody\">");
                int contador = 1;
                for (int i = 0; i < list.size(); i++) {
                    CiaCursos ciaCursos = list.get(i);
                    CiaInfracciones ci = consultas.infraccionesByPer(c.getCon(), ciaCursos.getCiaPersonas().getPerId().intValue());
                    out.println("<tr>");
                    out.println("<td>" + contador++ + "</td>");
                    out.println("<td>" + ciaCursos.getCiaPersonas().getPerDocumento() + "</td>");
                    out.println("<td >" + ciaCursos.getCiaPersonas().getPerNombres() + " " + ciaCursos.getCiaPersonas().getPerApellidos() + "</td>");
                    out.println("<td>" + (ci == null ? "N/A" : ci.getInfNumero()) + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            }
            out.println("<input id=\"descargar\" onclick=\"descargarExcel()\" type=\"button\" value=\"Descargar excel.\" class=\"btn btn-success\" />");
        } catch (Exception ex) {
            Logger.getLogger(AgendamientoDelDia.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
