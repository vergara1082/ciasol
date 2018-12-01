/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.persistencia.CiaCursos;
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
        PrintWriter out = response.getWriter();
        Conexion c = new Conexion();
        List<CiaCursos> list = new ArrayList<>();
        try {
            String tipo = request.getParameter("selectHorario");
            c.conectar();
            Consultas consultas = new Consultas();
            list = consultas.cursoPorHorario(c.getCon(), Integer.valueOf(tipo));

            out.println("<table>");
            out.println("<tr>");
            out.println("<td > NOMBRE </td>");
            out.println("<td>APELLIDO</td>");
            out.println("<td >EDAD</td>");
            out.println("</tr>");
            for (int i = 0; i < list.size(); i++) {
                List<CiaCursos> persona = (List<CiaCursos>) list.get(i);
                out.println("<tr>");
                out.println("<td>" + persona.get(i).getCurFecha() + "</td>");
                out.println("<td >" + persona.get(i).getCurEstado() + "</td>");
                out.println("<td>" + persona.get(i).getCurId() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
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
