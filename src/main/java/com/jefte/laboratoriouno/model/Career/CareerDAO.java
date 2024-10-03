/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Career;

import com.jefte.laboratoriouno.model.Course.*;
import com.jefte.laboratoriouno.model.DatabaseConnection;
import com.jefte.laboratoriouno.model.Student.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jefte
 */
public class CareerDAO {

    public CareerDAO() {
    }

    public void create(Career career) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO careers (career_id, career_name) VALUES (?, ?)");
            
            ps.setString(1, course.getCode());
            ps.setString(2, course.getName());
            ps.setByte(3, course.getMax_capacity());
            ps.setString(4, course.getDuration());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Course added succesfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while adding a course: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public ArrayList<Career> read() {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<Career> courses = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM courses");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                courses.add(new Career(
                        resultSet.getString("code"),
                        resultSet.getString("course_name"),
                        Byte.parseByte(resultSet.getString("max_capacity")),
                        resultSet.getString("duration")
                        ));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return courses;
    }

    public void update(Career course) {

        DatabaseConnection db = new DatabaseConnection();

        try  {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE courses SET code= ?, course_name= ?, max_capacity= ?, duration= ? WHERE code= ?");
            ps.setString(1, course.getCode());
            ps.setString(2, course.getName());
            ps.setByte(3, course.getMax_capacity());
            ps.setString(4, course.getDuration());
            ps.setString(5, course.getCode());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void delete(String code) {

        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM courses WHERE code=?");
            preparedStatement.setString(1, code);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Course deleted succesfully");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error while deleting a course: " + e.toString());
        } finally {
            db.disconnect();
        }

    }
}
