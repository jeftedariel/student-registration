/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jefte.laboratoriouno.model.Student;

/**
 *
 * @author jefte
 */
public class StudentInfo {
    String student_name, career_Name,course_name;

    public StudentInfo(String student_name, String career_Name, String course_name) {
        this.student_name = student_name;
        this.career_Name = career_Name;
        this.course_name = course_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getCareer_Name() {
        return career_Name;
    }

    public String getCourse_name() {
        return course_name;
    }
    
    
}
