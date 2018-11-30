/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

/**
 *
 * @author jcarr
 */
public class NewClass {

    public static void main(String[] args) {
        try {
            Conexion net = new Conexion();
            net.conectar();
            new Consultas().ConsultaPersonaPorDocumento(net.getCon(), "1082951997").getPerNombres();
            System.out.println(new Consultas().ConsultaPersonaPorDocumento(net.getCon(), "1082951997").getPerNombres());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
