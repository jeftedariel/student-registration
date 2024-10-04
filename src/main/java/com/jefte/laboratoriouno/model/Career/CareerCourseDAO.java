/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Career;

import com.jefte.laboratoriouno.model.Course.Course;
import com.jefte.laboratoriouno.model.enrollCourse.*;
import com.jefte.laboratoriouno.model.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jefte
 */
public class CareerCourseDAO {

    public CareerCourseDAO() {
    }

    public void create(String course_name, String career_id) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO career_courses (career_id, code) VALUES (?, (SELECT code FROM courses WHERE course_name = ?));");

            ps.setString(1, career_id);
            ps.setString(2, course_name);
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

    public ArrayList<String> getCourses(String course_name) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> UnsignedCourses = new ArrayList<>();
        String career_id = "";
        
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT career_id from careers where career_name = ?");

            ps.setString(1, course_name);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                career_id = resultSet.getString("career_id");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while loading Career`s name: " + e.toString());
        }
        
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT c.code, c.course_name FROM courses c LEFT JOIN career_courses cc on c.code = cc.code AND cc.career_id = ? WHERE cc.code is null");
            ps.setString(1, career_id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                UnsignedCourses.add(
                        resultSet.getString("course_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
            return UnsignedCourses;
        }

    }

    public Course getCourse(String course_name) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM courses WHERE course_name = ?");
            ps.setString(1, course_name);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                return new Course(
                        resultSet.getString("code"),
                        resultSet.getString("course_name"),
                        resultSet.getByte("max_capacity"),
                        resultSet.getString("duration")
                );
            }
        } catch(SQLException e){
            System.err.println("Error: "+ e);
                 
                    
        } finally{
            db.disconnect();
            return null;
        }

    }

}
