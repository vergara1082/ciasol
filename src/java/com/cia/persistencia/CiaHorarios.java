package com.cia.persistencia;
// Generated 29/11/2018 07:18:52 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CiaHorarios generated by hbm2java
 */
public class CiaHorarios  implements java.io.Serializable {


     private BigDecimal horId;
     private BigDecimal horTipo;
     private String horTiempo;
     private BigDecimal horEstado;
     private Date horFechaEstado;
     private Set ciaCursoses = new HashSet(0);

    public CiaHorarios() {
    }

    public CiaHorarios(BigDecimal horId) {
        this.horId = horId;
    }
    
    

	
    public CiaHorarios(BigDecimal horId, BigDecimal horTipo, String horTiempo, BigDecimal horEstado) {
        this.horId = horId;
        this.horTipo = horTipo;
        this.horTiempo = horTiempo;
        this.horEstado = horEstado;
    }
    public CiaHorarios(BigDecimal horId, BigDecimal horTipo, String horTiempo, BigDecimal horEstado, Date horFechaEstado, Set ciaCursoses) {
       this.horId = horId;
       this.horTipo = horTipo;
       this.horTiempo = horTiempo;
       this.horEstado = horEstado;
       this.horFechaEstado = horFechaEstado;
       this.ciaCursoses = ciaCursoses;
    }
   
    public BigDecimal getHorId() {
        return this.horId;
    }
    
    public void setHorId(BigDecimal horId) {
        this.horId = horId;
    }
    public BigDecimal getHorTipo() {
        return this.horTipo;
    }
    
    public void setHorTipo(BigDecimal horTipo) {
        this.horTipo = horTipo;
    }
    public String getHorTiempo() {
        return this.horTiempo;
    }
    
    public void setHorTiempo(String horTiempo) {
        this.horTiempo = horTiempo;
    }
    public BigDecimal getHorEstado() {
        return this.horEstado;
    }
    
    public void setHorEstado(BigDecimal horEstado) {
        this.horEstado = horEstado;
    }
    public Date getHorFechaEstado() {
        return this.horFechaEstado;
    }
    
    public void setHorFechaEstado(Date horFechaEstado) {
        this.horFechaEstado = horFechaEstado;
    }
    public Set getCiaCursoses() {
        return this.ciaCursoses;
    }
    
    public void setCiaCursoses(Set ciaCursoses) {
        this.ciaCursoses = ciaCursoses;
    }




}


