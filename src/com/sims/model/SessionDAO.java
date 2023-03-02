/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pragith
 */
public class SessionDAO {
    
    PreparedStatement pst;
    ResultSet rs;

    
    public boolean setSession(Session session){
        boolean value=false;
        try {
            
            
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            
            pst = con.prepareStatement("insert into session values(?,?,?,?,?);");
            
            pst.setString(1, session.getSessionID());
            pst.setFloat(2, session.getHours());
            pst.setString(3, session.getType());
            pst.setString(4, session.getDateTime());
            pst.setString(5, session.getCourseID());
           

            if (pst.executeUpdate() >= 1) {
                value = true;
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        return value;
    
 
    }
    



    public Session getSession(String courseID){
        Session session=null;
        try {
           Connection con = DBConnectionUtil.getDBConnection();
            
            pst = con.prepareStatement("select * from session where courseid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            pst.setString(1, courseID);
            
            rs=pst.executeQuery();
            
            while(rs.next()){
           
                session=new Session();
                
                session.setCourseID(rs.getString(1));
                session.setHours(rs.getFloat(2));
                session.setType(rs.getString(3));
                session.setDateTime(rs.getString(4));
                session.setCourseID(rs.getString(5));

            }

            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        return session;
    
 
    }
    
    
    public float getTotalHours(String courseID){
        float total=0;
        try {
           Connection con = DBConnectionUtil.getDBConnection();
            
            pst = con.prepareStatement("select hours from session where courseid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            pst.setString(1, courseID);
            
            rs=pst.executeQuery();
            
            while(rs.next()){

                total+=rs.getFloat(2);
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return total;
    
 
    }
    
    
    
    public String viewSessionID(String dateTime,String courseID){
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select sessionid from session where date_time=? and courseid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, dateTime);
            pst.setString(2, courseID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                //session = new Session();
               String id=rs.getString(1);
               return id; 
                
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return null;
        
    }
    
        
    public ArrayList<Session>viewAllSessions(){
        ArrayList<Session> sessionList=new ArrayList<Session>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select sessionid,date_time,courseid from session ORDER BY sessionid DESC;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
           
            rs=pst.executeQuery();

            while (rs.next()) {
                Session session = new Session();
                
                session.setSessionID(rs.getString(1));
                session.setDateTime(rs.getString(2));
                session.setCourseID(rs.getString(3));
                
                
                sessionList.add(session);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessionList;
        
    } 
        
        
        
        
        
        
        
        
        
        
        
        
}