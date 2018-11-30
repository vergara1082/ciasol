package com.cia.persistencia;
// Generated 29/11/2018 07:18:52 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CiaUsuarios generated by hbm2java
 */
public class CiaUsuarios  implements java.io.Serializable {


     private BigDecimal usId;
     private CiaPersonas ciaPersonas;
     private String usNombre;
     private String usPassword;
     private BigDecimal usEstado;
     private Date usFechaEstado;
     private Set ciaRecursosUsuarioses = new HashSet(0);
     private Set ciaInfraccioneses = new HashSet(0);

    public CiaUsuarios() {
    }

	
    public CiaUsuarios(BigDecimal usId, CiaPersonas ciaPersonas, String usNombre, String usPassword, BigDecimal usEstado) {
        this.usId = usId;
        this.ciaPersonas = ciaPersonas;
        this.usNombre = usNombre;
        this.usPassword = usPassword;
        this.usEstado = usEstado;
    }
    public CiaUsuarios(BigDecimal usId, CiaPersonas ciaPersonas, String usNombre, String usPassword, BigDecimal usEstado, Date usFechaEstado, Set ciaRecursosUsuarioses, Set ciaInfraccioneses) {
       this.usId = usId;
       this.ciaPersonas = ciaPersonas;
       this.usNombre = usNombre;
       this.usPassword = usPassword;
       this.usEstado = usEstado;
       this.usFechaEstado = usFechaEstado;
       this.ciaRecursosUsuarioses = ciaRecursosUsuarioses;
       this.ciaInfraccioneses = ciaInfraccioneses;
    }
   
    public BigDecimal getUsId() {
        return this.usId;
    }
    
    public void setUsId(BigDecimal usId) {
        this.usId = usId;
    }
    public CiaPersonas getCiaPersonas() {
        return this.ciaPersonas;
    }
    
    public void setCiaPersonas(CiaPersonas ciaPersonas) {
        this.ciaPersonas = ciaPersonas;
    }
    public String getUsNombre() {
        return this.usNombre;
    }
    
    public void setUsNombre(String usNombre) {
        this.usNombre = usNombre;
    }
    public String getUsPassword() {
        return this.usPassword;
    }
    
    public void setUsPassword(String usPassword) {
        this.usPassword = usPassword;
    }
    public BigDecimal getUsEstado() {
        return this.usEstado;
    }
    
    public void setUsEstado(BigDecimal usEstado) {
        this.usEstado = usEstado;
    }
    public Date getUsFechaEstado() {
        return this.usFechaEstado;
    }
    
    public void setUsFechaEstado(Date usFechaEstado) {
        this.usFechaEstado = usFechaEstado;
    }
    public Set getCiaRecursosUsuarioses() {
        return this.ciaRecursosUsuarioses;
    }
    
    public void setCiaRecursosUsuarioses(Set ciaRecursosUsuarioses) {
        this.ciaRecursosUsuarioses = ciaRecursosUsuarioses;
    }
    public Set getCiaInfraccioneses() {
        return this.ciaInfraccioneses;
    }
    
    public void setCiaInfraccioneses(Set ciaInfraccioneses) {
        this.ciaInfraccioneses = ciaInfraccioneses;
    }




}


