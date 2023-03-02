/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

/**
 *
 * @author pragith
 */
public class Medical {
    
    private String medicalRefNo;
    private String date;
    private String type;
    private int hours;
    private String courseID;
    private String courseType;
    private String studentID;
    private String medicalSubmitDate;
    private String medicalStartDate;
    private String medicalEndDate;
    private String medicalStatus;

    public String getMedicalRefNo() {
        return medicalRefNo;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getHours() {
        return hours;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseType() {
        return courseType;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getMedicalSubmitDate() {
        return medicalSubmitDate;
    }

    public String getMedicalStartDate() {
        return medicalStartDate;
    }

    public String getMedicalEndDate() {
        return medicalEndDate;
    }

    public String getMedicalStatus() {
        return medicalStatus;
    }
    
    
    
    
    
    
    
    
    
    //---------- setters -----------

    public void setMedicalRefNo(String medicalRefNo) {
        this.medicalRefNo = medicalRefNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setMedicalSubmitDate(String medicalSubmitDate) {
        this.medicalSubmitDate = medicalSubmitDate;
    }

    public void setMedicalStartDate(String medicalStartDate) {
        this.medicalStartDate = medicalStartDate;
    }

    public void setMedicalEndDate(String medicalEndDate) {
        this.medicalEndDate = medicalEndDate;
    }

    public void setMedicalStatus(String medicalStatus) {
        this.medicalStatus = medicalStatus;
    }

   

    
    
    
    
    
    
    
}
