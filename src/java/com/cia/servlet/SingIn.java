/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

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
        try {
            PrintWriter out = response.getWriter();
            Conexion con = new Conexion();
            Consultas consultas = new Consultas();
            request.getSession().setAttribute("conexion", con);
            try {
                con.conectar();
                if (!con.testConexion()) {
                    Logger.getLogger(SingIn.class.getName()).log(Level.SEVERE, null, "No hay Conexión");
                    return;
                }
                String user = request.getParameter("inputUser");
                String pass = request.getParameter("inputPassword");

                CiaUsuarios us = consultas.validarUsuario(con.getCon(), user, pass);
                if (us == null) {
                    request.getRequestDispatcher("index.html").forward(request, response);
                }
                request.getSession().setAttribute("user", us);
                request.getRequestDispatcher("paginas/inicio2.jsp").forward(request, response);

            } catch (Exception ex) {
                if (request.getSession().getAttribute("user") != null) {
                    if (request.getSession().getAttribute("conexion") != null) {
                        con = (Conexion) request.getSession().getAttribute("conexion");
                        if (con.testConexion()) {
                            request.getRequestDispatcher("paginas/inicio2.jsp").forward(request, response);
                        }
                        response.reset();
                        request.getRequestDispatcher(request.getContextPath()).forward(request, response);
                    }
                }
            }

        }catch(Exception ex){
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
