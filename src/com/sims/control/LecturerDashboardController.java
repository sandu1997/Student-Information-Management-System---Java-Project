/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Lecturer;
import com.sims.model.LecturerDAO;
import com.sims.model.Notice;
import com.sims.model.NoticeDOA;
import com.sims.model.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class LecturerDashboardController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button btn_notice;
    @FXML
    private Button btn_course;
    @FXML
    private Button btn_exam;
    @FXML
    private Button btn_student;
    @FXML
    private VBox notice_vbox;
    @FXML
    private DatePicker cmb_date;
    @FXML
    private Button btn_Search;
    @FXML
    private TableView<Notice> table_view;
    @FXML
    private TableColumn<Notice, String> tale_col_date;
    @FXML
    private TableColumn<Notice, String> table_col_titel;
    @FXML
    private TableColumn<Notice, String> table_col_publisher;
    @FXML
    private Button btn_Profile;
    @FXML
    private Button btn_SignOut;
    @FXML
    private Label lbl_name;
    
    Staff staff = new Staff();
    LecturerDAO lecturerDAO = new LecturerDAO();    
    private FXMLLoader loder = null;   
    Pane pane;
    String lecturerid = null;
    @FXML
    private Button btn_notice_view;
    
    Notice notice = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNoticeTable();
    }

    @FXML
    private void btn_noticeActionHandel(ActionEvent event) {
        mainPane.setRight(notice_vbox);
    }

    @FXML
    private void btn_courseActionHandel(ActionEvent event) {
//        loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentuserProfile.fxml"));
//        pane = loder.load();
//        StudentuserProfileController stt = loder.getController();
//        mainPane.setRight(pane);
    }

    @FXML
    private void btn_examActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerExam.fxml"));
        pane = loder.load();
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_studentActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStudent.fxml"));
        pane = loder.load();
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btn_ProfileActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerProfileUpdate.fxml"));
        pane = loder.load();
        LecturerProfileUpdateController lec = loder.getController();
        lec.setUser(lecturerid);
        mainPane.setRight(pane);
        
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
    
    private void setNoticeTable() {
        ObservableList<Notice> obslist = FXCollections.observableArrayList();

        NoticeDOA dao = new NoticeDOA();
        ArrayList<Notice> notices = dao.getAllNotice();

        for (Notice notice : notices) {
            obslist.add(notice);
            System.out.println(notice.getID() + " " + notice.getTitle() + " " + notice.getPublisher());
        }

        tale_col_date.setCellValueFactory(new PropertyValueFactory<>("ID"));
        table_col_titel.setCellValueFactory(new PropertyValueFactory<>("title"));
        table_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        table_view.setItems(obslist);
    }
    
    public void setUser(String userid){
        this.lecturerid = userid;
        staff = lecturerDAO.getLecturer(lecturerid);
        lbl_name.setText(staff.getFirstName());
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
    private void table_viewOnMouseCicked(MouseEvent event) throws IOException {
        notice = table_view.getSelectionModel().getSelectedItem();
    }
}
