/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

/**
 *
 * @author Helanka
 */
public class ExamsMarks {
    private String examCourseId;
    private String type;
    private String marksStuId;
    private double marks;

    public String getExamCourseId() {
        return examCourseId;
    }

    public String getType() {
        return type;
    }

    public String getMarksStuId() {
        return marksStuId;
    }

    public double getMarks() {
        return marks;
    }

    public void setExamCourseId(String examCourseId) {
        this.examCourseId = examCourseId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMarksStuId(String marksStuId) {
        this.marksStuId = marksStuId;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
    
    
}
