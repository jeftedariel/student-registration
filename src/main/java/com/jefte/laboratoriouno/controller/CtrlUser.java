/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.User;
import com.jefte.laboratoriouno.model.UserDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jefte
 */
public class CtrlUser {
    UserDAO dao = new UserDAO();
    int id;
    
    public void selectedRow(JTable table,JTextField name, JTextField role, JTextField birthdate, JTextField email, JTextField password, JTextField degree) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                role.setText((table.getValueAt(row, 2).toString()));
                birthdate.setText((table.getValueAt(row, 3).toString()));
                email.setText((table.getValueAt(row, 4).toString()));
                password.setText((table.getValueAt(row, 5).toString()));
                if(table.getValueAt(row, 6) != null){
                degree.setText(((table.getValueAt(row, 6).toString())));
                } else {
                degree.setText("N/A");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    public void loadDataStudents(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<User> users = dao.read();
        
        users.forEach(user -> {Object[] row = {
            user.getId(), 
            user.getName(), 
            user.getRole(), 
            user.getBirthdate(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getDegree()}; 
        model.addRow(row);});  
    }
}
