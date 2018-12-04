/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.procesos;

import com.cia.db.Conexion;
import com.cia.db.Consultas;
import com.cia.persistencia.CiaCursos;
import com.cia.persistencia.CiaInfracciones;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elkin Madrid
 */
public class ProcesosGenerarExcelCurso {

    public void generarExcelCurso(List<CiaCursos> lista, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Consultas consultas = new Consultas();
        Conexion c = new Conexion();
        c.conectar();
//        File excel = new File(request.getRealPath("") + "/data");
        File excel = new File("C:/Archivos/");
        if (!excel.exists()) {
            excel.mkdirs();
        }
//        excel = new File(request.getRealPath("/") + "data/ReportePersuasivoByObli.xls");
        excel = new File(excel.getAbsolutePath() + "/Reporte.xls");
        try (FileWriter fw = new FileWriter(excel)) {
            String header = "N° Documento" + ";" + "Nombres" + ";" + "N° comparendo";
            fw.write(header);
            for (int i = 0; i < lista.size(); i++) {
                fw.write("\r\n");
                CiaInfracciones ci = consultas.infraccionesByPer(c.getCon(), lista.get(i).getCiaPersonas().getPerId().intValue());
                String nombres = lista.get(i).getCiaPersonas().getPerNombres() + " " + lista.get(i).getCiaPersonas().getPerApellidos();
                String row = lista.get(i).getCiaPersonas().getPerDocumento() + ";" + nombres + ";" + (ci == null ? "N/A" : ci.getInfNumero());
                fw.write(row);
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(excel);
        byte[] bytes = new byte[1000];
        int read;
        String fileName = excel.getName();
        String contentType = "application/vnd.ms-excel";
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        try (ServletOutputStream out = response.getOutputStream()) {
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
        }

    }

}
