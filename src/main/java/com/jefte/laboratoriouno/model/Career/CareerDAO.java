/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Career;

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
public class CareerDAO {

    public CareerDAO() {
    }

    public void create(Career career) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO careers (career_id, career_name) VALUES (?, ?)");
            
            ps.setString(1, career.getId());
            ps.setString(2, career.getName());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Career added succesfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while adding a course: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public ArrayList<Career> read() {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<Career> careers = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM careers");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                careers.add(new Career(
                        resultSet.getString("career_id"),
                        resultSet.getString("career_name")
                        ));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return careers;
    }

    public void update(Career career) {

        DatabaseConnection db = new DatabaseConnection();

        try  {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE careers SET career_id= ?, career_name= ? WHERE career_id= ?");
            ps.setString(1, career.getId());
            ps.setString(2, career.getName());
            ps.setString(3, career.getId());
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM careers WHERE career_id=?");
            preparedStatement.setString(1, code);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Career deleted succesfully");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error while deleting a course: " + e.toString());
        } finally {
            db.disconnect();
        }

    }
}
