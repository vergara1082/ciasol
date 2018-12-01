/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.cia.utils.Cifrado;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jboss.logging.Logger;

/**
 *
 * @author Miguel Borja
 */
@ManagedBean(name = "reportes")
@ViewScoped
public class Reportes implements Serializable {

    private static long serialVersionUID = 1644569354932066798L;

    private String rootPdf;
    private String rootExcel;
    private String fileName;
    private Map<String, Object> params;
    private List<Reportes> listReporte;
    private int option;

    /**
     *
     */
    @PostConstruct
    public void reporte() {
        try {
        } catch (Exception e) {
            Logger.getLogger("ReportBean").log(Logger.Level.FATAL, e);
        }//Try//Try
    }//Public Void

    /**
     *
     * @param reporte
     * @return
     */
    /**
     * @return the loginBean
     */
    /**
     * @return the option
     */
    public int getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(int option) {
        this.option = option;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the params
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the rootPdf
     */
    public String getRootPdf() {
        return rootPdf;
    }

    /**
     * @param rootPdf the rootPdf to set
     */
    public void setRootPdf(String rootPdf) {
        this.rootPdf = rootPdf;
    }

    /**
     * @return the rootExcel
     */
    public String getRootExcel() {
        return rootExcel;
    }

    /**
     * @param rootExcel the rootExcel to set
     */
    public void setRootExcel(String rootExcel) {
        this.rootExcel = rootExcel;
    }

    /**
     * @return the listReporte
     */
    public List<Reportes> getListReporte() {
        return listReporte;
    }

    /**
     * @param listReporte the listReporte to set
     */
    public void setListReporte(List<Reportes> listReporte) {
        this.listReporte = listReporte;
    }

}
