/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaHorarios;
import com.cia.persistencia.CiaPersonas;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author jcarreno
 */
public class Consultas {

    public CiaPersonas ConsultaPersonaPorDocumento(Connection con, String documento) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaPersonas per;
        try {

            pst = con.prepareStatement("Select * from cia_personas where per_documento = ?");
            pst.setString(1, documento);
            rst = pst.executeQuery();
            if (rst.next()) {
                per = new CiaPersonas();
                per.setPerId(rst.getBigDecimal("per_id"));
                per.setPerTpDocumento(rst.getBigDecimal("per_tp_documento"));
                per.setPerDocumento(rst.getString("per_documento"));
                per.setPerNombres(rst.getString("per_nombres"));
                per.setPerApellidos(rst.getString("per_apellidos"));
                per.setPerDireccion(rst.getString("per_direccion"));
                per.setPerTelefono(rst.getBigDecimal("per_telefono"));
                per.setPerEstado(rst.getBigDecimal("per_estado"));
                per.setPerFechaEstado(rst.getDate("per_fecha_estado"));
                return per;
            }

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Logger.Level.FATAL, e);
            throw new Exception("Error consultando la persona por Documento");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rst != null) {
                rst.close();
            }
        }
        return null;
    }

    public List<CiaCursos> allCurso(Connection con) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos cursos;
        try {

            pst = con.prepareStatement("Select * from cia_cursos where cur_fecha = ?");
            pst.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            rst = pst.executeQuery();
            List<CiaCursos> listCursos = new ArrayList<>();
            while (rst.next()) {
                cursos = new CiaCursos();
                cursos.setCurId(rst.getBigDecimal("cur_id"));
                CiaHorarios ciaHorarios = getHorarioByid(con, rst.getBigDecimal("hor_id"));
                cursos.setCiaHorarios(ciaHorarios);
                cursos.setCiaPersonas(new CiaPersonas());
                cursos.setCurFecha(rst.getDate("cur_fecha"));
                cursos.setCurEstado(rst.getBigDecimal("cur_estado"));
                cursos.setCurFechaEstado(rst.getDate("cur_fecha_estado"));
                listCursos.add(cursos);
            }

            return listCursos;

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Logger.Level.FATAL, e);
            throw new Exception("Error consultando la persona por Documento");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rst != null) {
                rst.close();
            }
        }

    }

    public CiaHorarios getHorarioByid(Connection con, BigDecimal id) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaHorarios horarios;
        try {

            pst = con.prepareStatement("Select * from cia_horarios where hor_id = ?");
            pst.setBigDecimal(1, id);
            rst = pst.executeQuery();
            if (rst.next()) {
                horarios = new CiaHorarios();
                horarios.setHorId(id);
                horarios.setHorTiempo(rst.getString("hor_tiempo"));
                horarios.setHorTipo(rst.getBigDecimal("hor_tipo"));
                horarios.setHorEstado(rst.getBigDecimal("hor_estado"));
                horarios.setHorFechaEstado(rst.getDate("hor_fecha_estado"));
                return horarios;
            }

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Logger.Level.FATAL, e);
            throw new Exception("Error consultando la persona por Documento");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rst != null) {
                rst.close();
            }
        }
        return null;

    }

    public List cursoPorHorario(Connection c, String tipo) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos ciaCursos = null;
        List<CiaCursos> listaCurso = new ArrayList<>();
        try {
            pst = c.prepareStatement("SELECT cur.* FROM cia_cursos cur \n"
                    + "INNER JOIN cia_horarios hor ON hor.hor_id = cur.hor_id\n"
                    + "INNER JOIN cia_detalle_cursos detcur ON detcur.cur_id = cur.cur_id\n"
                    + "INNER JOIN cia_infracciones inf ON inf.inf_id = detcur.inf_id\n"
                    + "INNER JOIN cia_personas per ON per.per_id = inf.per_id\n"
                    + "WHERE hor.hor_tipo = ?");

            pst.setString(1, tipo);
            rst = pst.executeQuery();
            while (rst.next()) {
                ciaCursos = new CiaCursos();
                ciaCursos.setCurId(rst.getBigDecimal("cur_id"));
                ciaCursos.setCurFecha(rst.getDate("cur_fecha"));
                ciaCursos.setCurEstado(rst.getBigDecimal("cur_estado"));
                ciaCursos.setCiaHorarios(new CiaHorarios(rst.getBigDecimal("hor_id")));
                ciaCursos.setCiaPersonas(new CiaPersonas());
                ciaCursos.setCurFechaEstado(rst.getDate("cur_fecha_estado"));
                listaCurso.add(ciaCursos);
            }
        } catch (SQLException e) {
            throw new Exception("Error al consultar los cursos. ");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rst != null) {
                rst.close();
            }
        }
        return listaCurso;
    }

    public static void main(String[] args) throws IOException, Exception {
        Conexion net = new Conexion();
        net.conectar();
        List<CiaCursos> listCursos = new Consultas().allCurso(net.getCon());
        for (CiaCursos listCurso : listCursos) {
            System.out.println(listCurso.getCurId());
        }
    }

}
