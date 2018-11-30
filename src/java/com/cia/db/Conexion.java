/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jcarr
 */
public class Conexion {

    private transient Connection con;
    private String driver;
    private String url;
    private String user = "";
    private String password = "";

    public boolean testConexion() {
        return this.con != null;
    }

    public void conectar() throws SQLException, IOException, Exception {
        setDriver("org.postgresql.Driver");
        setUrl("jdbc:postgresql://cia2.ccqavp4kwsxo.us-east-2.rds.amazonaws.com:5432/cia2");
        setUser("jeison_01");
        String pass = "", encript = "";
        setPassword("1082951997");

        if (!testConexion()) {
            if (driver == null) {
                throw new SQLException("No se ha definido el driver de conexion");
            }
            if (url == null) {
                throw new SQLException("No se ha especificado la url para la conexion");
            }
            try {
                Class.forName(driver).newInstance();
            } catch (Exception e) {
                throw new IOException("Error! No se ha logrado cargar el driver especificado");
            }
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
        }
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
