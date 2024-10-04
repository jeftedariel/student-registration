/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Student;

import com.jefte.laboratoriouno.model.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author jefte
 */
public class StudentDAO {

    public StudentDAO() {
    }

    public void create(Student user) {
        DatabaseConnection db = new DatabaseConnection();
        String career_id = "";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT career_id from careers where career_name = ?");

            ps.setString(1, user.getCareer_id());

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                career_id = resultSet.getString("career_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while loading Career`s name: " + e.toString());
        }

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO students (enrollment, name, address, phone, career_id) VALUES (?, ?, ?, ?, ?)");

            ps.setInt(1, user.getEnrollment());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, career_id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "User added succesfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while adding a user: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public ArrayList<Student> read() {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<Student> users = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT s.*, c.career_name FROM students s JOIN careers c ON s.career_id = c.career_id;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(new Student(
                        resultSet.getInt("enrollment"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("career_name")));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return users;
    }

    public void update(Student user) {

        DatabaseConnection db = new DatabaseConnection();
        String career_id = "";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT career_id from careers where career_name = ?");

            ps.setString(1, user.getCareer_id());

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                career_id = resultSet.getString("career_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while loading Career`s name: " + e.toString());
        }

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE students SET name= ?, address= ?, phone= ?, career_id= ? WHERE enrollment= ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, career_id);
            ps.setInt(5, user.getEnrollment());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void delete(int id) {

        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM students WHERE enrollment=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "User deleted succesfully");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error while deleting the user: " + e.toString());
        } finally {
            db.disconnect();
        }

    }
    
    public HashMap<String, String> getCareers(){
        DatabaseConnection db = new DatabaseConnection();
        HashMap<String,String> careers = new HashMap<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM careers");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                careers.put(resultSet.getString("career_id"), resultSet.getString("career_name"));
                
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return careers;
    }
    
    
    public ArrayList<StudentInfo> studentInfo(int enrollment) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<StudentInfo> si = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT s.name AS student_name, c.career_name, crs.course_name FROM students s JOIN careers c ON s.career_id = c.career_id JOIN student_course sc ON s.enrollment = sc.enrollment JOIN courses crs ON sc.code = crs.code WHERE s.enrollment = ?");
            ps.setInt(1, enrollment);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                si.add(new StudentInfo(
                        resultSet.getString("student_name"),
                        resultSet.getString("career_name"),
                        resultSet.getString("course_name")));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return si;
    }
}
