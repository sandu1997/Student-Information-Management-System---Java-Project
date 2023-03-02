/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class StudentDAO extends UserDAO {

    public boolean saveStudent(Student student) {
        boolean value = false;

        if (saveUser(student)) {
            try {
                Connection con = DBConnectionUtil.getDBConnection();

                pst = con.prepareStatement("insert into student values(?,?,?,?);");
                pst.setString(1, student.getUserID());
                pst.setInt(2, student.getYear());
                pst.setInt(3, student.getSemester());
                pst.setString(4, student.getDepartment());

                if (pst.executeUpdate() >= 1) {
                    value = true;
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return value;
    }

    public boolean updateStudent(Student student) {
        boolean value = false;

        try {
            updateUser(student);

            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("update student set year=?,semester=?,stnd_dpt=? where studentid=?;");
            pst.setString(4, student.getUserID());
            pst.setInt(1, student.getYear());
            pst.setInt(2, student.getSemester());
            pst.setString(3, student.getDepartment());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean deleteStudent(Student user) {
        boolean value = false;
        try {
            deleteUser(user);
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from student where studentid=?;");
            pst.setString(1, user.getUserID());

            if (pst.executeUpdate() >= 1) {

                deleteUser(user);
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean deleteStudentbyUser(User user) {
        boolean value = false;
        if (deleteUser(user)) {
            try {

                Connection con = DBConnectionUtil.getDBConnection();

                pst = con.prepareStatement("delete from student where studentid=?;");
                pst.setString(1, user.getUserID());

                if (pst.executeUpdate() >= 1) {

                    deleteUser(user);
                    value = true;
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return value;
    }

    public Student getStudent(String userid) {
        Student student = new Student();
        User user = getUser(userid);

        student.setUserID(user.getUserID());
        student.setFirstName(user.getFirstName());
        student.setLastname(user.getLastname());
        student.setUsernic(user.getUsernic());
        student.setPhone(user.getPhone());
        student.setEmail(user.getEmail());
        student.setAddress(user.getAddress());
        student.setDob(user.getDob());
        student.setGender(user.getGender()); 
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from student where studentid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, userid);

            rs = pst.executeQuery();

            if (rs.first()) {
                student.setYear(rs.getInt(2));
                student.setSemester(rs.getInt(3));
                student.setDepartment(rs.getString(4));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student;
    }

    public ArrayList<Student> getAllStudent(int year, int sem) {
        ArrayList<Student> studentlist = new ArrayList<Student>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select *  from user,student where userid=studentid and year=? and semester=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            pst.setInt(1, year);
            pst.setInt(2, sem);

            rs = pst.executeQuery();

            while (rs.next()) {
                Student student = new Student();

                student.setUserID(rs.getString(1));
                student.setFirstName(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setUsernic(rs.getString(4));
                student.setPhone(rs.getInt(5));
                student.setEmail(rs.getString(6));
                student.setAddress(rs.getString(7));
                student.setDob(rs.getString(8));
                student.setGender(rs.getString(9));
                student.setYear(rs.getInt(11));
                student.setSemester(rs.getInt(12));
                student.setDepartment(rs.getString(13));

                studentlist.add(student);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentlist;
    }
    
    public ArrayList<Student> getAllStudent(int year) {
        ArrayList<Student> studentlist = new ArrayList<Student>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select *  from user,student where userid=studentid and year=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            pst.setInt(1, year);

            rs = pst.executeQuery();

            while (rs.next()) {
                Student student = new Student();

                student.setUserID(rs.getString(1));
                student.setFirstName(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setUsernic(rs.getString(4));
                student.setPhone(rs.getInt(5));
                student.setEmail(rs.getString(6));
                student.setAddress(rs.getString(7));
                student.setDob(rs.getString(8));
                student.setGender(rs.getString(9));
                student.setYear(rs.getInt(11));
                student.setSemester(rs.getInt(12));
                student.setDepartment(rs.getString(13));

                studentlist.add(student);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentlist;
    }
    
    
    
    // pageeth added part
    
   
     public ArrayList<Student> getStudentByDepartment(String year,String department){
        ArrayList<Student> studentIDlist=new ArrayList<Student>();
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select studentid from student where stnd_dpt=? and year=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, department);
            pst.setString(2, year);
            
            rs=pst.executeQuery();

            while(rs.next()) {
         
                Student student = new Student();
                
                student.setUserID(rs.getString(1));
                studentIDlist.add(student);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentIDlist;
        
    } 
    
    
    public  ArrayList<Student> studentYear() throws SQLException {
        ArrayList<Student> yearList = new ArrayList<Student>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select DISTINCT year from student;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=pst.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setYear(rs.getInt(1));
                yearList.add(student);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return yearList;
    }
    
   
}
