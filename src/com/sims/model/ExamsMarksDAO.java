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
public class ExamsMarksDAO {
    
    ExamDAO examDAO = new ExamDAO();
    
    
    //Get Mark from marks table according to the given Course ID, Exam Type and Student ID(LecturerStuMarks)
    public ExamsMarks getMark(Marks marks, Exam exam) {
        
        Connection con = null;
        ExamsMarks column = new ExamsMarks();
        
        try {
            Exam examRow = examDAO.getExam(exam);
            
            con = DBConnectionUtil.getDBConnection();
            
            String getMarkForAStudent = "SELECT * FROM marks WHERE marks_st_id = ? AND marks_examid = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(getMarkForAStudent);
            preparedStatement.setString(1, marks.getMarksStuId());
            preparedStatement.setString(2, examRow.getExamId());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                column.setExamCourseId(examRow.getExamCourseId());
                column.setType(examRow.getType());
                column.setMarksStuId(resultSet.getString(1));
                column.setMarks(Double.parseDouble(resultSet.getString(3)));
                
                System.out.println(column.getExamCourseId() + ", " + column.getType()+ ", " + column.getMarksStuId()  + ", " + column.getMarks());
            } else {
                column.setExamCourseId("");
                column.setType("");
                column.setMarksStuId("");
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
        return column;
    }
    
    //Get Marks from marks table according to the given Course ID and Exam Type(LecturerStuMarks)
    public ArrayList<ExamsMarks> getMarks(Exam exam) {
        
        ArrayList<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
        Connection con = null;
        
        try {
            Exam examRow = examDAO.getExam(exam);
            
            con = DBConnectionUtil.getDBConnection();
            
            String getMarks = "SELECT * FROM marks WHERE marks_examid = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(getMarks);
            preparedStatement.setString(1, examRow.getExamId());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                ExamsMarks dataRow = new ExamsMarks();
                
                dataRow.setExamCourseId(examRow.getExamCourseId());
                dataRow.setType(examRow.getType());
                dataRow.setMarksStuId(resultSet.getString(1));
                dataRow.setMarks(Double.parseDouble(resultSet.getString(3)));
                
                System.out.println(dataRow.getExamCourseId() + ", " + dataRow.getType() + ", " +
                        dataRow.getMarksStuId() + ", " + dataRow.getMarks());
                
                examMarksList.add(dataRow);
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
        
        return examMarksList;
    }
    
    //Get All Marks from marks table(LecturerStuMarks, LecturerExam) +getAllMarks()
    public  ArrayList<ExamsMarks> getMarks() { 
    
        ArrayList<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
        Connection con = null;
        
        try {
            con  = DBConnectionUtil.getDBConnection();
            
            String getAllMarks = "SELECT exam_courseid, exam_type, marks_st_id, mark FROM marks, exam WHERE marks_examid = examid;";
            PreparedStatement preparedStatement = con.prepareStatement(getAllMarks);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                ExamsMarks dataRow = new ExamsMarks();
                
                dataRow.setExamCourseId(resultSet.getString(1));
                dataRow.setType(resultSet.getString(2));
                dataRow.setMarksStuId(resultSet.getString(3));
                dataRow.setMarks(Double.parseDouble(resultSet.getString(4)));
                
                //System.out.println(dataRow.getExamCourseId() + ", " + dataRow.getType() + ", " + 
                        //dataRow.getMarksStuId() + ", " + dataRow.getMarks());
                
                examMarksList.add(dataRow);
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
        
        return examMarksList;
    }
    
    //Get Marks from marks table according to the given Student ID(LecturerStuMarks)
    public ArrayList<ExamsMarks> getMarksForAStudent(Marks marks) {
        
        ArrayList<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
        Connection con = null;
        
        try {
            con = DBConnectionUtil.getDBConnection();
            
            String getMarks = "SELECT exam_courseid, exam_type, marks_st_id, mark  FROM marks, exam WHERE marks.marks_st_id = ? AND marks_examid = examid;";
            PreparedStatement preparedStatement = con.prepareStatement(getMarks);
            preparedStatement.setString(1, marks.getMarksStuId());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                ExamsMarks dataRow = new ExamsMarks();
                
                dataRow.setExamCourseId(resultSet.getString(1));
                dataRow.setType(resultSet.getString(2));
                dataRow.setMarksStuId(resultSet.getString(3));
                dataRow.setMarks(Double.parseDouble(resultSet.getString(4)));
                
                System.out.println(dataRow.getExamCourseId() + ", " + dataRow.getType() + ", " +
                        dataRow.getMarksStuId() + ", " + dataRow.getMarks());
                
                examMarksList.add(dataRow);
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
        
        return examMarksList;
    }
    
}
