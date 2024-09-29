/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model;

/**
 *
 * @author jefte
 */
public class DatabaseConf {
    //Just a simple object to get information with the correct datatype...
    private String host, username, password, database;
    private int port;

    public DatabaseConf(String host,String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }
    
    public DatabaseConf(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = 3306;
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public String toString() {
        return "DatabaseConf{" + "host=" + host + ", database=" + database + ", username=" + username + ", password=" + password + ", port=" + port + '}';
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
          
}
