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
public class Marks {
    private double marks;
    private String marksStuId;
    private String marksExamId;

    public Marks() {
    }

    public Marks(double marks, String marksStuId) {
        this.marks = marks;
        this.marksStuId = marksStuId;
    }

    public Marks(double marks, String marksStuId, String marksExamId) {
        this.marks = marks;
        this.marksStuId = marksStuId;
        this.marksExamId = marksExamId;
    }

    public double getMarks() {
        return marks;
    }

    public String getMarksStuId() {
        return marksStuId;
    }

    public String getMarksExamId() {
        return marksExamId;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setMarksStuId(String marksStuId) {
        this.marksStuId = marksStuId;
    }

    public void setMarksExamId(String marksExamId) {
        this.marksExamId = marksExamId;
    }    
}
