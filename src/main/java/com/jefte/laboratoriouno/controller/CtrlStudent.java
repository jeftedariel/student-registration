/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.controller;

import com.jefte.laboratoriouno.model.Student.Student;
import com.jefte.laboratoriouno.model.Student.StudentDAO;
import com.jefte.laboratoriouno.model.Student.StudentInfo;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class CtrlStudent {

    StudentDAO dao = new StudentDAO();
    int id;

    public int getId() {
        return id;
    }

    public void selectedRow(JTable table, JTextField name, JTextField role, JTextField birthdate, JComboBox career) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                role.setText((table.getValueAt(row, 2).toString()));
                birthdate.setText((table.getValueAt(row, 3).toString()));
                career.setSelectedItem(table.getValueAt(row, 4).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Select a row first.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select error: " + e.toString());
        }
    }

    public void loadDataStudents(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Student> students = dao.read();

        students.forEach(student -> {
            Object[] row = {
                student.getEnrollment(),
                student.getName(),
                student.getAddress(),
                student.getPhone(),
                student.getCareer_id()};
            model.addRow(row);
        });
    }
    
    public void loadStudentInfo(JTable table, int enrollment) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<StudentInfo> studentsInfo = dao.studentInfo(enrollment);

        studentsInfo.forEach(studentInfo -> {
            Object[] row = {
                studentInfo.getCourse_name(),
            };
            model.addRow(row);
        });
    }
    
    
    public void setCareers(JComboBox cbxCareers){
        cbxCareers.setModel(new javax.swing.DefaultComboBoxModel<>(this.dao.getCareers().values().toArray()));
    }
    
    public void addStudent(JTextField txtName, JTextField txtPhone, JTextField txtAddress, JComboBox cbxCareer) {
        Random rand = new Random();
        this.dao.create(new Student(rand.nextInt(1000),txtName.getText(),txtPhone.getText(), txtAddress.getText(), cbxCareer.getSelectedItem().toString()));
        
    }
    public void updatedStudent(JTextField txtName, JTextField txtPhone, JTextField txtAddress, JComboBox cbxCareer) {
            this.dao.update(new Student(this.id,txtName.getText(),txtPhone.getText(), txtAddress.getText(), cbxCareer.getSelectedItem().toString()));
    }

    public void deleteStudent(){
        this.dao.delete(this.id);
    }
    public void clearFields(JTextField txtName, JTextField txtPhone, JTextField txtAddress) {
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
    }
}
