/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Course;

import com.jefte.laboratoriouno.model.Student.*;

/**
 *
 * @author jefte
 */
public class Course {
    private byte max_capacity;
    private String code, name, duration;

    public Course(String code, String name,byte max_capacity, String duration) {
        this.max_capacity = max_capacity;
        this.code = code;
        this.name = name;
        this.duration = duration;
    }
    
    
    public byte getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(byte max_capacity) {
        this.max_capacity = max_capacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    
    
}
