/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcarreno
 */
public class Actulizaciones {

    /*Update det cur*/
    /**
     *
     * @param con
     * @param fecha
     * @param curso
     * @param detalle_cur
     * @return
     */
    public boolean updateDetalleCurso(Connection con, String fecha, long curso, long detalle_cur) {

        PreparedStatement pst = null;
        ResultSet rst = null;

        try {
            pst = con.prepareStatement("update public.cia_detalle_cursos set cur_id = ?,dcr_fecha_estado = date(?) Where dcr_id = ? ");
            pst.setLong(1, curso);
            pst.setLong(3, detalle_cur);
            pst.setString(2, fecha);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Actulizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /*Update det cur*/

    public boolean updatePersona(Connection con, String nombres, String apellidos, String tp_documento, String perDocumento) {

        PreparedStatement pst = null;
        ResultSet rst = null;

        try {
            pst = con.prepareStatement("update public.cia_personas set per_nombres = ?,per_apellidos = ?, per_tp_documento = ?, per_documento = ?  Where per_documento = ? and per_tp_documento = ?");
            pst.setString(1, nombres);
            pst.setString(2, apellidos);
            pst.setInt(3, Integer.valueOf(tp_documento));
            pst.setString(4, perDocumento);
            pst.setString(5, perDocumento);
            pst.setInt(6, Integer.valueOf(tp_documento));
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Actulizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateInfraciones(Connection con,
            String numero_factura, String fecha_factura, String valor_curso,
            String codigoInfraccion,String numero_comparendo
    , String fecha_comparendo
    ) {

        PreparedStatement pst = null;
        ResultSet rst = null;

        try {
            pst = con.prepareStatement("update public.cia_infracciones set inf_factura = ?,inf_fecha = date(?), inf_valor_curso = ?"
                    + ", inf_codigo = ? , inf_numero = ? , inf_fac_fecha = date(?) Where inf_numero = ? ");
            pst.setString(1, numero_factura);
            pst.setString(2, fecha_factura);
            
            pst.setInt(3, Integer.valueOf(valor_curso));
            pst.setString(4, codigoInfraccion);
            pst.setString(5, numero_comparendo);
            pst.setString(6, fecha_comparendo);
            pst.setString(7, numero_comparendo);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Actulizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
