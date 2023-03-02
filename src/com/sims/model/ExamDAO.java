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
 * @author Helanka
 */
public class ExamDAO {
    
    //Get Exam from exam table according to the given Course ID and Exam Type
    public Exam getExam(Exam exam) {
        
        Exam column = null;
        
        Connection con = null;
                
        try {
            
            con = DBConnectionUtil.getDBConnection();
            
            column = new Exam();
            
            String getExam = "SELECT * FROM exam WHERE exam_courseid = ? AND exam_type= ?;";
            PreparedStatement preparedStatement = con.prepareStatement(getExam);
            preparedStatement.setString(1, exam.getExamCourseId());
            preparedStatement.setString(2, exam.getType());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                column.setExamId(resultSet.getString(1));
                column.setType(resultSet.getString(2));
                column.setExamCourseId(resultSet.getString(4));
                
                //System.out.println(column.getExamId() + ", " + column.getType()+ ", " + column.getExamCourseId());
            } else {
                column.setType("");
                column.setExamCourseId("");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return column;
    }
    
    //Get All Exam from exam table
    public String[][] getExam() {
        
        String[][] dataRow = null;
        
        Connection con = null;
        
        try {
            
            
            con = DBConnectionUtil.getDBConnection();
            
            int rowIndex = 0;
            
            String rowcount = "SELECT count(examid) FROM exam;";
            PreparedStatement ps = con.prepareStatement(rowcount);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            dataRow = new String[rowCount][4];
            
            String getExam = "SELECT * FROM exam;";
            PreparedStatement preparedStatement = con.prepareStatement(getExam);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                dataRow[rowIndex][0] = resultSet.getString(1);
                dataRow[rowIndex][1] = resultSet.getString(2);
                dataRow[rowIndex][2] = resultSet.getString(3);
                dataRow[rowIndex][3] = resultSet.getString(4);
                
                System.out.println(dataRow[rowIndex][0] + ", " + dataRow[rowIndex][1] + ", " + dataRow[rowIndex][2] + ", " + dataRow[rowIndex][3]);
                
                rowIndex++;
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dataRow;
    }
    
    //Get All Exam ID from exam table for ComboBox
    public ArrayList<String> getExamCourseId() {
    
        String[][] dataRow = null;
        ArrayList<String> id = new ArrayList<String>();
        Connection con = null;
        
        try {
                   
            con = DBConnectionUtil.getDBConnection();
            
            int rowIndex = 0;
            String rowcount = "SELECT count(DISTINCT exam_courseid) FROM exam;";
            PreparedStatement ps = con.prepareStatement(rowcount);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            dataRow = new String[rowCount][1];
            
            String getExam = "SELECT DISTINCT exam_courseid FROM exam ORDER BY exam_courseid ASC;";
            PreparedStatement preparedStatement = con.prepareStatement(getExam);
            
            ResultSet resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
                
                dataRow[rowIndex][0] = resultSet.getString(1);
                
                id.add(dataRow[rowIndex][0]);
                
                System.out.println(dataRow[rowIndex][0]);
                
                rowIndex++;
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return id;
    }
    
    //Get All Exam Type from exam table for ComboBox
    public ArrayList<String> getExamType() {
    
        String[][] dataRow = null;
        ArrayList<String> type = new ArrayList<String>();
        Connection con = null;
        
        try {
                   
            con = DBConnectionUtil.getDBConnection();
            
            int rowIndex = 0;
            String rowcount = "SELECT count(DISTINCT exam_type) FROM exam;";
            PreparedStatement ps = con.prepareStatement(rowcount);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            dataRow = new String[rowCount][1];
            
            String getExam = "SELECT DISTINCT exam_type FROM exam ORDER BY exam_type ASC;";
            PreparedStatement preparedStatement = con.prepareStatement(getExam);
            
            ResultSet resultSet = preparedStatement.executeQuery();
 
            while (resultSet.next()) {
                
                dataRow[rowIndex][0] = resultSet.getString(1);
                
                type.add(dataRow[rowIndex][0]);
                
                System.out.println(dataRow[rowIndex][0]);
                
                rowIndex++;
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return type;
    }
    
}
