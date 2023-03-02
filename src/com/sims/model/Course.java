/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

/**
 *
 *
 */
public class Course {
    
    private String courseid;
    private String courseName;
    private int credit;
    private String course_dpt;
    private int courseyear;
    private int courseSemester;
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCourseyear() {
        return courseyear;
    }

    public void setCourseyear(int courseyear) {
        this.courseyear = courseyear;
    }

    public int getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(int courseSemester) {
        this.courseSemester = courseSemester;
    }
    
    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCourse_dpt() {
        return course_dpt;
    }

    public void setCourse_dpt(String course_dpt) {
        this.course_dpt = course_dpt;
    }
    
}
