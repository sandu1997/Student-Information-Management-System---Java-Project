/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Attendance;
import com.sims.model.AttendanceDAO;
import com.sims.model.Session;
import com.sims.model.SessionDAO;
import com.sims.model.TechnicalOfficer;
import com.sims.model.TechnicalOfficerDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerAttendanceAddBoxController implements Initializable {

    @FXML
    private TextField txtStudent;
   
    
    @FXML
    private Button btnAdd;
    @FXML
    private Pane pan2;
    @FXML
    private Pane pan1;

    @FXML
    private Button btnReset;

    
    
    
    TechnicalOfficerDAO technicalOfficerDAO = new TechnicalOfficerDAO();
    @FXML
    private TextField txtSessionid;
    @FXML
    private ComboBox combStatus;
    ObservableList<String> status=FXCollections.observableArrayList("Present","Absent");
    
    //private Attendance atendanceAdd = null;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combStatus.setItems(status);
        
    }    

    private void getSessionIDActionController(ActionEvent event) { 
    }

    @FXML
    private void addAttendanceActionController(ActionEvent event) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        
        Attendance attendance = new Attendance();
        
        String status,courseStatus;
        
        status=(String) combStatus.getValue();
        if (status=="Present"){
            courseStatus="1";
        }else{
            courseStatus="0";
        }
        
        
        if((txtStudent.getText().trim().isEmpty()) || (txtSessionid.getText().trim().isEmpty())){
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Error");
                a1.setContentText("Please fill this form...!");
                a1.setHeaderText(null);
                a1.showAndWait();
        }else{
            attendance.setStudentID(txtStudent.getText());
            attendance.setSessionID(txtSessionid.getText());
            attendance.setAttendanceStatus(courseStatus);
        
            System.out.println(txtStudent.getText());
            System.out.println(txtSessionid.getText());
            System.out.println(courseStatus);
        
            if (attendanceDAO.addAttendance(attendance)== true) {
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(txtStudent.getText() + " Succesfully added");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText("Details Are Wrong! / Error in Operation!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }
        }
        
    }

    @FXML
    private void resetFormActionController(ActionEvent event) {
        txtStudent.setText("");
        txtSessionid.setText("");
        combStatus.setValue(null);
    }
    
}
