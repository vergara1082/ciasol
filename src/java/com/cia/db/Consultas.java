/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import Cifrado.Encriptar_md5;
import com.cia.persistencia.CiaPersonas;
import com.cia.persistencia.CiaUsuarios;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jboss.logging.Logger;

/**
 *
 * @author yo
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
    }//ConsultaPersonaDocumento

    /*Consultar USuarios*/
    public CiaUsuarios validarUsuario(Connection con, String user, String pass) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaUsuarios obj;
        Encriptar_md5 en = new Encriptar_md5();
        try {
            pst = con.prepareStatement("Select * from cia_usuarios where us_nombre = ? and us_password = ?");
            pst.setString(1, user.toUpperCase());
            pst.setString(2, en.get_md5(pass));
            rst = pst.executeQuery();
            if (rst.next()) {
                obj = new CiaUsuarios();
                obj.setUsId(rst.getBigDecimal("us_id"));
                obj.setUsNombre(rst.getString("us_nombre"));
                obj.setUsPassword(rst.getString("us_password"));
                obj.setUsEstado(rst.getBigDecimal("us_estado"));
                obj.setCiaPersonas(new CiaPersonas(rst.getBigDecimal("per_id"), "", "", BigDecimal.ZERO, user, BigDecimal.ZERO));
                obj.setUsFechaEstado(rst.getDate("us_fecha_estado"));
                return obj;
            }

        } catch (SQLException e) {
            Logger.getLogger(Consultas.class.getName()).log(Logger.Level.FATAL, e);
            throw new Exception("Error consultando El usuario");
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
    /* End Consultas Usuarios*/
}
