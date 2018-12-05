/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import java.sql.Connection;
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
            pst = con.prepareStatement("update from public.cia_detalle_cursos set cur_id = ?,dcr_fecha_estado = date(?) Where dcr_id = ? ");
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

}
