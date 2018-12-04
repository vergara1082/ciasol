/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.servlet;

import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.db.Inserciones;
import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaDetalleCursos;
import com.cia.persistencia.CiaInfracciones;
import com.cia.persistencia.CiaPersonas;
import com.cia.persistencia.CiaUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jcarr
 */
public class agregarInfractorCurso extends HttpServlet {

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
            Conexion con;
            try {
                if (request.getSession().getAttribute("user") != null) {
                    if (request.getSession().getAttribute("conexion") != null) {
                        con = (Conexion) request.getSession().getAttribute("conexion");
                        if (con.testConexion()) {
                            try {

                                String documento = request.getParameter("txtDocumento");
                                String nombres = request.getParameter("txtNombres");
                                String apellidos = request.getParameter("txtApellidos");
                                String tipoDocumento = request.getParameter("cmbTipoDocumento");
                                Consultas consultas = new Consultas();
                                Inserciones in = new Inserciones();

                                CiaPersonas cp = consultas.ConsultaPersonaPorDocumento(con.getCon(), documento, new BigDecimal(tipoDocumento));

                                if (cp == null) {
                                    cp = new CiaPersonas(null, nombres, apellidos, BigDecimal.valueOf(Long.valueOf(tipoDocumento)), documento, BigDecimal.ONE);

                                    BigDecimal perId = in.insertarPersona(con.getCon(), cp);
                                    cp.setPerId(perId);
                                    if (perId.intValue() == 0) {
                                        response.setStatus(500);
                                        con.getCon().rollback();
                                        out.print("<span class=\"text-warning\"><h5>Error al realizar registro</h5></span>");
                                        return;
                                    }
                                }

                                CiaInfracciones inf = new CiaInfracciones(null, cp, new CiaUsuarios(BigDecimal.ONE, cp, "", "", null),
                                        request.getParameter("txtInfCodigo"),
                                        request.getParameter("txtInfFactura"), BigDecimal.ONE, new BigDecimal(request.getParameter("txtInfValorCurso")));
                                inf.setInfNumero(request.getParameter("txtInfNumero"));
                                inf.setInfFecha(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechaComp")));
                                inf.setInfFacFecha(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechaFac")));
                                inf.setInfId(in.insertarInfraccion(con.getCon(), inf));
                                if (inf.getInfId().intValue() == 0) {
                                    response.setStatus(500);
                                    con.getCon().rollback();
                                    out.print("<span class=\"text-warning\"><h5>Error al realizar registro</h5></span>");
                                    return;
                                }

                                CiaCursos cur = consultas.consultaCursoPorHorario(con.getCon(), new BigDecimal(request.getParameter("txtCurTipo")));

                                /*if (cur == null) {
                                    cur = new CiaCursos();
                                    cur.setCiaHorarios(consultas.getHorarioByid(con.getCon(), new BigDecimal(request.getParameter("txtCurTipo"))));
                                    cur.setCiaPersonas(new CiaPersonas(BigDecimal.ONE));
                                    cur.setCurEstado(BigDecimal.ONE);
                                    cur.setCurFecha(new Date());
                                    cur.setCurFechaEstado(new Date());
                                    cur.setCurId(in.insertarCurso(con.getCon(), cur));
                                    con.getCon().commit();
                                }*/
                                CiaDetalleCursos detcur = new CiaDetalleCursos(null, cur, inf, BigDecimal.ONE, new Date());

                                detcur.setDcrId(in.insertarDetCurso(con.getCon(), detcur));

                                if (detcur.getDcrId().intValue() == 0) {
                                    response.setStatus(500);
                                    con.getCon().rollback();
                                    out.print("<span class=\"text-warning\"><h5>Error al realizar registro</h5></span>");
                                    return;
                                }
                                con.getCon().commit();
                                out.print("<span class=\"text-success\"><h5>Registro realizado correctamente</h5></span>");
                            } catch (Exception e) {
                                e.printStackTrace();
                                con.getCon().rollback();
                                out.print("<span class=\"text-warning\"><h5>Error al realizar registro</h5></span>");
                            }
                        }
                        //request.getRequestDispatcher("/asingInf").forward(request, response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(agregarInfractorCurso.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(agregarInfractorCurso.class.getName()).log(Level.SEVERE, null, ex);
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
