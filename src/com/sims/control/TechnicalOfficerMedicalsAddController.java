/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Medical;
import com.sims.model.MedicalDAO;
import com.sims.model.TechnicalOfficer;
import com.sims.model.TechnicalOfficerDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerMedicalsAddController implements Initializable {

    @FXML
    private TextField txtRefNomf;
    @FXML
    private TextField txtStudentIDmf;
    @FXML
    private DatePicker combEndDatemf;
    @FXML
    private DatePicker combStartDatemf;
    
    @FXML
    private ComboBox combMedicalStarusmf;
    ObservableList<String> medicalStatus=FXCollections.observableArrayList("Pending","Approved","Denid");
    
    @FXML
    private DatePicker combSubmitDatemf;
    @FXML
    private Button btnAddMedical;
    @FXML
    private Button btnResetForm;
    
    @FXML
    private ComboBox combRefNocm;
    ObservableList<String> medicalRefNoList=FXCollections.observableArrayList();
    
    @FXML
    private DatePicker combDatecm;
    @FXML
    private ComboBox combTypeIDcm;
    ObservableList<String> medType=FXCollections.observableArrayList("n");
    
    @FXML
    private ComboBox combCourseIDcm;
    ObservableList<String> list=FXCollections.observableArrayList();
    
    @FXML
    private Button btnAddMedicalcm;
    @FXML
    private Button btnResetFormcm;
    @FXML
    private TextField txtStudentIDcm;
    @FXML
    private Button btnLoadRefNo;
    @FXML
    private Pane pan1;
    @FXML
    private Pane pan2;
    @FXML
    private Label reflable;
    @FXML
    private Spinner hour;
    @FXML
    private Spinner minute;

    /**
     * Initializes the controller class.
     */
    TechnicalOfficerDAO technicalOfficerDAO = new TechnicalOfficerDAO();
    
    Medical medical = new Medical();
    
    MedicalDAO medicalDAO = new MedicalDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //todo
        
        //---------- cours list pass to combobox-----------------
        ArrayList<TechnicalOfficer> cours;
        try {
            cours = technicalOfficerDAO.getCourseList("dpt02");
            for(TechnicalOfficer technicalOfficer : cours){
                list.add(technicalOfficer.getCourse());
            }
            combCourseIDcm.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(TechnicalOfficerMedicalsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //set value to combobox
        combMedicalStarusmf.setItems(medicalStatus);
        combTypeIDcm.setItems(medType);
        
        //pane disable
        pan1.setDisable(true);
        pan2.setDisable(true);
        reflable.setDisable(true);
        combRefNocm.setDisable(true);
        
        //time spiner hour
        SpinnerValueFactory<Integer> hours=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,24,8);
        this.hour.setValueFactory(hours);
        //time spinter minute
        SpinnerValueFactory<Integer> minutes=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
        minute.setValueFactory(minutes);

    }    

    @FXML
    private void addMedicalActionController(ActionEvent event) {
       
        String refNo, studentID, SubmitDate, StartDate, EndDate, status;
//        refNo=txtRefNomf.getText();
//        studentID=txtStudentIDmf.getText();
//        SubmitDate=combSubmitDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        StartDate=combStartDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        EndDate=combEndDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        status=(String)combMedicalStarusmf.getValue();
        
        
//        medical.setMedicalRefNo(refNo);
//        medical.setStudentID(studentID);
//        medical.setMedicalSubmitDate(SubmitDate);
//        medical.setMedicalStartDate(StartDate);
//        medical.setMedicalEndDate(EndDate);
//        medical.setMedicalStatus(status);
//        
//        medicalDAO.addMedical(medical);
//        
//        System.out.println(refNo + studentID + SubmitDate + StartDate + EndDate + status);
        
        if((txtRefNomf.getText().trim().isEmpty()) || (txtStudentIDmf.getText().trim().isEmpty()) || combSubmitDatemf.getValue()==null || combStartDatemf.getValue()==null || combEndDatemf.getValue()==null || combMedicalStarusmf.getValue()==null){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Error");
            a1.setContentText("Please fill this form...!");
            a1.setHeaderText(null);
            a1.showAndWait();
        
        }else{
            refNo=txtRefNomf.getText();
            studentID=txtStudentIDmf.getText();
            SubmitDate=combSubmitDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            StartDate=combStartDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            EndDate=combEndDatemf.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            status=(String)combMedicalStarusmf.getValue();
            
            
            
            medical.setMedicalRefNo(refNo);
            medical.setStudentID(studentID);
            medical.setMedicalSubmitDate(SubmitDate);
            medical.setMedicalStartDate(StartDate);
            medical.setMedicalEndDate(EndDate);
            medical.setMedicalStatus(status);

            if (medicalDAO.addMedical(medical)== true) {
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(studentID + " Succesfully Added!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Error");
                a1.setContentText("Check Details!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }
        }
    
    }
    
    

    @FXML
    private void resetMedicalActionController(ActionEvent event) {
        txtRefNomf.setText("");
        txtStudentIDmf.setText("");
        combSubmitDatemf.setValue(null);
        combStartDatemf.setValue(null);
        combEndDatemf.setValue(null);
        combMedicalStarusmf.setValue("");
    }

    @FXML
    private void addMedicalCourseModuleActionController(ActionEvent event) {
        String studentID,refNo,date,courseID,type,hours,minutes,dateTime;
//        studentID=txtStudentIDcm.getText();
//        refNo=(String) combRefNocm.getValue();
//        date=combDatecm.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        courseID=(String) combCourseIDcm.getValue();
//        type=(String) combTypeIDcm.getValue();
        
//        if(((int) hour.getValue()<10)){
//            hours="0"+String.valueOf(hour.getValue());
//        }else{
//            hours=String.valueOf(hour.getValue());
//        }
//        if(((int) minute.getValue()<10)){
//            minutes="0"+String.valueOf(minute.getValue());
//        }else{
//            minutes=String.valueOf(minute.getValue());
//        }
//        dateTime=date+" "+hours+":"+minutes+":00";

//        medical.setStudentID(studentID);
//        medical.setMedicalRefNo(refNo);
//        medical.setCourseID(courseID);
//        medical.setDate(dateTime);
//        medical.setType(type);
////        
////        
//        medicalDAO.addMedicalCourseModule(medical);

        
        
        if((txtStudentIDcm.getText().trim().isEmpty()) || (combRefNocm.getValue()==null) || combDatecm.getValue()==null || combCourseIDcm.getValue()==null || combTypeIDcm.getValue()==null){
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Error");
            a1.setContentText("Please fill this form...!");
            a1.setHeaderText(null);
            a1.showAndWait();
        
        }else{
            studentID=txtStudentIDcm.getText();
            refNo=(String) combRefNocm.getValue();
            date=combDatecm.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            courseID=(String) combCourseIDcm.getValue();
            type=(String) combTypeIDcm.getValue();
            
            
            if(((int) hour.getValue()<10)){
            hours="0"+String.valueOf(hour.getValue());
            }else{
                hours=String.valueOf(hour.getValue());
            }
            if(((int) minute.getValue()<10)){
                minutes="0"+String.valueOf(minute.getValue());
            }else{
                minutes=String.valueOf(minute.getValue());
            }
            dateTime=date+" "+hours+":"+minutes+":00";
            
            
            medical.setStudentID(studentID);
            medical.setMedicalRefNo(refNo);
            medical.setCourseID(courseID);
            medical.setDate(dateTime);
            medical.setType(type);

            if (medicalDAO.addMedicalCourseModule(medical)== true) {
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(studentID + " Succesfully Added!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Error");
                a1.setContentText("Check Details!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }
        }
        
    }

    @FXML
    private void resetMedicalCourseModuleActionController(ActionEvent event) {
        pan1.setDisable(true);
        pan2.setDisable(true);
        reflable.setDisable(true);
        combRefNocm.setDisable(true);
        txtStudentIDcm.setText("");
        combRefNocm.setValue(null);
        combDatecm.setValue(null);
        combTypeIDcm.setValue(null);
        combCourseIDcm.setValue(null);
        medicalRefNoList.clear();
    }

    @FXML
    private void LoadRefNoActionController(ActionEvent event) throws SQLException {
        medicalRefNoList.clear();//clear list
        String stuid;
        stuid=txtStudentIDcm.getText();
        pan1.setDisable(false);
        pan2.setDisable(false);
        reflable.setDisable(false);
        combRefNocm.setDisable(false);
        
        ArrayList<TechnicalOfficer> refno;
            refno = technicalOfficerDAO.getMedicalRefNoList(stuid);
            for(TechnicalOfficer technicalOfficer : refno){
                medicalRefNoList.add(technicalOfficer.getRefNo());
            }
            combRefNocm.setItems(medicalRefNoList);
       
            
        
        
    }

    @FXML
    private void setHourActionController(MouseEvent event) {
    }

    @FXML
    private void setMinuteActionController(MouseEvent event) {
    }
    
}
