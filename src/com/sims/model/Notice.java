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
public class Notice {
    
    private String ID;
    private String content;
    private String title;
    private String publisher;

    public Notice() {
        ID = null;
        content = null;
        title = null;
        publisher = null;
    }

    public Notice(String ID, String content, String title, String publisher) {
        this.ID = ID;
        this.content = content;
        this.title = title;
        this.publisher = publisher;
    }

    public String getID() {
        return ID;
    }

    public void setID(String dD) {
        this.ID = dD;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    
}
