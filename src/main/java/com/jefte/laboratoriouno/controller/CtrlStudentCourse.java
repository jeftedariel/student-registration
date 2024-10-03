/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.DatabaseConnection;
import com.jefte.laboratoriouno.model.enrollCourse.StudentCourse;
import com.jefte.laboratoriouno.model.enrollCourse.StudentCourseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> studentCourses = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT enrollment FROM students");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(
                        resultSet.getString("enrollment")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        
        enrollment.setModel(new javax.swing.DefaultComboBoxModel<>(studentCourses.stream().map(i -> i).toArray()));
    }
    
    public void setCodes(JComboBox code){
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> studentCourses = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT code FROM courses");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(
                        resultSet.getString("code")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        
        code.setModel(new javax.swing.DefaultComboBoxModel<>(studentCourses.stream().map(i -> i).toArray()));
    }
    
    public void getCount(JComboBox code, JLabel label){
        label.setText("Students Registered: "+ String.valueOf(this.dao.countCourseStudents(code.getSelectedItem().toString())));
    }
    
    public void checkEnrolled(JComboBox enrollment, JComboBox code, JButton Register, JLabel Warning){
        Register.setEnabled(!this.dao.isEnrolled(enrollment.getSelectedItem().toString(), code.getSelectedItem().toString()));
        Warning.setVisible(this.dao.isEnrolled(enrollment.getSelectedItem().toString(), code.getSelectedItem().toString()));
    }
}
