/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Notice;
import com.sims.model.NoticeDOA;
import com.sims.model.Student;
import com.sims.model.StudentDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class StudentDashboardController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button btn_notice;
    @FXML
    private Button btn_userProfile;
    @FXML
    private Button btn_course;
    @FXML
    private Button btn_timetable;
    @FXML
    private VBox notice_vbox;

    Pane pane;
    @FXML
    private Button btn_gradeAndGPA;
    @FXML
    private Button btn_Attendance;
    @FXML
    private Button btn_medical;
    @FXML
    private TableView<Notice> table_view;
    @FXML
    private TableColumn<Notice, String> tale_col_date;
    @FXML
    private TableColumn<Notice, String> table_col_titel;
    @FXML
    private TableColumn<Notice, String> table_col_publisher;

    ObservableList<Notice> obslist = FXCollections.observableArrayList();

    NoticeDOA dao = new NoticeDOA();

    Notice notice = null;

    private Student student;

    private Stage primaryStage = new Stage();
    private FXMLLoader loder = null;
    private Parent root = null;
    @FXML
    private Button btn_notice_view;
    @FXML
    private Button btn_SignOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Notice> notices = dao.getAllNotice();

        for (Notice notice : notices) {
            obslist.add(notice);
//            System.out.println(notice.getID() + " " + notice.getTitle() + " " + notice.getPublisher());
        }

        tale_col_date.setCellValueFactory(new PropertyValueFactory<>("ID"));
        table_col_titel.setCellValueFactory(new PropertyValueFactory<>("title"));
        table_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        table_view.setItems(obslist);

    }

    @FXML
    private void btn_noticeActionHandel(ActionEvent event) {
        mainPane.setRight(notice_vbox);
    }

    @FXML
    private void btn_userProfileActionHandel(ActionEvent event) throws IOException {
//        pane = getpane("UserProfile.fxml");
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentuserProfile.fxml"));
        pane = loder.load();
        StudentuserProfileController stt = loder.getController();
        stt.setStudent(student);
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_courseActionHandel(ActionEvent event) throws IOException {
//        pane = getpane("StudentCourseDetails.fxml");
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentCourseDetails.fxml"));
        pane = loder.load();
        StudentCourseDetailsController stb = loder.getController();
        stb.setStudent(student);
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_timetableActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentTimeTable.fxml"));
        pane = loder.load();
        StudentTimeTableController stb = loder.getController();
        stb.setStudent(student);
        mainPane.setRight(pane);

    }

    private Pane getpane(String fxmlname) {
        Pane pane = null;

        try {

            pane = FXMLLoader.load(getClass().getResource("/com/sims/view/" + fxmlname));

        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pane;
    }

    @FXML
    private void btn_gradeAndGPAActionHandel(ActionEvent event) throws IOException {
//        pane = getpane("StudentGradeandGPA.fxml");
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentGradeandGPA.fxml"));
        pane = loder.load();
        StudentGradeandGPAController stb = loder.getController();
        stb.setStudent(student);
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_AttendanceActionHandel(ActionEvent event) throws IOException {
//        pane = getpane("StudentAttendance.fxml");
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentAttendance.fxml"));
        pane = loder.load();
        StudentAttendanceController stb = loder.getController();
        stb.setStudent(student);
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_medicalActionHandel(ActionEvent event) throws IOException {
//        pane = getpane("StudentMedicals.fxml");
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentMedicals.fxml"));
        pane = loder.load();
        StudentMedicalsController stb = loder.getController();
        stb.setStudent(student);
        mainPane.setRight(pane);
    }

    public void setStudentid(String studentid) {
        StudentDAO dao = new StudentDAO();
        this.student = dao.getStudent(studentid);
    }

    @FXML
    private void btn_notice_viewActionhandel(ActionEvent event) throws IOException {
        if (notice != null) {
            Stage primaryStage = new Stage();
            FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/NoticeView.fxml"));
            Parent root = loder.load();
            NoticeViewController child = loder.getController();
            child.setNotice(notice);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    @FXML
    private void table_viewOnMouseCicked(MouseEvent event) {
        notice = table_view.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btn_SignOutActionHandel(ActionEvent event) throws IOException {
        Stage stage_ = (Stage) btn_SignOut.getScene().getWindow();
        stage_.close();

        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
                
            }
            
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
            
        });
        
        stage.setScene(scene);
        stage.show();
    }

}
