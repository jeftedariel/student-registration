/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.enrollCourse;

import com.jefte.laboratoriouno.model.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author jefte
 */
public class StudentCourseDAO {

    public StudentCourseDAO() {
    }

    public void create(StudentCourse sc) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO student_course (enrollment, code) VALUES (?, (SELECT code FROM courses WHERE course_name = ?));");

            ps.setString(1, sc.getEnrollment());
            ps.setString(2, sc.getCode());
            ps.execute();
            JOptionPane.showMessageDialog(null, "You`ve being enrolled succesfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while registering a course: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public ArrayList<StudentCourse> read() {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<StudentCourse> studentCourses = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM student_course");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                studentCourses.add(new StudentCourse(
                        resultSet.getString("enrollment"),
                        resultSet.getString("code")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return studentCourses;
    }

    public int countCourseStudents(String code) {
        DatabaseConnection db = new DatabaseConnection();
        int total = 0;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT COUNT(*) FROM student_course sc JOIN courses c ON sc.code = c.code WHERE c.course_name = ?;");
            ps.setString(1, code);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                total = Integer.parseInt(resultSet.getString("count(*)"));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return total;

    }

    public boolean isEnrolled(String enrollment, String code) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT COUNT(*) FROM student_course sc JOIN courses c ON sc.code = c.code WHERE sc.enrollment = ? AND c.course_name = ?;");
            ps.setString(1, enrollment);
            ps.setString(2, code);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (Integer.parseInt(resultSet.getString("count(*)")) > 0) ? true : false;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return false;
    }
    
    public ArrayList<String> getStudentEnrollment(){
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> studentEnrollment = new ArrayList<>();
        
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT enrollment FROM students");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                studentEnrollment.add(resultSet.getString("enrollment"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
            return studentEnrollment;
        }
    }
    
    public ArrayList<String> getStudentCourses(JComboBox enrollment){
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> studentCodes = new ArrayList<>();
        String career_id="";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT career_id FROM students where enrollment = ?");
            
            ps.setString(1, enrollment.getSelectedItem().toString());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
               career_id= resultSet.getString("career_id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } 
        
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT c.course_name FROM career_courses cc JOIN courses c ON cc.code = c.code WHERE cc.career_id = ?");
            ps.setString(1, career_id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                studentCodes.add(
                        resultSet.getString("course_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
            return studentCodes;
        }
        
    }
    
            

}
