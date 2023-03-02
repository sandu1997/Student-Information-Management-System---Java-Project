/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Attendance;
import com.sims.model.AttendanceDAO;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerAttendanceUpdateController implements Initializable {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnOk;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtSessionID;
    @FXML
    private ComboBox combStatus;
    ObservableList<String> status=FXCollections.observableArrayList("Present","Absent","Medical");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       combStatus.setItems(status);
    }    

    @FXML
    private void updateAttendanceActionController(ActionEvent event) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        
        Attendance attendance = new Attendance();
        
        String status,courseStatus;
        
        status=(String) combStatus.getValue();
        if (status=="Present"){
            courseStatus="1";
        }else if (status=="Medical"){
            courseStatus="2";
        }else{
            courseStatus="0";
        }
        
        
        if((txtStudentID.getText().trim().isEmpty()) || (txtSessionID.getText().trim().isEmpty())){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Error");
            a1.setContentText("Please fill this form...!");
            a1.setHeaderText(null);
            a1.showAndWait();
        
        }else{
            attendance.setStudentID(txtStudentID.getText());
            attendance.setSessionID(txtSessionID.getText());
            
            attendance.setAttendanceStatus(courseStatus);

            System.out.println(txtStudentID.getText());
            System.out.println(txtSessionID.getText());
            
            if (attendanceDAO.updateAttendance(attendance)== true) {
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(txtStudentID.getText() + " Succesfully Updated!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Error");
                a1.setContentText("Details Are Wrong!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }
        }
        
        
        
        
    }

    @FXML
    private void closeWindowActionController(ActionEvent event) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
         
            stage.close();
        }
    
    
}
