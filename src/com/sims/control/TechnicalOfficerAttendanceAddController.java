/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Attendance;
import com.sims.model.Session;
import com.sims.model.SessionDAO;
import com.sims.model.Student;
import com.sims.model.StudentDAO;
import com.sims.model.TechnicalOfficer;
import com.sims.model.TechnicalOfficerDAO;
import com.sims.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerAttendanceAddController implements Initializable {

   
    
    
    @FXML
    private Button btnOk;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnReset;
  
    
    @FXML
    private TableView<Student> tableAttendance;
    @FXML
    private TableColumn<Student, String> colStudentID;
    
    @FXML
    private ComboBox combStudentYear;
    ObservableList<String> yearlist=FXCollections.observableArrayList();
    
    Student student = new Student();
    StudentDAO studentDAO = new StudentDAO();
    @FXML
    private TableView<Session> tableSession;
    @FXML
    private TableColumn<Session, String> colSessionID;
    @FXML
    private TableColumn<Session, String> colDateTime;
    @FXML
    private TableColumn<Session, String> colCourseID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // ---------- year list pass to combobox-----------------
        ArrayList<Student> year;
        try {
            year= studentDAO.studentYear();
            for(Student student : year){
                yearlist.add(String.valueOf(student.getYear()));
            }
            combStudentYear.setItems(yearlist);
        } catch (SQLException ex) {
            Logger.getLogger(TechnicalOfficerMedicalsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        




//---------------------------session table----------------------
        SessionDAO sessionDAO = new SessionDAO();
        ObservableList<Session> sessionList = FXCollections.observableArrayList();
        ArrayList<Session> sessions = sessionDAO.viewAllSessions();

        for(Session session : sessions){
            sessionList.add(session);
        }
        colSessionID.setCellValueFactory(new PropertyValueFactory<>("SessionID"));
        colDateTime.setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("CourseID"));

        tableSession.setItems(sessionList);




        
    }    

    @FXML
    private void actionButtonHandler(ActionEvent event) {
        String year;

        year=(String) combStudentYear.getValue();

        ObservableList<Student> studentList = FXCollections.observableArrayList();
        
        User user = new User();
    
        ArrayList<Student> students = studentDAO.getStudentByDepartment(year, "dpt02");

        System.out.println(year);
        System.out.println(user.getUserID());
        for(Student student : students){
            studentList.add(student);
        }

        
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("UserID"));

        tableAttendance.setItems(studentList);
    
    }


    @FXML
    private void resetMedicalAction(ActionEvent event) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
         
        stage.close();
        
    }

    @FXML
    private void addAttendanceActionController(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/TechnicalOfficerAttendanceAddBox.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
}
