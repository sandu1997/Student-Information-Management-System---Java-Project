/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Attendance;
import com.sims.model.AttendanceDAO;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
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
public class TechnicalOfficerAttendanceController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox notice_vbox;
    @FXML
    private TextField txtStuId;
    private DatePicker txtDate;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<Attendance> tableAttendance;
    @FXML
    private TableColumn<Attendance,String> colStudent;
    @FXML
    private TableColumn<Attendance,String> colStatus;
    @FXML
    private TableColumn<Attendance,String> colDateTime;
    @FXML
    private TableColumn<Attendance,String> colSessionID;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    
    
    @FXML
    private ComboBox combStatus;
    ObservableList<String> Status=FXCollections.observableArrayList("All","Present","Absent","Medical");
    
    
    @FXML
    private ComboBox combCourseID;
    ObservableList<String> list = FXCollections.observableArrayList();
    TechnicalOfficerDAO technicalOfficerDAO = new TechnicalOfficerDAO();
    
    
    
    @FXML
    private Button btnUpdate;
  
    @FXML
    private ComboBox combType;
    ObservableList<String> type=FXCollections.observableArrayList("Theory","Practical");
    
    

    /**
     * Initializes the controller class.
     */
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
        
        //status set
        combStatus.setItems(Status);
        combType.setItems(type);
        
        
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String date, courseID, state, type, courseType = null, studentID;
        courseID = (String) combCourseID.getValue();
        studentID=txtStuId.getText();
        
        
        if(null == (String) combStatus.getValue()){
            state="All";
        }else switch ((String) combStatus.getValue()) {
            case "Present":
                state="1";
                break;
            case "Absent":
                state="0";
                break;
            case "Medical":
                state="2";
                break;
            default:
                state="All";
                break;
        }
        
        
        
        type=(String) combType.getValue();
        if ("Theory".equals(type)){
            courseType="T";
        }
        if ("Practical".equals(type)){
            courseType="P";
        }
        
        
        
        
        
        try {
            date = txtDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NullPointerException x) {
            date = "0";
        }
        
     
        
        ObservableList<Attendance> AttendanceListbySession = FXCollections.observableArrayList();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        ArrayList<Attendance> students;

            if ((studentID.trim().isEmpty()) && (state.trim().isEmpty())) {
                students=attendanceDAO.getAllAttendanceByCourse(courseID, courseType);
            } else if (studentID.trim().isEmpty() ) {
                students=attendanceDAO.getAllAttendanceByCourseAndStatus(courseID, courseType, state);
            } else{
                students=attendanceDAO.getAttendanceByStudent(courseID, studentID, courseType, state);
            }
            

        for (Attendance student : students) {
            AttendanceListbySession.add(student);
            System.out.println(student.getStudentID()+" "+student.getAttendanceStatus());
        }

        colSessionID.setCellValueFactory(new PropertyValueFactory<>("SessionID"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        colDateTime.setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("AttendanceStatus"));
        
        tableAttendance.setItems(AttendanceListbySession);
        
        
        
        
    
        
        
        
        
        
    }

    @FXML
    private void searchBtnActionHandler(MouseEvent event) {
        
        
    }

    @FXML
    private void addAtendaceBtnActionHandler(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerAttendanceAdd.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void deleteAttendanceBtnActionHandler(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerAttendanceDelete.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void updateAtendaceBtnActionHandler(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerAttendanceUpdate.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
}
