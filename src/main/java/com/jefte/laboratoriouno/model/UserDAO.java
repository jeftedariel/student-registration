/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jefte
 */
public class UserDAO {

    public UserDAO() {
    }

    public void create(User user) {
        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO users ("
                    + "id, birthdate, name, role, email, password, degree) VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, user.getId());
            ps.setDate(2, user.getBirthdate());
            ps.setString(3, user.getName());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getDegree());
            ps.execute();
            JOptionPane.showMessageDialog(null, "User added succesfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error ocurred while adding a user: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public ArrayList<User> read() {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM users");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id_user");
                Date birthdate = resultSet.getDate("birthdate");
                String name = resultSet.getString("name");
                String role = resultSet.getString("id_role");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String degree = resultSet.getString("id_career");
                users.add(new User(id, birthdate, name, role, email, password, degree));

            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return users;
    }

    public void update(User user) {

        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE users SET birthdate= ?, name= ?, role= ?, email= ?, password= ?, degree= ? WHERE id= ?");
            ps.setDate(1, user.getBirthdate());
            ps.setString(2, user.getName());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getDegree());
            ps.setInt(7, user.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void delete(int id) {

        DatabaseConnection db = new DatabaseConnection();

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "User deleted succesfully");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error while deleting the user: " + e.toString());
        } finally {
            db.disconnect();
        }

    }
}
