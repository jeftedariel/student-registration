/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.Course.Course;
import com.jefte.laboratoriouno.model.Course.CourseDAO;
import java.util.List;
import javax.swing.JButton;
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
public class CtrlCourse {

    CourseDAO dao = new CourseDAO();
    String id;

    public void selectedRow(JTable table, JTextField code, JTextField course, JTextField maxCap, JTextField duration) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = table.getValueAt(row, 0).toString();
                code.setText((table.getValueAt(row, 0).toString()));
                course.setText((table.getValueAt(row, 1).toString()));
                maxCap.setText((table.getValueAt(row, 2).toString()));
                duration.setText((table.getValueAt(row, 3).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Select a row first.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select error: " + e.toString());
        }
    }

    public void loadDataCourses(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Course> courses = dao.read();

        courses.forEach(student -> {
            Object[] row = {
                student.getCode(),
                student.getName(),
                student.getMax_capacity(),
                student.getDuration()};
            model.addRow(row);
        });
    }

    public void addCourse(JTable table, JTextField code, JTextField course, JTextField maxCap, JTextField duration) {
        this.dao.create(new Course(code.getText(),course.getText(), Byte.parseByte(maxCap.getText()), duration.getText()));
        
    }
    public void updatedCourse(JTable table, JTextField code, JTextField course, JTextField maxCap, JTextField duration) {
            this.dao.update(new Course(code.getText(),course.getText(), Byte.parseByte(maxCap.getText()), duration.getText()));
    }

    public void deleteCourse(){
        this.dao.delete(this.id);
    }
    public void clearFields(JTextField code, JTextField course, JTextField maxCap, JTextField duration) {
        code.setText("");
        course.setText("");
        maxCap.setText("");
        duration.setText("");
    }
    
    public void validate(JButton add, JButton edit, JTextField code, JTextField course, JTextField maxcap, JTextField duration, String regex, String regex2){
        add.setEnabled(code.getText().matches(regex) && course.getText().matches(regex) && maxcap.getText().matches(regex2) && duration.getText().matches(regex));
        edit.setEnabled(code.getText().matches(regex) && course.getText().matches(regex) && maxcap.getText().matches(regex2) && duration.getText().matches(regex));
    }
}
