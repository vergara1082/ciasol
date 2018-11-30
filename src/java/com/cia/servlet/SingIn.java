/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

import Cifrado.Encriptar_md5;
import Cifrado.KeyStore;
import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.persistencia.CiaUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jcarreno
 */
public class SingIn extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getSession().getAttribute("user") != null) {
                if (request.getSession().getAttribute("conexion") != null) {
                    Conexion con = (Conexion) request.getSession().getAttribute("conexion");
                    if (con.testConexion()) {
                        request.getRequestDispatcher("paginas/inicio.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher(request.getContextPath()).forward(request, response);
                }
            }
            Conexion con = new Conexion();
            Consultas consultas = new Consultas();
            request.getSession().setAttribute("conexion", con);
            try {
                con.conectar();
                if (!con.testConexion()) {
                    Logger.getLogger(SingIn.class.getName()).log(Level.SEVERE, null, "No hay Conexión");
                    return;
                }
                if (request.getParameter("inputUser") == null) {
                    request.getRequestDispatcher(request.getContextPath()).forward(request, response);
                }

                if (request.getParameter("inputPassword") == null) {
                    request.getRequestDispatcher(request.getContextPath()).forward(request, response);
                }

                String user = request.getParameter("inputUser");
                String pass = request.getParameter("inputPassword");

                CiaUsuarios us = consultas.validarUsuario(con.getCon(), user, pass);
                if (us == null) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet SingIn</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet SingIn at " + request.getContextPath() + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
                request.getSession().setAttribute("user", us);
                request.getRequestDispatcher("paginas/inicio.jsp").forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(SingIn.class.getName()).log(Level.SEVERE, null, ex);
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
