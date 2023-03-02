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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pragith
 */
public class TechnicalOfficerDAO {
    PreparedStatement pst;
    ResultSet rs;
    
 
    public  ArrayList<TechnicalOfficer> getCourseList(String departmentID) throws SQLException {
        ArrayList<TechnicalOfficer> courselist = new ArrayList<TechnicalOfficer>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select courseid from course_module where course_dpt=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, departmentID);
            rs=pst.executeQuery();

            while (rs.next()) {

                TechnicalOfficer technicalOfficer = new TechnicalOfficer();

                technicalOfficer.setCourse(rs.getString(1));

                courselist.add(technicalOfficer);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courselist;
    }   
        
        
    public  ArrayList<TechnicalOfficer> getMedicalRefNoList(String studentID) throws SQLException {
        ArrayList<TechnicalOfficer> refnolist = new ArrayList<TechnicalOfficer>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select med_refno from medical where med_st_id=?;", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, studentID);
            rs=pst.executeQuery();

            while (rs.next()) {

                TechnicalOfficer technicalOfficer = new TechnicalOfficer();

                technicalOfficer.setRefNo(rs.getString(1));

                refnolist.add(technicalOfficer);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return refnolist;
    }   
    
    
 
        
}
