/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Medical;
import com.sims.model.MedicalDAO;
import com.sims.model.Student;
import com.sims.model.StudentDAO;
import com.sims.model.TimeTableDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC PLUS
 */
public class StudentMedicalsController implements Initializable {

    @FXML
    private VBox notice_vbox;
    @FXML
    private TableView<Medical> tableview;
    @FXML
    private TableColumn<Medical, String> colno;
    @FXML
    private TableColumn<Medical, String> colcoursecode;
    @FXML
    private TableColumn<Medical, String> coldate;
    @FXML
    private TableColumn<Medical, String> coltype;
    @FXML
    private TableColumn<Medical, String> colStatus;
    @FXML
    private TextField txt_course;
    @FXML
    private Button btn_Search;
    
    private Student student;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    

    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
        if (txt_course.getText().isEmpty()) {
        
        } else {
            SetTabaleByCourseID(txt_course.getText().toUpperCase(), student.getUserID());
            txt_course.setText(null);
            txt_course.setPromptText("Course Code");
        }
    }
    
    private void SetTabaleByCourseID(String CourseCode, String studentID) {
        ObservableList<Medical> sobslist = FXCollections.observableArrayList();
        MedicalDAO medicalDAO = new MedicalDAO();
        ArrayList<Medical> medicals = medicalDAO.viewStuMedicalByCourseID(CourseCode, studentID);
        
        for (Medical medical : medicals) {
            sobslist.add(medical);
        }
        
        colno.setCellValueFactory(new PropertyValueFactory<>("medicalRefNo"));
        colcoursecode.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("medicalSubmitDate"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("medicalStatus"));
        
        tableview.setItems(sobslist);
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
   
    
}
