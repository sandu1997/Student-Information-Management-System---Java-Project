/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

/**
 *
 * @author acer
 */
public class Staff extends User {

    private String title;
    private float salary;
    private String grade;
    private String jobtype;
    private String Qualification;

    public Staff() {
        title = null;
        salary = 0;
        jobtype = null;
        Qualification = null;
    }

    public Staff(String title, float salary, String grade, String jobtype, String Qualification) {
        this.title = title;
        this.salary = salary;
        this.grade = grade;
        this.jobtype = jobtype;
        this.Qualification = Qualification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

}
