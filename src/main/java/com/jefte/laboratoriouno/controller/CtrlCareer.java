/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.Career.Career;
import com.jefte.laboratoriouno.model.Career.CareerDAO;
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
public class CtrlCareer {

    CareerDAO dao = new CareerDAO();
    String id;

    public void selectedRow(JTable table, JTextField identifier, JTextField career) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = table.getValueAt(row, 0).toString();
                identifier.setText((table.getValueAt(row, 0).toString()));
                career.setText((table.getValueAt(row, 1).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Select a row first.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select error: " + e.toString());
        }
    }

    public void loadDataCareers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Career> careers = dao.read();

        careers.forEach(career -> {
            Object[] row = {
                career.getId(),
                career.getName()};
            model.addRow(row);
        });
    }

    public void addCareer(JTable table, JTextField identifier, JTextField career) {
        this.dao.create(new Career(identifier.getText(), career.getText()));
        
    }
    public void updatedCareer(JTable table, JTextField identifier, JTextField career) {
            this.dao.update(new Career(identifier.getText(), career.getText()));
    }

    public void deleteCareer(){
        this.dao.delete(this.id);
    }
    public void clearFields(JTextField identifier, JTextField career) {
        identifier.setText("");
        career.setText("");
    }
}
