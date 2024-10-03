/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.enrollCourse.StudentCourse;
import com.jefte.laboratoriouno.model.enrollCourse.StudentCourseDAO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author jefte
 */
public class CtrlStudentCourse {
    
    StudentCourseDAO dao = new StudentCourseDAO();

    public void addCourse(JComboBox enrollment, JComboBox code) {
        this.dao.create(new StudentCourse(enrollment.getSelectedItem().toString(), code.getSelectedItem().toString()));    
    }
    
    public void setEnrollments(JComboBox enrollment){
        enrollment.setModel(new javax.swing.DefaultComboBoxModel<>(this.dao.getStudentEnrollment().values().toArray()));
    }
    
    public void setCodes(JComboBox enrollment, JComboBox code){
        code.setModel(new javax.swing.DefaultComboBoxModel<>(this.dao.getStudentCodes(enrollment).stream().map(i -> i).toArray()));
    }
    
    public void getCount(JComboBox code, JLabel label){
       label.setText("Students Registered: "+ String.valueOf(this.dao.countCourseStudents(code.getSelectedItem().toString())));
    }
    
    public void checkEnrolled(JComboBox enrollment, JComboBox code, JButton Register, JLabel Warning){
        Register.setEnabled(!this.dao.isEnrolled(enrollment.getSelectedItem().toString(), code.getSelectedItem().toString()));
        Warning.setVisible(this.dao.isEnrolled(enrollment.getSelectedItem().toString(), code.getSelectedItem().toString()));
    }
}
