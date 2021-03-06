package com.cia.persistencia;
// Generated 29/11/2018 07:18:52 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CiaCursos generated by hbm2java
 */
public class CiaCursos  implements java.io.Serializable {


     private BigDecimal curId;
     private CiaHorarios ciaHorarios;
     private CiaPersonas ciaPersonas;
     private Date curFecha;
     private BigDecimal curEstado;
     private Date curFechaEstado;
     private Set ciaDetalleCursoses = new HashSet(0);

    public CiaCursos() {
    }

	
    public CiaCursos(BigDecimal curId) {
        this.curId = curId;
    }
    public CiaCursos(BigDecimal curId, CiaHorarios ciaHorarios, CiaPersonas ciaPersonas, Date curFecha, BigDecimal curEstado, Date curFechaEstado, Set ciaDetalleCursoses) {
       this.curId = curId;
       this.ciaHorarios = ciaHorarios;
       this.ciaPersonas = ciaPersonas;
       this.curFecha = curFecha;
       this.curEstado = curEstado;
       this.curFechaEstado = curFechaEstado;
       this.ciaDetalleCursoses = ciaDetalleCursoses;
    }
   
    public BigDecimal getCurId() {
        return this.curId;
    }
    
    public void setCurId(BigDecimal curId) {
        this.curId = curId;
    }
    public CiaHorarios getCiaHorarios() {
        return this.ciaHorarios;
    }
    
    public void setCiaHorarios(CiaHorarios ciaHorarios) {
        this.ciaHorarios = ciaHorarios;
    }
    public CiaPersonas getCiaPersonas() {
        return this.ciaPersonas;
    }
    
    public void setCiaPersonas(CiaPersonas ciaPersonas) {
        this.ciaPersonas = ciaPersonas;
    }
    public Date getCurFecha() {
        return this.curFecha;
    }
    
    public void setCurFecha(Date curFecha) {
        this.curFecha = curFecha;
    }
    public BigDecimal getCurEstado() {
        return this.curEstado;
    }
    
    public void setCurEstado(BigDecimal curEstado) {
        this.curEstado = curEstado;
    }
    public Date getCurFechaEstado() {
        return this.curFechaEstado;
    }
    
    public void setCurFechaEstado(Date curFechaEstado) {
        this.curFechaEstado = curFechaEstado;
    }
    public Set getCiaDetalleCursoses() {
        return this.ciaDetalleCursoses;
    }
    
    public void setCiaDetalleCursoses(Set ciaDetalleCursoses) {
        this.ciaDetalleCursoses = ciaDetalleCursoses;
    }




}


