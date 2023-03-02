/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author pragith
 */
public class MedicalDAO {
    PreparedStatement pst;
    ResultSet rs;
    
    
    
    
    public boolean addMedical(Medical medical){
        boolean value=false;
        
        try{
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("insert into medical values(?,?,?,?,?,?,?);");
            pst.setString(1, medical.getMedicalRefNo());
            pst.setString(2, medical.getStudentID());
            pst.setString(3, "NO");
            pst.setString(4, medical.getMedicalSubmitDate());
            pst.setString(5, medical.getMedicalStartDate());
            pst.setString(6, medical.getMedicalEndDate());
            pst.setString(7, medical.getMedicalStatus());
        
            if(pst.executeUpdate()>=1){
                value=true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return value;
    
    }
    
    public boolean deleteMedical(Medical medical){
        boolean value=false;
        
        try{
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("delete from medical where med_refno=? and med_st_id=?;");
            pst.setString(1, medical.getMedicalRefNo());
            pst.setString(2, medical.getStudentID());
        
            if(pst.executeUpdate()>=1){
                value=true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return value;
    
    }
    
    
    
    public boolean addMedicalCourseModule(Medical medical){
        boolean value=false;
        try{
            Connection con = DBConnectionUtil.getDBConnection();
            
            pst = con.prepareStatement("insert into medical_course_module values(?,?,?,?,?);");
            pst.setString(1, medical.getStudentID());
            pst.setString(2, medical.getMedicalRefNo());
            pst.setString(3, medical.getCourseID());
            pst.setString(4, medical.getDate());
            pst.setString(5, medical.getType());
        
            if(pst.executeUpdate()>=1){
                value=true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return value;
    
    }    
    
    
    public boolean updateMedical(Medical medical){
        boolean value=false;
        
        try{
            Connection con = DBConnectionUtil.getDBConnection();
            
            pst = con.prepareStatement("update medical set med_status=? where med_refno=? and med_st_id=? ;");
            
            pst.setString(1, medical.getMedicalStatus());
            pst.setString(2, medical.getMedicalRefNo());
            pst.setString(3, medical.getStudentID());
        
            if(pst.executeUpdate()>=1){
                value=true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return value;
    
    }   
    
    
    //-----------------------view medicals details------------------------------------
    public Medical viewMedical(String studentID, String courseID){
        Medical medical=null;
        try{
            Connection con = DBConnectionUtil.getDBConnection();
            
            pst = con.prepareStatement("select * from medical_course_module where mcm_st_id=? and mcm_courseID=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            pst.setString(1, studentID);
            pst.setString(2, courseID);
            
            rs=pst.executeQuery();
        
            while(rs.next()){
                medical = new Medical();
            
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
               
                
                
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return medical;
    
    } 
    
    
    public ArrayList<Medical>viewAllStudentMedical(String courseID,String dateTime){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_courseid=? and mcm_date_time LIKE ?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, dateTime);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }    
    //------------------
    
    public ArrayList<Medical>viewMedicalByCourseIdDateTime(String courseID,String dateTime){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_courseid=? and mcm_date_time LIKE ?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, dateTime + "%");
            
            rs=pst.executeQuery();

            if (rs.first()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }    
    
    
    public ArrayList<Medical>viewMedicalByCourseIdStudentID(String courseID,String studentID){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_courseid=? and mcm_st_id=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, studentID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
     
    
    public ArrayList<Medical>viewMedicalByStudentIdDateTime(String studentID,String dateTime){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_st_id=? and mcm_date_time LIKE ?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            pst.setString(2, dateTime + "%");
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    public ArrayList<Medical>viewMedicalByCourseID(String courseID){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_courseid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    public ArrayList<Medical>viewMedicalByStudentID(String studentID){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_st_id=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    public ArrayList<Medical>viewMedicalByDateTime(String dateTime){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical_course_module where mcm_date_time LIKE ?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, dateTime + "%");
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setMedicalRefNo(rs.getString(2));
                medical.setCourseID(rs.getString(3));
                medical.setDate(rs.getString(4));
                medical.setType(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    //-----------------------view medical form details--------------------------------
    public ArrayList<Medical>viewAllMedicalForm(){
        ArrayList<Medical> medicalFormList=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from medical ORDER BY med_submit_date DESC ;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setMedicalRefNo(rs.getString(1));
                medical.setStudentID(rs.getString(2));
                medical.setMedicalSubmitDate(rs.getString(4));
                medical.setMedicalStartDate(rs.getString(5));
                medical.setMedicalEndDate(rs.getString(6));
                medical.setMedicalStatus(rs.getString(7));
                
                medicalFormList.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalFormList;
        
    }
   

    public ArrayList<Medical>viewAllMedicalFormFilted(String studentID, String submitDate){
        ArrayList<Medical> medicalFormList=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("select * from medical where med_st_id=? and med_submit_date=? ORDER BY med_submit_date DESC ;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            pst.setString(2, submitDate);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setMedicalRefNo(rs.getString(1));
                medical.setStudentID(rs.getString(2));
                medical.setMedicalSubmitDate(rs.getString(4));
                medical.setMedicalStartDate(rs.getString(5));
                medical.setMedicalEndDate(rs.getString(6));
                medical.setMedicalStatus(rs.getString(7));
                
                medicalFormList.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalFormList;
        
    }
    
 
    public ArrayList<Medical>viewMedicalFormFiltedByStudentID(String studentID){
        ArrayList<Medical> medicalFormList=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("select * from medical where med_st_id=? ORDER BY med_submit_date DESC ;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setMedicalRefNo(rs.getString(1));
                medical.setStudentID(rs.getString(2));
                medical.setMedicalSubmitDate(rs.getString(4));
                medical.setMedicalStartDate(rs.getString(5));
                medical.setMedicalEndDate(rs.getString(6));
                medical.setMedicalStatus(rs.getString(7));
                
                medicalFormList.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalFormList;
        
    }
    
    
    public ArrayList<Medical>viewMedicalFormFiltedBySubmitDate(String submitDate){
        ArrayList<Medical> medicalFormList=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();
            
            
            pst = con.prepareStatement("select * from medical where med_submit_date=? ORDER BY med_submit_date DESC ;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, submitDate);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setMedicalRefNo(rs.getString(1));
                medical.setStudentID(rs.getString(2));
                medical.setMedicalSubmitDate(rs.getString(4));
                medical.setMedicalStartDate(rs.getString(5));
                medical.setMedicalEndDate(rs.getString(6));
                medical.setMedicalStatus(rs.getString(7));
                
                medicalFormList.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalFormList;
        
    }

    
    
    
    
    //--------------------------------For lecrurer use (Created by Helanka)------------------------------------
    public ArrayList<Medical> viewStudentMedicalByCourseID(String courseID,String type){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("SELECT mcm_courseid, med_st_id, med_start_date, med_end_date, mcm_at_type, "
                    + "med_status FROM medical, medical_course_module "
                    + "WHERE mcm_courseid =  ? AND mcm_at_type = ? AND medical.med_refno = medical_course_module.mcm_med_refno;", 
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID);
            pst.setString(2, type);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setCourseID(rs.getString(2));
                medical.setMedicalStartDate(rs.getString(3).toString());
                medical.setMedicalEndDate(rs.getString(4).toString());
                medical.setType(rs.getString(5));
                medical.setMedicalStatus(rs.getString(6));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    public ArrayList<Medical> viewStudentMedicalByStudentID(String studentID,String type){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("SELECT mcm_courseid, med_st_id, med_start_date, med_end_date, mcm_at_type, "
                    + "med_status FROM medical, medical_course_module "
                    + "WHERE mcm_st_id =  ? AND mcm_at_type = ? AND medical.med_refno = medical_course_module.mcm_med_refno", 
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            pst.setString(2, type);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setStudentID(rs.getString(1));
                medical.setCourseID(rs.getString(2));
                medical.setMedicalStartDate(rs.getString(3).toString());
                medical.setMedicalEndDate(rs.getString(4).toString());
                medical.setType(rs.getString(5));
                medical.setMedicalStatus(rs.getString(6));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    //For Student use
    public ArrayList<Medical> viewStuMedicalByCourseID(String courseID,String studentID){
        ArrayList<Medical> medicallist=new ArrayList<Medical>();
        
         try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("SELECT med_refno, mcm_courseid, med_submit_date, mcm_at_type, med_status "
                    + "FROM medical, medical_course_module WHERE mcm_courseid =  ? AND med_st_id = ? "
                    + "AND medical.med_refno = medical_course_module.mcm_med_refno;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, courseID.toUpperCase());
            pst.setString(2, studentID);
            
            rs=pst.executeQuery();

            while (rs.next()) {
                Medical medical=new Medical();
                
                medical.setMedicalRefNo(rs.getString(1));
                medical.setCourseID(rs.getString(2));
                medical.setMedicalSubmitDate(rs.getString(3).toString());
                medical.setType(rs.getString(4));
                medical.setMedicalStatus(rs.getString(5));
                
                medicallist.add(medical);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicallist;
        
    }
    
    
    
    
}


