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
public class User {

    private String UserID;
    private String firstName;
    private String lastname;
    private String usernic;
    private String email;
    private int phone;
    private String dob;
    private String gender;
    private String address;
    private String department;

    public User() {
        UserID = null;
        firstName = null;
        lastname = null;
        usernic = null;
        email = null;
        phone = 0;
        dob = null;
        gender = null;
        address = null;
        department = null;
    }

    public User(String UserID, String firstName, String lastname, String usernic, String email, int phone, String dob, String gender, String address, String department) {
        this.UserID = UserID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.usernic = usernic;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getUsernic() {
        return usernic;
    }

    public void setUsernic(String usernic) {
        this.usernic = usernic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
