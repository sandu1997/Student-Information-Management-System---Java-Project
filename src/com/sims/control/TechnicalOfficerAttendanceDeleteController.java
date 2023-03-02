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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerAttendanceDeleteController implements Initializable {

    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtSessionID;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DeleteActionController(ActionEvent event) {
        Attendance attendance = new Attendance();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        
        
        if((txtStudentID.getText().trim().isEmpty()) || (txtSessionID.getText().trim().isEmpty())){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Error");
            a1.setContentText("Please fill this form...!");
            a1.setHeaderText(null);
            a1.showAndWait();
        
        }else{
            attendance.setStudentID(txtStudentID.getText());
            attendance.setSessionID(txtSessionID.getText());


            System.out.println(txtStudentID.getText());
            System.out.println(txtSessionID.getText());
            
            if (attendanceDAO.deleteAttendance(attendance)== true) {
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(txtStudentID.getText() + " Succesfully deleted!");
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
    
    
    
    
}
