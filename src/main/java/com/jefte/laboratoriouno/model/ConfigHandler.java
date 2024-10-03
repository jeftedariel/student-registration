/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model;

import java.io.InputStream;
import java.util.Map;
import javax.swing.JOptionPane;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author jefte
 */
public class ConfigHandler {
    //in here we'll store everything from the config.yaml
    Map<String, Object> data;
    
    public ConfigHandler() {
        this.load();
    }
    
    private void load() {
        Yaml yaml = new Yaml();
        //Loading the config file
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yml");
            if (inputStream == null) {
                throw new RuntimeException("Couldn't find config.yml.");
            }
            //Store what it found
            this.data = yaml.load(inputStream);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error while loading config.yaml file..." + e);
            System.out.println(e);
        }
    }

    public DatabaseConf getDatabaseConfig() {
        //It tooks only de database header data
        Map<String, Object> dbConfig = (Map<String, Object>) this.data.get("database");
        //I prefer doing it with a specific object bc of getters and tosring
        
        return new DatabaseConf(
                String.valueOf(dbConfig.get("host")),
                String.valueOf(dbConfig.get("database")),
                String.valueOf(dbConfig.get("username")),
                String.valueOf(dbConfig.get("password")),
                (int) dbConfig.get("port")
        );
    }

    
}
