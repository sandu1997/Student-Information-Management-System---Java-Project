/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Medical;
import com.sims.model.MedicalDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerMedicalsUpdateController implements Initializable {

    @FXML
    private TextField txtStudentID;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnOk;
    @FXML
    private TextField txtRefNo;
    @FXML
    private ComboBox combStatus;
    ObservableList<String> status=FXCollections.observableArrayList("Denied","Approved");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combStatus.setItems(status);
    }    

    @FXML
    private void updateActionController(ActionEvent event) {
        
        Medical medical = new Medical();
        MedicalDAO medicalDAO = new MedicalDAO();
    
        
        String status;
        
        status=(String) combStatus.getValue();

        
        if((txtRefNo.getText().trim().isEmpty()) || (txtStudentID.getText().trim().isEmpty())){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Error");
            a1.setContentText("Please fill this form...!");
            a1.setHeaderText(null);
            a1.showAndWait();
        
        }else{
            medical.setMedicalRefNo(txtRefNo.getText());
            medical.setStudentID(txtStudentID.getText());
            medical.setMedicalStatus(status);

            System.out.println(txtRefNo.getText());
            System.out.println(txtStudentID.getText());
            System.out.println(status);
            
            if (medicalDAO.updateMedical(medical)== true) {
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
    private void okActionController(ActionEvent event) {
        
           
            Stage stage = (Stage) btnOk.getScene().getWindow();
         
            stage.close();
          }
        
    
    
}
