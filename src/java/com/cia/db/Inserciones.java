/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import Cifrado.Encriptar_md5;
import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaDetalleCursos;
import com.cia.persistencia.CiaInfracciones;
import com.cia.persistencia.CiaPersonas;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 *
 * @author jcarreno
 */
public class Inserciones {

    /*per*/
    public BigDecimal insertarPersona(Connection con, CiaPersonas persona) throws Exception {

        PreparedStatement pst = null;
        ResultSet key = null;
        try {
            pst = con.prepareStatement("INSERT INTO public.cia_personas(\n"
                    + "per_id,per_nombres, per_apellidos, per_tp_documento, per_documento, "
                    + "per_telefono, per_direccion, per_estado, per_fecha_estado)\n"
                    + "	VALUES (NEXTVAL('s_ciapersonas'),?, ?, ?, ?, ?, ?, ?,?)", new String[]{"per_id"});

            pst.setString(1, persona.getPerNombres());
            pst.setString(2, persona.getPerApellidos());
            pst.setBigDecimal(3, persona.getPerTpDocumento());
            pst.setString(4, persona.getPerDocumento());
            pst.setBigDecimal(5, persona.getPerTelefono());
            pst.setString(6, persona.getPerDireccion());
            pst.setBigDecimal(7, persona.getPerEstado());
            pst.setDate(8, new Date(Calendar.getInstance().getTime().getTime()));

            pst.executeUpdate();

            key = pst.getGeneratedKeys();
            if (key != null) {
                if (key.next()) {
                    return key.getBigDecimal(1);
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (key != null) {
                key.close();
            }
        }
        return BigDecimal.ZERO;
    }

    /*per*/
 /*inf*/
    public BigDecimal insertarInfraccion(Connection con, CiaInfracciones inf) throws Exception {

        PreparedStatement pst = null;
        ResultSet key = null;
        try {
            pst = con.prepareStatement("INSERT INTO public.cia_infracciones(\n"
                    + "	inf_id, per_id, inf_codigo, inf_fecha, inf_factura, inf_estado, "
                    + "inf_fecha_estado, us_id, inf_numero, inf_valor_curso, inf_fac_fecha)\n"
                    + "	VALUES ((NEXTVAL('s_infracciones')), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[]{"inf_id"});

            pst.setBigDecimal(1, inf.getCiaPersonas().getPerId());
            pst.setString(2, inf.getInfCodigo());
            pst.setDate(3, new Date(inf.getInfFecha().getTime()));
            pst.setString(4, inf.getInfFactura());
            pst.setBigDecimal(5, inf.getInfEstado());
            pst.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
            pst.setBigDecimal(7, inf.getCiaUsuarios().getUsId());
            pst.setString(8, inf.getInfNumero());
            pst.setBigDecimal(9, inf.getInfvalorCurso());
            pst.setDate(10, new Date(Calendar.getInstance().getTime().getTime()));

            pst.executeUpdate();

            key = pst.getGeneratedKeys();
            if (key != null) {
                if (key.next()) {
                    return key.getBigDecimal(1);
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (key != null) {
                key.close();
            }
        }
        return BigDecimal.ZERO;
    }

    /*inf*/
 /*detCur*/
    public BigDecimal insertarDetCurso(Connection con, CiaDetalleCursos obj) throws Exception {

        PreparedStatement pst = null;
        ResultSet key = null;
        try {
            pst = con.prepareStatement("INSERT INTO public.cia_detalle_cursos(\n"
                    + "	dcr_id, cur_id, inf_id, dcr_estado, dcr_fecha_estado)\n"
                    + "	VALUES (NEXTVAL('s_detalle_cursos'), ?, ?, ?, ?)", new String[]{"dcr_id"});

            pst.setBigDecimal(1, obj.getCiaCursos().getCurId());
            pst.setBigDecimal(2, obj.getCiaInfracciones().getInfId());
            pst.setBigDecimal(3, obj.getDcrEstado());
            pst.setDate(4, new Date(Calendar.getInstance().getTime().getTime()));

            pst.executeUpdate();

            key = pst.getGeneratedKeys();
            if (key != null) {
                if (key.next()) {
                    return key.getBigDecimal(1);
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (key != null) {
                key.close();
            }
        }
        return BigDecimal.ZERO;
    }

    /*detCur*/
 /*detCur*/
    public BigDecimal insertarCurso(Connection con, CiaCursos obj) throws Exception {

        PreparedStatement pst = null;
        ResultSet key = null;

        try {
            pst = con.prepareStatement("INSERT INTO public.cia_cursos(\n"
                    + "	cur_id, hor_id, cur_ins_id, cur_fecha, cur_estado, cur_fecha_estado)\n"
                    + "	VALUES (NEXTVAL('s_cursos'), ?, ?, ?, ?, ?)", new String[]{"cur_id"});

            pst.setBigDecimal(1, obj.getCiaHorarios().getHorId());
            pst.setBigDecimal(2, obj.getCiaPersonas().getPerId());
            pst.setDate(3, new Date(Calendar.getInstance().getTime().getTime()));
            pst.setBigDecimal(4, obj.getCurEstado());
            pst.setDate(5, new Date(Calendar.getInstance().getTime().getTime()));

            pst.executeUpdate();

            key = pst.getGeneratedKeys();
            if (key != null) {
                if (key.next()) {
                    return key.getBigDecimal(1);
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (key != null) {
                key.close();
            }
        }
        return BigDecimal.ZERO;
    }

    /*detCur*/
    public BigDecimal insertarCertificado(Connection con, BigDecimal dercur) throws Exception {

        PreparedStatement pst = null;
        ResultSet key = null;

        try {
            pst = con.prepareStatement("INSERT INTO cia_certificados(\n"
                    + "	cer_id, cer_numero, dcr_id, cer_fecha)\n"
                    + "	VALUES ((NEXTVAL('s_certificado')), (NEXTVAL('s_numero_cer')), ?, ?)");

            pst.setBigDecimal(1, dercur);
            pst.setDate(2, new Date(Calendar.getInstance().getTime().getTime()));

            pst.executeUpdate();

            key = pst.getGeneratedKeys();
            if (key != null) {
                if (key.next()) {
                    return key.getBigDecimal(1);
                }
            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (key != null) {
                key.close();
            }
        }
        return BigDecimal.ZERO;
    }

    /*detCur*/
    public static void main(String[] args) throws IOException, Exception {
        Conexion net = new Conexion();
        net.conectar();
        try {
            Encriptar_md5 en = new Encriptar_md5();
            System.out.println(en.get_md5("ciasoledad"));
        } catch (Exception e) {
            e.printStackTrace();
            net.getCon().rollback();
        }
    }

}
