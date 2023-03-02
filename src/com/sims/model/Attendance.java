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
public class Attendance {
    
    private String attendanceStatus;
    private int hours;
    private String sessionID;
    private String dateTime;
    private String studentID;
    private String courseID;
    private float totalHours;
    private String sessionType;
    
    public int getHours(){
        return hours;
    }

    public float getTotalHours() {
        return totalHours;
    }

//----------getters-----------
    public String getSessionType() {
        return sessionType;
    }

    public String getStudentID() {
        return studentID;
    }
    
    public String getCourseID(){
        return courseID;
    }
    
    public String getAttendanceStatus(){
        return attendanceStatus;
    }
    
    public String getSessionID(){
        return sessionID;
    }
    
    public String getDateTime(){
        return dateTime;
    }

    //----------setters-------------------

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
    
    
    public void setAttendanceStatus(String attendanceStatus){
        this.attendanceStatus = attendanceStatus;
    }
    
    public void setHours(int hours){
        this.hours = hours;
    }
        
    public void setSessionID(String sessionType){
        this.sessionID = sessionType;
    }
        
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }
        
    public void setStudentID(String studentID){
        this.studentID = studentID;
    }
        
    public void setCourseID(String courseID){
        this.courseID = courseID;
    }
    
    public void setTotalHours(float totalHours){
        this.totalHours = totalHours;
    }

    
   
    
    
    public void getAligibility(String studentID, String courseID){
        Session session = new Session();
        session.getTotalHours();
    //not completed....
    }
    
    
    
    
    
    
    
}
