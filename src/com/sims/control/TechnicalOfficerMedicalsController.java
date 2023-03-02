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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerMedicalsController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox notice_vbox;
    @FXML
    private TextField txtStudentID;
    @FXML
    private Button btnSearch;

    @FXML
    private TableView<Medical> table;
    @FXML
    private TableColumn<Medical, String> colRefNo;
    @FXML
    private TableColumn<Medical, String> colStudentID;
    @FXML
    private TableColumn<Medical, String> colSubmitDate;
    @FXML
    private TableColumn<Medical, String> colStartDate;
    @FXML
    private TableColumn<Medical, String> colEndDate;
    @FXML
    private TableColumn<Medical, String> colStatus;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;

    @FXML
    private ComboBox combCourseID;
    ObservableList<String> list = FXCollections.observableArrayList();
    TechnicalOfficerDAO technicalOfficerDAO = new TechnicalOfficerDAO();

    @FXML
    private Tab tabMedicalForm;
    @FXML
    private Tab tabMedicalCourseModule;
    @FXML
    private DatePicker txtDate;

    @FXML
    private TableView<Medical> medicalCourseModule;
    @FXML
    private TableColumn<Medical, String> colRefNo_cm;
    @FXML
    private TableColumn<Medical, String> colStudentID_cm;
    @FXML
    private TableColumn<Medical, String> colCourseID_cm;
    @FXML
    private TableColumn<Medical, String> colDateTime_cm;
    @FXML
    private TableColumn<Medical, String> colType_cm;

    private Medical medical_ = null;

    boolean tab1, tab2; //-----------tabs-----
    @FXML
    private Button btnUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //---------- cours list pass to combobox-----------------
        ArrayList<TechnicalOfficer> cours;
        try {
            cours = technicalOfficerDAO.getCourseList("dpt02");
            for (TechnicalOfficer technicalOfficer : cours) {
                list.add(technicalOfficer.getCourse());
            }
            combCourseID.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(TechnicalOfficerMedicalsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //------------view all medical forms-------------------
        ObservableList<Medical> medicalFormList = FXCollections.observableArrayList();

        MedicalDAO medicalDAO = new MedicalDAO();
        ArrayList<Medical> students = medicalDAO.viewAllMedicalForm();

        for (Medical student : students) {
            medicalFormList.add(student);
        }

        colRefNo.setCellValueFactory(new PropertyValueFactory<>("MedicalRefNo"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        colSubmitDate.setCellValueFactory(new PropertyValueFactory<>("MedicalSubmitDate"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("MedicalStartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("MedicalEndDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("MedicalStatus"));

        table.setItems(medicalFormList);

        //-------------------------test click--------------------------------
//        final observableList<TablePosition> selectedCells = table.getSelectionModel().getSelectedCells();
//        selectedCells.addListener(new ListChangeListener<TablePosition>(){
//            @Override
//            public void onChanged(Change change){
//                for (TablePosition pos: selectedCells){
//                    System.out.println("cells selected"+pos.getRow()+"and column"+pos.getcolumn());
//                }
//            }
//        
//        
//        }
        // TODO
    }

    @FXML
    private void addMedicalBtnActionController(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerMedicalsAdd.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void deleteMedicalBtnActionController(ActionEvent event) {
        MedicalDAO medicalDAO = new MedicalDAO();
        if (medical_ != null) {
            if (medicalDAO.deleteMedical(medical_)) {
                System.out.println("Delete successfull");
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText("Succesfully Deleted!");
                a1.setHeaderText(null);
                a1.showAndWait();
                
            }else{
                System.out.println("Delete Error");
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText("Error!");
                a1.setHeaderText(null);
                a1.showAndWait();  
            }
        }

    }

    @FXML
    private void searchBtnActionController(ActionEvent event) {
        String studentID = null;
        String date, courseID;

        courseID = (String) combCourseID.getValue();
        studentID = txtStudentID.getText();
        try {
            date = txtDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NullPointerException x) {
            date = "0";
        }

        if (tab1 == true) {
            //-------------------------tab 1---------------------------------------
            
            
            ObservableList<Medical> medicalFormListFilted = FXCollections.observableArrayList();

            MedicalDAO medicalDAO = new MedicalDAO();

            ArrayList<Medical> students;

            if ((studentID.trim().isEmpty()) && (date == "0")) {
                students = medicalDAO.viewAllMedicalForm();
            } else if (studentID.trim().isEmpty()) {
                students = medicalDAO.viewMedicalFormFiltedBySubmitDate(date);
            } else if (date == "0") {
                students = medicalDAO.viewMedicalFormFiltedByStudentID(studentID);
            } else {
                students = medicalDAO.viewAllMedicalFormFilted(studentID, date);
            }

            for (Medical student : students) {
                medicalFormListFilted.add(student);
            }

            colRefNo.setCellValueFactory(new PropertyValueFactory<>("MedicalRefNo"));
            colStudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
            colSubmitDate.setCellValueFactory(new PropertyValueFactory<>("MedicalSubmitDate"));
            colStartDate.setCellValueFactory(new PropertyValueFactory<>("MedicalStartDate"));
            colEndDate.setCellValueFactory(new PropertyValueFactory<>("MedicalEndDate"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("MedicalStatus"));

            table.setItems(medicalFormListFilted);

        } else {
            //-------------------------tab 2---------------------------------------
            ObservableList<Medical> medicalList = FXCollections.observableArrayList();

            MedicalDAO medicalDAO = new MedicalDAO();

            ArrayList<Medical> students;

            if ((studentID.trim().isEmpty()) && (date == "0")) {
                students = medicalDAO.viewMedicalByCourseID(courseID);
            } else if ((studentID.trim().isEmpty()) && (courseID.trim().isEmpty())) {
                students = medicalDAO.viewMedicalByDateTime(date);
            } else if ((date == "0") && (courseID.trim().isEmpty())) {
                students = medicalDAO.viewMedicalByStudentID(studentID);
            } else if (courseID.trim().isEmpty()) {
                students = medicalDAO.viewMedicalByStudentIdDateTime(studentID, date);
            } else if (date == "0") {
                students = medicalDAO.viewMedicalByCourseIdStudentID(courseID, studentID);

            } else {
                students = medicalDAO.viewMedicalByCourseIdDateTime(courseID, date);
            }

            for (Medical student : students) {
                medicalList.add(student);
            }

            colRefNo_cm.setCellValueFactory(new PropertyValueFactory<>("MedicalRefNo"));
            colStudentID_cm.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
            colCourseID_cm.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
            colDateTime_cm.setCellValueFactory(new PropertyValueFactory<>("Date"));
            colType_cm.setCellValueFactory(new PropertyValueFactory<>("Type"));

            medicalCourseModule.setItems(medicalList);

        }

    }

    @FXML
    private void medicalFormActionController(Event event) {
        combCourseID.setDisable(true);
        tab1 = true;
        tab2 = false;
    }

    @FXML
    private void medicalCourseModuleActionController(Event event) {
        combCourseID.setDisable(false);
        tab1 = false;
        tab2 = true;
    }

    @FXML
    private void tableOnmouseClick(MouseEvent event) {

        medical_ = table.getSelectionModel().getSelectedItem();
        
        

    }

    @FXML
    private void updateMedicalBtnActionController(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerMedicalsUpdate.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
