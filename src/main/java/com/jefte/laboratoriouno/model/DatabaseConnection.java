/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
        
/**
 *
 * @author jefte
 */
public class DatabaseConnection {
    ConfigHandler ch = new ConfigHandler();
    String url = "jdbc:mariadb://"+ch.getDatabaseConfig().getHost()+":" + ch.getDatabaseConfig().getPort() + "/" + ch.getDatabaseConfig().getDatabase();

    Connection connection = null;

    public DatabaseConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, ch.getDatabaseConfig().getUsername(), ch.getDatabaseConfig().getPassword());
            System.out.println("Connection succesfull");
        } catch (ClassNotFoundException e) {
            System.err.println("Error while loading JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error during the connection: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection: " + e.getMessage());
            }
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
