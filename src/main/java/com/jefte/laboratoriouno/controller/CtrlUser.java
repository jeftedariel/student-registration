/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.User;
import com.jefte.laboratoriouno.model.UserDAO;
import java.util.List;
import javax.swing.JTable;
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
