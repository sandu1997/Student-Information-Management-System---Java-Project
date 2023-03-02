/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Student extends User {

    private int year;
    private int semester;
    private String state;
    private double gpa;

    public Student() {
        year = 0;
        semester = 0;
        state = null;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getSGPA(ArrayList<Course> courslist) {
        if (!courslist.isEmpty()) {
            double sumCiGi = 0, sumCi = 0;
            CourseDAO cdao = new CourseDAO();

            for (Course c1 : courslist) {
                sumCiGi += c1.getCredit() * getGradePoint(c1.getGrade());
                sumCi += c1.getCredit();
            }
            return sumCiGi / sumCi;
        } else {
            return -100;
        }
    }

    public double getCGPA(ArrayList<Course> courslist) {
        return getSGPA(courslist);
    }

    public void setGpa(ArrayList<Course> courslist) {
        double input = getSGPA(courslist);
        this.gpa = Math.round(input * 100.0) / 100.0;
    }
    
    public double getGpa() {
        return gpa;
    }

    private static double getGradePoint(String grade) {
        double gp = 0;

        if ("A+".equals(grade) || "A".equals(grade)) {
            gp = 4.00;
        } else if ("A-".equals(grade)) {
            gp = 3.70;
        } else if ("B+".equals(grade)) {
            gp = 3.30;
        } else if ("B".equals(grade)) {
            gp = 3.00;
        } else if ("B-".equals(grade)) {
            gp = 2.70;
        } else if ("C+".equals(grade)) {
            gp = 2.30;
        } else if ("C".equals(grade)) {
            gp = 2.00;
        } else if ("C-".equals(grade)) {
            gp = 1.70;
        } else if ("D+".equals(grade)) {
            gp = 1.30;
        } else if ("D".equals(grade)) {
            gp = 1.00;
        } else {
            gp = 0;
        }

        return gp;
    }

}
