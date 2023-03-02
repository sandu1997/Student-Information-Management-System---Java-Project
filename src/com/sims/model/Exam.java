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
public class Exam {
    private String examId;
    private String type;
    private String examCourseId;

    public Exam() {
    }

    public Exam(String examId, String type, String examCourseId) {
        this.examId = examId;
        this.type = type;
        this.examCourseId = examCourseId;
    }

    public String getExamId() {
        return examId;
    }

    public String getType() {
        return type;
    }

    public String getExamCourseId() {
        return examCourseId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExamCourseId(String examCourseId) {
        this.examCourseId = examCourseId;
    }
    
}
