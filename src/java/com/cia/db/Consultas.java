/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import com.cia.persistencia.CiaPersonas;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jboss.logging.Logger;
import org.postgresql.core.Query;

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

}
