/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.procesos;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Elkin Madrid
 */
public class ProcesosGenerarExcelCurso {

    public void generarExcelCurso() {
        File excel = new File("excelcurso.xlsx");
        try (FileWriter fw = new FileWriter(excel)) {
            String header = "Horario" + ";" + "Persona" + ";" + "Fecha";
            fw.write(header);
        } catch (Exception e) {
        }
    }

}
