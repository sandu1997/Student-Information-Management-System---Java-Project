/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Exam;
import com.sims.model.ExamDAO;
import com.sims.model.ExamsMarks;
import com.sims.model.ExamsMarksDAO;
import com.sims.model.Marks;
import com.sims.model.MarksDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LecturerExamController implements Initializable {

    @FXML
    private ComboBox<String> cmb_CourseCode;
    @FXML
    private ComboBox<String> cmb_ExamType;
    @FXML
    private Button btn_Add;
    @FXML
    private Button btn_Update;
    @FXML
    private TextField txt_StudentId;
    @FXML
    private TextField txt_Marks;
    @FXML
    private Button btn_Delete;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_CourseCode;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_ExamType;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_StudentId;
    @FXML
    private TableColumn<ExamsMarks, Double> tbl_Marks;
    @FXML
    private TableView<ExamsMarks> table;
    
    MarksDAO marksDAO = new MarksDAO();
    ExamDAO examDAO = new ExamDAO();
    Exam exam = new Exam();
    Marks marks = new Marks();
    ExamsMarksDAO examsMarksDAO = new ExamsMarksDAO();
    ExamsMarks examsMarks = null;
    
    
    //ComboBox database
    private ArrayList<String> courseid() {
        ArrayList<String> id = new ArrayList<String>();
        id = examDAO.getExamCourseId();
        return id;
    }
    ObservableList<String> courseId = FXCollections.observableArrayList(courseid());
    
    //ComboBox database
    private ArrayList<String> examType() {
        ArrayList<String> type = new ArrayList<String>();
        type = examDAO.getExamType();
        return type;
    }
    ObservableList<String> examType = FXCollections.observableArrayList(examType());
    
    //Table View
    ObservableList<ExamsMarks> obslist = FXCollections.observableArrayList();
    private void tableView() {
        ArrayList<ExamsMarks> marksData = examsMarksDAO.getMarks();
        
        for(ExamsMarks mark : marksData){
            obslist.add(mark);
            //System.out.println(" "+mark.getExamCourseId()+" "+mark.getType()+" "+mark.getMarksStuId()+" "+mark.getMarks());
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("examCourseId"));
        tbl_ExamType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("marksStuId"));
        tbl_Marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        
        table.setItems(obslist);
    
    }
    
    private void clearField() {
        txt_StudentId.setText("");
        txt_Marks.setText("");
        cmb_CourseCode.setValue(null);
        cmb_ExamType.setValue(null);
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ComboBox database
        cmb_CourseCode.setItems(courseId);  //getItems().addAll(courseId)
        cmb_ExamType.setItems(examType);
        tableView();
        clearField();
    }    

    @FXML
    private void btn_AddActionPerformed(ActionEvent event) {
        exam.setExamCourseId(cmb_CourseCode.getValue().toUpperCase());
        exam.setType(cmb_ExamType.getValue().toUpperCase());
        marks.setMarksStuId(txt_StudentId.getText());
        marks.setMarks(Double.parseDouble(txt_Marks.getText()));
        
        if (isFieldsEmpty()) {
            //JOptionPane.showMessageDialog(this, "Some fields are missing", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            if (marksDAO.insertMarks(marks, exam)) {
                //JOptionPane.showMessageDialog(this, "successfully inserted");
                tableView();
                 
            } else {
                //JOptionPane.showMessageDialog(this, "Error in inserting record", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
    }

    @FXML
    private void btn_UpdateActionPerformed(ActionEvent event) {
        exam.setExamCourseId(cmb_CourseCode.getValue().toUpperCase());
        exam.setType(cmb_ExamType.getValue().toUpperCase());
        marks.setMarksStuId(txt_StudentId.getText());
        marks.setMarks(Double.parseDouble(txt_Marks.getText()));
        
        if (isFieldsEmpty()) {
            //JOptionPane.showMessageDialog(this, "Some fields are missing", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            if (marksDAO.updateMarks(marks, exam)) {
                //JOptionPane.showMessageDialog(this, "successfully updated");
                tableView();
                clearField();
            } else {
                //JOptionPane.showMessageDialog(this, "Please check the student ID", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void btn_DeleteActionPerformed(ActionEvent event) {
        exam.setExamCourseId(cmb_CourseCode.getValue().toUpperCase());
        exam.setType(cmb_ExamType.getValue().toUpperCase());
        marks.setMarksStuId(txt_StudentId.getText());
        
        if (txt_StudentId.getText().isEmpty() ||  exam.getExamCourseId().isEmpty() || exam.getType().isEmpty()) {
            //JOptionPane.showMessageDialog(this, "Please Enter a Student ID", "Alert", JOptionPane.WARNING_MESSAGE);
        } else{
            //int select = JOptionPane.showConfirmDialog(this, "Are you sure that you want to delete?", "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            //if (select == JOptionPane.YES_OPTION) {
            if (marksDAO.deleteMarks(marks, exam)) {
                //JOptionPane.showMessageDialog(this, "successfully deleted");
                tableView();
                clearField();
            } else {
                //JOptionPane.showMessageDialog(this, "Please check the student ID", "Warning", JOptionPane.ERROR_MESSAGE);
            }
            //}
        }
    }
    
    @FXML
    private void tableviewOnMouseclicked(MouseEvent event) {
        examsMarks = table.getSelectionModel().getSelectedItem();
        cmb_CourseCode.setValue(examsMarks.getExamCourseId());
        cmb_ExamType.setValue(examsMarks.getType());
        txt_StudentId.setText(examsMarks.getMarksStuId());
        txt_Marks.setText(String.valueOf(examsMarks.getMarks()));
    }
    
    
    private boolean isFieldsEmpty() {
        return exam.getExamCourseId().isEmpty() || exam.getType().isEmpty() || txt_Marks.getText().isEmpty() || txt_StudentId.getText().isEmpty();
    }

    
    
    
    
}
