/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Attendance;
import com.sims.model.AttendanceDAO;
import com.sims.model.Student;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC PLUS
 */
public class StudentAttendanceController implements Initializable {

    @FXML
    private VBox notice_vbox;
    @FXML
    private TableView<Attendance> tableview;
    @FXML
    private TableColumn<Attendance, String> colcoursecode;
    @FXML
    private TableColumn<Attendance, String> coldate;
    @FXML
    private TableColumn<Attendance, String> coltype;
    @FXML
    private TableColumn<Attendance, String> colhours;
    @FXML
    private TableColumn<Attendance, String> colstatus;
    @FXML
    private Button btn_Search;
    @FXML
    private TextField txt_Course;

    private Student student;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
        if (txt_Course.getText().isEmpty()) {
        
        } else {
            SetTabaleByCourse(txt_Course.getText().toUpperCase(), student.getUserID());
            txt_Course.setText(null);
            txt_Course.setPromptText("Course Code");

        }
    }

     private void SetTabaleByCourse(String CourseCode, String studentID) {
        ObservableList<Attendance> sobslist = FXCollections.observableArrayList();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        ArrayList<Attendance> attendances = attendanceDAO.getStuAttendanceByCourse(CourseCode, studentID);
        
        for (Attendance attendance : attendances) {
            sobslist.add(attendance);
        }
        
        colcoursecode.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("sessionType"));
        colhours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("attendanceStatus"));
        
        tableview.setItems(sobslist);
    }
    

    public void setStudent(Student student) {
        this.student = student;
    }

    
    
}
