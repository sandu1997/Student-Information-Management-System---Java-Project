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
public class Session {
    
    private String sessionID;
    private float hours;
    private String type;
    private String dateTime;
    private String courseID;
    private float totalHours;

    
    //------------getters----------

    public float getTotalHours() {
        return totalHours;
    }
    
    
    
    public String getSessionID() {
        return sessionID;
    }

    public float getHours() {
        return hours;
    }

    public String getType() {
        return type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCourseID() {
        return courseID;
    }

    
    
    //---------setters------------------
    
    
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }
    
    
    
    
    
    
}
