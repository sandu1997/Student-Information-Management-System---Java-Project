/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class UserDAO {

    private int num;
    PreparedStatement pst;
    ResultSet rs;

    public boolean verifyPassword(String username, String pwd) {
        boolean valid = false;
        String password, quary;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            quary = "select password from login where username=?;";

            pst = con.prepareStatement(quary, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, username);

            rs = pst.executeQuery();

            if (rs.first()) {
                password = rs.getString(1);

                valid = password.equals(pwd);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    public String verifyUserName(String username) {
        String userid = null;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select log_userid from login where username=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, username);

            rs = pst.executeQuery();

            if (rs.first()) {
                userid = rs.getString(1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userid;
    }

    public String getUserType(String userid) {
        String type = null;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select st_job_type from staf where stfid=?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, userid);

            rs = pst.executeQuery();

            if (rs.first()) {
                type = rs.getString(1);
            } else {
                type = "student";
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return type;
    }

    public boolean saveUser(User user) {
        boolean value = false;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            java.sql.Date sqlDate = getsqldate(user.getDob());

            pst = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?);");
            pst.setString(1, user.getUserID());
            pst.setString(2, user.getFirstName());
            pst.setString(3, user.getLastname());
            pst.setString(4, user.getUsernic());
            pst.setInt(5, user.getPhone());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getAddress());
            pst.setDate(8, sqlDate);
            pst.setString(9, user.getGender());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean updateUser(User user) {
        boolean value = false;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            java.sql.Date sqlDate = getsqldate(user.getDob());

            pst = con.prepareStatement("update user set user_fname=?,user_lname=?,user_nic=?,user_contact=?,user_email=?,user_address=?,user_dob=?,user_gender=? where userid=?;");
            pst.setString(9, user.getUserID());
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getUsernic());
            pst.setInt(4, user.getPhone());
            pst.setString(5, user.getEmail());
            pst.setString(6, user.getAddress());
            pst.setDate(7, sqlDate);
            pst.setString(8, user.getGender());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }
    
    public boolean deleteUser(User user) {
        boolean value = false;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from user where userid=?;");
            pst.setString(1, user.getUserID());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }
    
    public User getUser(String userid) {
        User user = null;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from user where userid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, userid);
            
            rs=pst.executeQuery();

            if (rs.first()) {
                user = new User();
                
                user.setUserID(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setUsernic(rs.getString(4));
                user.setPhone(rs.getInt(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setDob(rs.getString(8));
                user.setGender(rs.getString(9));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
    
    public  ArrayList<User> getAllUser() {
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from user;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                
                user.setUserID(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setUsernic(rs.getString(4));
                user.setPhone(rs.getInt(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setDob(rs.getString(8));
                user.setGender(rs.getString(9));
                
                userlist.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userlist;
    }
    
    public  ArrayList<User> getAllUser(String usertype) {
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            String sql = null;
            
            switch(usertype){
                case "Admin":
                case "admin":
                    sql = "select * from user,staf where userid=stfid and st_job_type = 'Admin';";
                    break;
                case "Lecturer":
                case "lecturer":
                    sql = "select * from user,staf where userid=stfid and st_job_type = 'Lecturer';";
                    break;
                case "to":
                case "TO":
                    sql = "select * from user,staf where userid=stfid and st_job_type = 'Technical Officer';";
                    break;
                case "Student":
                case "stdent":
                    sql = "select * from user,student where userid=studentid;";
                    break;
                default:
                    sql = "select * from user;";
                    
            }

            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                
                user.setUserID(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setUsernic(rs.getString(4));
                user.setPhone(rs.getInt(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setDob(rs.getString(8));
                user.setGender(rs.getString(9));
                
                userlist.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userlist;
    }
    
    public  ArrayList<User> searchUserbyID(String id) {
        ArrayList<User> userlist = new ArrayList<User>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from user where userid like '%" + id + "%';", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                
                user.setUserID(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastname(rs.getString(3));
                user.setUsernic(rs.getString(4));
                user.setPhone(rs.getInt(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setDob(rs.getString(8));
                user.setGender(rs.getString(9));
                
                userlist.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userlist;
    }
    
    public int getUserCount(String usertype) {
        int user = 0;
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            String sql = null;
            
            switch(usertype){
                case "Admin":
                case "admin":
                    sql = "select count(stfid) from staf where st_job_type='Admin';";
                    break;
                case "Lecturer":
                case "lecturer":
                    sql = "select count(stfid) from staf where st_job_type='Lecturer';";
                    break;
                case "to":
                case "TO":
                    sql = "select count(stfid) from staf where st_job_type='Technical Officer';";
                    break;
                case "Student":
                case "stdent":
                    sql = "select count(studentid) from student;";
                    break;
                default:
                    sql = "select count(userid) from user;";
                    
            }
            

            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            rs=pst.executeQuery();

            if (rs.first()) {
                user = rs.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
        
    
    private java.sql.Date getsqldate(String date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//
//        Date Date_;
        java.sql.Date sqlDate = null;
        //Date_ = (Date) sdf.parse(date);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        sqlDate = java.sql.Date.valueOf(localDate);

        return sqlDate;
    }

}
