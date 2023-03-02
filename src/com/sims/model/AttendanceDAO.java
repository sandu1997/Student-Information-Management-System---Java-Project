/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author pragith
 */
public class AttendanceDAO {
    PreparedStatement pst;
    ResultSet rs;
    

    
    public boolean addAttendance(Attendance attendance){
        boolean value=false;
        
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
           
            
            pst = con.prepareStatement("insert into attendance values(?,?,?);");
        
            pst.setString(1, attendance.getStudentID());
            pst.setString(2, attendance.getSessionID());
            pst.setString(3, attendance.getAttendanceStatus());
            
            if(pst.executeUpdate()>=1){
                value=true;
            }
            
            
            
            
            
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return value;
    }
    
    

    
    public boolean updateAttendance(Attendance attendance){
        boolean value = false;
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            pst = con.prepareStatement("UPDATE attendance SET state=? WHERE at_st_id=? AND at_sessionid=?;");
           
            
            pst.setString(1, attendance.getAttendanceStatus());
            pst.setString(2, attendance.getStudentID());
            pst.setString(3, attendance.getSessionID());
            
            if(pst.executeUpdate()>=1){
                value=true;
            }
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    

    
      
      
    public boolean deleteAttendance(Attendance attendance){
        boolean value = false;
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            pst = con.prepareStatement("delete from attendance where at_st_id=? and at_sessionid=? ;");
           
            pst.setString(1, attendance.getStudentID());
            pst.setString(2, attendance.getSessionID());
            
            if(pst.executeUpdate()>=1){
                value=true;
            }
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
           
    public ArrayList<Attendance>getAllAttendanceByCourse(String courseID,String type){
        ArrayList<Attendance> allAttendanceList=new ArrayList<Attendance>();
        String sessionid = null;
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("select at_sessionid,at_st_id,date_time,state from attendance_with_session where courseid=? and type=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                
                
                attendance.setSessionID(rs.getString(1));
                attendance.setStudentID(rs.getString(2));
                attendance.setDateTime(rs.getString(3));
                if("0".equals(rs.getString(4))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                
                allAttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allAttendanceList;
        
    }
      
      
    public ArrayList<Attendance>getAllAttendanceByCourseAndStatus(String courseID,String type,String status){
        ArrayList<Attendance> allAttendanceList=new ArrayList<Attendance>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            if("All".equals(status)){
            pst = con.prepareStatement("select at_sessionid,at_st_id,date_time,state from attendance_with_session where courseid=? and type=?", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);

            }else{
            pst = con.prepareStatement("select at_sessionid,at_st_id,date_time,state from attendance_with_session where courseid=? and type=? and state=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            pst.setString(3, status);
            
            }
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                
                
                attendance.setSessionID(rs.getString(1));
                attendance.setStudentID(rs.getString(2));
                attendance.setDateTime(rs.getString(3));
                if("0".equals(rs.getString(4))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                allAttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allAttendanceList;
        
    }  

    
    public ArrayList<Attendance>getAttendanceByStudent(String courseID,String studentID,String type,String status){
        ArrayList<Attendance> allAttendanceList=new ArrayList<Attendance>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            if("All".equals(status)){
            pst = con.prepareStatement("select at_sessionid,at_st_id,date_time,state from attendance_with_session where courseid=? and type=? and at_st_id=?", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            pst.setString(3, studentID);

            }else{
            pst = con.prepareStatement("select at_sessionid,at_st_id,date_time,state from attendance_with_session where courseid=? and type=? and state=? and at_st_id=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            pst.setString(3, status);
            pst.setString(4, studentID);
            
            }
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                
                attendance.setSessionID(rs.getString(1));
                attendance.setStudentID(rs.getString(2));
                attendance.setDateTime(rs.getString(3));
                if("0".equals(rs.getString(4))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(4))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                allAttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allAttendanceList;
        
    } 
    
    
    public  ArrayList<Attendance> getAllStudentAttendance(String courseID, String dateTime,String state,String type) {
        String query1,query2;
        String sessionID=null;
        
        
        ArrayList<Attendance> attendancelist = new ArrayList<Attendance>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            
            query1="select sessionid from session where courseid=? and date_time=? and type=?;";
            pst = con.prepareStatement(query1, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    
            pst.setString(1, courseID);
            pst.setString(2, dateTime);
            pst.setString(3, type);
            
            rs=pst.executeQuery();
            while(rs.next()){
                sessionID=rs.getString(1);
            }
               
            
            query2="select * from attendance where at_sessionid=? and state=?;";
            pst = con.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            pst.setString(1, sessionID);
            pst.setString(2, state);
            
            rs=pst.executeQuery();
            
            if(rs.first()){
                Attendance attendance=new Attendance();
                
                attendance.setStudentID(rs.getString(1));
                attendance.setAttendanceStatus(rs.getString(3));
            
                attendancelist.add(attendance);
            }
           

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attendancelist;
    }
   
   
      
    
    
    
      
    //----------------------For Lecturer use(Created by Helanka)------------------------------  
    public ArrayList<Attendance> getAttendanceByCourse(String courseID,String type){
        ArrayList<Attendance> AttendanceList = new ArrayList<Attendance>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("SELECT courseid, at_st_id, date_time, hours, type, state FROM attendance, "
                    + "session WHERE courseid = ? AND type = ? AND attendance.at_sessionid =  session.sessionid;", 
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();

                attendance.setCourseID(rs.getString(1));
                attendance.setStudentID(rs.getString(2));
                attendance.setDateTime(rs.getString(3).toString());
                attendance.setHours(rs.getInt(4));
                attendance.setSessionType(rs.getString(5));
                if("0".equals(rs.getString(6))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(6))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(6))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                AttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return AttendanceList;
        
    }
    
    public ArrayList<Attendance> getAttendanceByStudentID(String studentID,String type){
        ArrayList<Attendance> AttendanceList = new ArrayList<Attendance>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("SELECT courseid, at_st_id, date_time, hours, type, state FROM attendance, "
                    + "session WHERE at_st_id = ? AND type = ? AND attendance.at_sessionid =  session.sessionid;", 
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            pst.setString(2, type);
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();

                attendance.setCourseID(rs.getString(1));
                attendance.setStudentID(rs.getString(2));
                attendance.setDateTime(rs.getString(3).toString());
                attendance.setHours(rs.getInt(4));
                attendance.setSessionType(rs.getString(5));
                if("0".equals(rs.getString(6))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(6))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(6))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                AttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return AttendanceList;
        
    }
    
    
    //For Student use  
    public ArrayList<Attendance> getStuAttendanceByCourse(String courseID,String studentID){
        ArrayList<Attendance> AttendanceList = new ArrayList<Attendance>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("SELECT courseid, date_time, type, hours, state FROM attendance, "
                    + "session WHERE courseid = ? AND at_st_id = ? AND attendance.at_sessionid =  session.sessionid;", 
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID.toUpperCase());
            pst.setString(2, studentID);
            
           
            rs=pst.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();

                attendance.setCourseID(rs.getString(1));
                attendance.setDateTime(rs.getString(2).toString());
                attendance.setSessionType(rs.getString(3));
                attendance.setHours(rs.getInt(4));
                if("0".equals(rs.getString(5))){
                    attendance.setAttendanceStatus("Absent");
                } else if ("1".equals(rs.getString(5))) {
                    attendance.setAttendanceStatus("Present");
                } else if ("2".equals(rs.getString(5))) {
                    attendance.setAttendanceStatus("medical");
                }
                
                AttendanceList.add(attendance);
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return AttendanceList;
        
    }
      
 
    
    
    
    
    
    
    
    
}
