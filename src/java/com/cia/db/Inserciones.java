/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

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
                    + "inf_fecha_estado, us_id, inf_numero)\n"
                    + "	VALUES (NEXTVAL('s_infracciones'), ?, ?, ?, ?, ?, ?, ?, ?);", new String[]{"inf_id"});

            pst.setBigDecimal(1, inf.getCiaPersonas().getPerId());
            pst.setString(2, inf.getInfCodigo());
            pst.setDate(3, new Date(inf.getInfFecha().getTime()));
            pst.setString(4, inf.getInfFactura());
            pst.setBigDecimal(5, inf.getInfEstado());
            pst.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
            pst.setBigDecimal(7, inf.getCiaUsuarios().getUsId());
            pst.setString(8, inf.getInfNumero());

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
    public static void main(String[] args) throws IOException, Exception {
        Conexion net = new Conexion();
        net.conectar();
        try {
            Inserciones in = new Inserciones();
            CiaPersonas per = new CiaPersonas(null, "Arnol", "Mendoza", BigDecimal.ONE, "8814567", BigDecimal.ONE);
            per.setPerId(in.insertarPersona(net.getCon(), per));
            net.getCon().commit();
        } catch (Exception e) {
            e.printStackTrace();
            net.getCon().rollback();
        }
    }

}
