/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import Cifrado.Encriptar_md5;
import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaDetalleCursos;
import com.cia.persistencia.CiaHorarios;
import com.cia.persistencia.CiaInfracciones;
import com.cia.persistencia.CiaPersonas;
import com.cia.persistencia.CiaUsuarios;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author yo
 */
public class Consultas {

    public CiaPersonas ConsultaPersonaPorDocumento(Connection con, String documento, BigDecimal tp) throws Exception {
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
    public List<CiaCursos> allCurso(Connection con) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos cursos;
        try {

            pst = con.prepareStatement("Select * from cia_cursos where cur_estado = 1");
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

    public boolean tieneCertifiaco(Connection con, long idDetcur) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos cursos;
        try {

            pst = con.prepareStatement("Select * from cia_certificados where dcr_id= ?");
            pst.setLong(1, idDetcur);
            rst = pst.executeQuery();
            if (rst.next()) {
                return true;
            }

            return false;

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

    /*Cur get*/
    public CiaCursos consultaCursoPorHorario(Connection con, BigDecimal id) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos cursos;
        try {

            pst = con.prepareStatement("Select \n"
                    + "*\n"
                    + "from cia_cursos\n"
                    + "Where \n"
                    + "cur_estado = 1\n"
                    + "and hor_id=?");
            pst.setBigDecimal(1, id);
            rst = pst.executeQuery();
            if (rst.next()) {
                cursos = new CiaCursos();
                cursos.setCurId(rst.getBigDecimal("cur_id"));
                CiaHorarios ciaHorarios = getHorarioByid(con, rst.getBigDecimal("hor_id"));
                cursos.setCiaHorarios(ciaHorarios);
                cursos.setCiaPersonas(new CiaPersonas());
                cursos.setCurFecha(rst.getDate("cur_fecha"));
                cursos.setCurEstado(rst.getBigDecimal("cur_estado"));
                cursos.setCurFechaEstado(rst.getDate("cur_fecha_estado"));
                return cursos;
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

    /*cur get*/
    public List cursoPorHorario(Connection c, int tipo) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaCursos ciaCursos = null;
        List<CiaCursos> listaCurso = new ArrayList<>();
        try {
            pst = c.prepareStatement("SELECT * FROM cia_cursos cur "
                    + "INNER JOIN cia_horarios hor ON hor.hor_id = cur.hor_id "
                    + "INNER JOIN cia_detalle_cursos detcur ON detcur.cur_id = cur.cur_id "
                    + "and to_char(current_date,'dd/MM/yyyy') = to_char(detcur.dcr_fecha_estado,'dd/MM/yyyy') "
                    + "INNER JOIN cia_infracciones inf ON inf.inf_id = detcur.inf_id  "
                    + "INNER JOIN cia_personas per ON per.per_id = inf.per_id WHERE hor.hor_tipo = ? "
                    + "");

            pst.setInt(1, tipo);
            rst = pst.executeQuery();
            while (rst.next()) {
                ciaCursos = new CiaCursos();
                ciaCursos.setCurId(rst.getBigDecimal("cur_id"));
                ciaCursos.setCurFecha(rst.getDate("cur_fecha"));
                ciaCursos.setCurEstado(rst.getBigDecimal("cur_estado"));
                ciaCursos.setCiaHorarios(new CiaHorarios(rst.getBigDecimal("hor_id")));
                ciaCursos.setCiaPersonas(new CiaPersonas(rst.getBigDecimal("per_id"), rst.getString("per_nombres"), rst.getString("per_apellidos"), rst.getBigDecimal("per_tp_documento"), rst.getString("per_documento"), rst.getBigDecimal("per_estado")));
                ciaCursos.setCurFechaEstado(rst.getDate("cur_fecha_estado"));
                listaCurso.add(ciaCursos);
            }
        } catch (SQLException e) {
            Logger.getLogger("Cosultas").log(Logger.Level.FATAL, "Error al consultar los cursos.", e);
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

    public CiaInfracciones infraccionesByPer(Connection c, int per_id) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        CiaInfracciones ci = new CiaInfracciones();
        try {
            pst = c.prepareStatement("SELECT * FROM CIA_INFRACCIONES WHERE PER_ID =?");
            pst.setInt(1, per_id);
            rst = pst.executeQuery();
            if (rst.next()) {
                ci = new CiaInfracciones();
                ci.setCiaPersonas(new CiaPersonas(rst.getBigDecimal("per_id")));
                ci.setInfNumero(rst.getString("inf_numero"));
                ci.setInfId(rst.getBigDecimal("inf_id"));
                ci.setInfFecha(rst.getDate("inf_fecha"));
                ci.setInfCodigo(rst.getString("inf_codigo"));
                ci.setInfFactura(rst.getString("inf_factura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar persona.");
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rst != null) {
                rst.close();
            }
        }
        return ci;
    }

    public static void main(String[] args) throws IOException, Exception {
        Conexion net = new Conexion();
        net.conectar();
        List<CiaCursos> listCursos = new Consultas().allCurso(net.getCon());
        for (CiaCursos listCurso : listCursos) {
            System.out.println(listCurso.getCurId());
        }
    }

    public List<HashMap> getlistDetalleCursoByCurso(Connection conexion, long idCurso) throws Exception {

        PreparedStatement pst = null;
        ResultSet rst = null;
        try {

            pst = conexion.prepareStatement("Select dc.dcr_id,pe.per_documento,pe.per_nombres, pe.per_apellidos from Cia_Cursos as c\n"
                    + "Inner join cia_detalle_cursos as dc on dc.cur_id = c.cur_id \n"
                    + "Inner Join cia_infracciones as inf on inf.inf_id = dc.inf_id\n"
                    + "Inner join cia_personas as pe on pe.per_id = inf.per_id\n"
                    + "where c.hor_id = ? and dcr_estado = ? \n"
                    + "and to_char(now(),'dd/MM/yyyy') = to_char(dcr_fecha_estado,'dd/MM/yyyy')\n"
                    + "order by 1 asc");
            pst.setLong(1, idCurso);
            pst.setLong(2, 1);
            rst = pst.executeQuery();
            List<HashMap> listDetalleCursos = new ArrayList<>();
            List<HashMap> listaDetallesCursosId = new ArrayList<>();
            while (rst.next()) {
                HashMap hashMap = new HashMap();
                hashMap.put("dcr_id", rst.getBigDecimal("dcr_id").toString());
                hashMap.put("per_nombres", rst.getString("per_nombres"));
                hashMap.put("per_apellidos", rst.getString("per_apellidos"));
                hashMap.put("per_documetos", rst.getString("per_documento"));
                listDetalleCursos.add(hashMap);
            }
            return listDetalleCursos;

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

    public List<HashMap> getlistDetalleCursoByCurso(Connection conexion, long idCurso, String fecha) throws Exception {

        PreparedStatement pst = null;
        ResultSet rst = null;
        try {

            pst = conexion.prepareStatement("Select dc.dcr_id,pe.per_documento,pe.per_nombres, pe.per_apellidos from Cia_Cursos as c\n"
                    + "Inner join cia_detalle_cursos as dc on dc.cur_id = c.cur_id \n"
                    + "Inner Join cia_infracciones as inf on inf.inf_id = dc.inf_id\n"
                    + "Inner join cia_personas as pe on pe.per_id = inf.per_id\n"
                    + "where c.hor_id = ? and dcr_estado = ? \n"
                    + "and ? = to_char(dcr_fecha_estado,'dd/MM/yyyy')\n"
                    + "order by 1 asc");
            pst.setLong(1, idCurso);
            pst.setString(3, fecha);
            pst.setLong(2, 1);
            rst = pst.executeQuery();
            List<HashMap> listDetalleCursos = new ArrayList<>();
            while (rst.next()) {
                HashMap hashMap = new HashMap();
                hashMap.put("dcr_id", rst.getBigDecimal("dcr_id").toString());
                hashMap.put("per_nombres", rst.getString("per_nombres"));
                hashMap.put("per_apellidos", rst.getString("per_apellidos"));
                hashMap.put("per_documetos", rst.getString("per_documento"));
                listDetalleCursos.add(hashMap);
            }
            return listDetalleCursos;

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

    public List<HashMap> getlistDetalleCursoByPersonaReasignacion(Connection conexion, long tipoDoc, String documento) throws Exception {

        PreparedStatement pst = null;
        ResultSet rst = null;
        try {

            pst = conexion.prepareStatement("Select\n"
                    + "pe.*,inf.*,dcr.dcr_id\n"
                    + "from\n"
                    + "Cia_personas pe\n"
                    + "Inner join Cia_infracciones inf on pe.per_id= inf.per_id\n"
                    + "Inner join Cia_detalle_cursos dcr on dcr.inf_id = inf.inf_id \n"
                    + "where \n"
                    + "pe.per_documento = ? and pe.per_tp_documento = ?\n"
                    + "and\n"
                    + "dcr.dcr_id not in (Select dcr_id from cia_certificados)");
            pst.setLong(2, tipoDoc);
            pst.setString(1, documento);
            rst = pst.executeQuery();
            List<HashMap> listDetalleCursos = new ArrayList<>();
            while (rst.next()) {
                HashMap hashMap = new HashMap();
                hashMap.put("dcr_id", rst.getBigDecimal("dcr_id").toString());
                hashMap.put("per_nombres", rst.getString("per_nombres"));
                hashMap.put("per_documento", rst.getString("per_documento"));
                hashMap.put("per_apellidos", rst.getString("per_apellidos"));
                hashMap.put("inf_numero", rst.getString("inf_numero"));
                hashMap.put("dcr_fecha", rst.getString("dcr_fecha"));
                hashMap.put("inf_codigo", rst.getString("inf_codigo"));
                listDetalleCursos.add(hashMap);
            }
            return listDetalleCursos;

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

    public List<CiaPersonas> getDataPersonByDetailsCourses(Connection con, long id_detalle) throws Exception {
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {

            pst = con.prepareStatement("SELECT \n"
                    + "cia_personas.per_nombres, cia_personas.per_apellidos, cia_personas.per_documento\n"
                    + " FROM cia_detalle_cursos \n"
                    + "inner join cia_infracciones on cia_detalle_cursos.inf_id = cia_infracciones.inf_id \n"
                    + "inner join cia_personas on cia_infracciones.per_id = cia_personas.per_id \n"
                    + "where cia_detalle_cursos.cur_id= ?");
            pst.setLong(1, id_detalle);
            rst = pst.executeQuery();
            List<CiaPersonas> listCiaPersonase = new ArrayList<>();
            while (rst.next()) {
                CiaPersonas civPersonas = new CiaPersonas();
                civPersonas.setPerDocumento(rst.getString("per_documento"));
                civPersonas.setPerNombres(rst.getString("per_nombres"));
                civPersonas.setPerApellidos(rst.getString("per_apellidos"));
                listCiaPersonase.add(civPersonas);
            }

            return listCiaPersonase;

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

}
