/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.Career.CareerCourseDAO;
import com.jefte.laboratoriouno.model.enrollCourse.StudentCourse;
import javax.swing.JComboBox;

/**
 *
 * @author jefte
 */
public class CtrlCareerCourse {
    
    CareerCourseDAO dao = new CareerCourseDAO();

    public void addCourse(JComboBox course_name, String career_id) {
        this.dao.create(course_name.getSelectedItem().toString(),career_id);    
    }
    
    public void setCourses(JComboBox courses, String code){
       courses.setModel(new javax.swing.DefaultComboBoxModel<>(this.dao.getCourses(code).stream().map(i -> i).toArray()));
    }
}
