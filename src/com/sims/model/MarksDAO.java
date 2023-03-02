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
public class MarksDAO {
    
    ExamDAO examDAO = new ExamDAO();
 
    
    //Insert Marks into marks table(LecturerExam)
    public boolean insertMarks(Marks marks, Exam exam) {
        
        Connection con = null;
        boolean status = false;
         
        try {
            Exam column = examDAO.getExam(exam);
            
            con = DBConnectionUtil.getDBConnection();
            
            
            String insertMarks = "INSERT INTO marks (marks_st_id, marks_examid, mark) VALUES ( ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(insertMarks, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, marks.getMarksStuId());
            preparedStatement.setString(2, column.getExamId());
            preparedStatement.setDouble(3, marks.getMarks());
            int i = preparedStatement.executeUpdate();
            
            if (i == 1) {
                //System.out.println("A Marks record has inserted successfully");
                status = true;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return status;
    }

    //Update Marks in marks table(LecturerExam)
    public boolean updateMarks(Marks marks, Exam exam) {
    
        boolean status = false;
        
        Connection con = null;
        
        try {
            Exam column = examDAO.getExam(exam);
            
            con = DBConnectionUtil.getDBConnection();
            
            String updateMarks = "UPDATE marks SET mark = ? WHERE marks_examid = ? AND marks_st_id = ?;";
            PreparedStatement preparedStatement  = con.prepareStatement(updateMarks);
            preparedStatement.setDouble(1, marks.getMarks());
            preparedStatement.setString(2, column.getExamId());
            preparedStatement.setString(3, marks.getMarksStuId());
            int i = preparedStatement.executeUpdate();
            
            if (i == 1) {
                System.out.println("A Marks record has updated successfully");
                status = true;
            } else {
                System.out.println("Error in updating record");
                status = false;
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return status;
    }
    
    //Delete Marks from marks table according to the given Student ID(LecturerExam)
    public boolean deleteMarks(Marks marks, Exam exam) {
        
        boolean status = false;
        
        Connection con = null;
        
        try {
            
            Exam column = examDAO.getExam(exam);
            
            con = DBConnectionUtil.getDBConnection();
            
            String deleteMarks = "DELETE FROM marks WHERE marks_st_id = ? AND marks_examid = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(deleteMarks);
            preparedStatement.setString(1, marks.getMarksStuId());
            preparedStatement.setString(2, column.getExamId());
            int i = preparedStatement.executeUpdate();
            
            if (i == 1) {
                System.out.println("A Marks record has deleted successfully");
                status = true;
            } else {
                System.out.println("Error in deleting record");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarksDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return status;
    }
    

    
       
}
