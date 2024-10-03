/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.enrollCourse;

import com.jefte.laboratoriouno.model.Career.*;
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
public class StudentCourseDAO {

    public StudentCourseDAO() {
    }

    public void create(StudentCourse sc) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO student_course (enrollment, code) VALUES (?, ?)");

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
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT COUNT(*) FROM student_course where code = ?");
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
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT COUNT(*) FROM student_course where enrollment = ? and code = ? ");
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

}
