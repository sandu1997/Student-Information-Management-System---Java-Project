/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.CourseDAO;
import com.sims.model.Notice;
import com.sims.model.NoticeDOA;
import com.sims.model.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class AdminDashboardController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    
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
    private BorderPane mainPane;
    @FXML
    private Button btn_AddNotice;
    @FXML
    private Button btn_update;
    @FXML
    private TableView<Notice> table_view;
    @FXML
    private TableColumn<Notice, String> tale_col_date;
    @FXML
    private TableColumn<Notice, String> table_col_titel;
    @FXML
    private TableColumn<Notice, String> table_col_publisher;

    private String userID;

    NoticeDOA dao = new NoticeDOA();
    @FXML
    private TextField txt_feild_search;
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_delete;

    Notice notice_ = null;
    @FXML
    private Button btn_All;

    @FXML
    private Button btn_SignOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        System.out.println(userID + " init");
        tale_col_date.setCellValueFactory(new PropertyValueFactory<>("ID"));
        table_col_titel.setCellValueFactory(new PropertyValueFactory<>("title"));
        table_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        settable();

    }

    @FXML
    private void btn_noticeActionHandel(ActionEvent event) {
        mainPane.setRight(notice_vbox);
        settable();
    }

    @FXML
    private void btn_userProfileActionHandel(ActionEvent event) {
        pane = getpane("AdminUserprofile.fxml");
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_courseActionHandel(ActionEvent event) {
        pane = getpane("AdminCourse.fxml");
        mainPane.setRight(pane);
    }

    @FXML
    private void btn_timetableActionHandel(ActionEvent event) {
        pane = getpane("AdminTimeTable.fxml");
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
    private void btn_AddNoticeActionHandler(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminNoticeAdd.fxml"));
        Parent root = loder.load();
        AdminNoticeAddController adminoticeadd = loder.getController();
        if (adminoticeadd != null) {
            adminoticeadd.setAddScene(userID, this);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void btn_update_test(ActionEvent event) throws IOException {
        if (notice_ != null) {
            Stage primaryStage = new Stage();
            FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminNoticeAdd.fxml"));
            Parent root = loder.load();
            AdminNoticeAddController adminoticeadd = loder.getController();
            notice_.setPublisher(userID);
            if (adminoticeadd != null) {
                adminoticeadd.setUpdateScene(notice_, this);
            }
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void setUserID(String id) {
        this.userID = id;
//        System.out.println(this.userID + "when set");
    }

    @FXML
    private void btn_searchActionHandeler(ActionEvent event) {
        ObservableList<Notice> obslist = FXCollections.observableArrayList();
        ArrayList<Notice> notices = dao.searchNotice(txt_feild_search.getText());

        for (Notice notice : notices) {
            obslist.add(notice);
//            System.out.println(notice.getID()+" "+notice.getTitle()+" "+notice.getPublisher());
        }

        table_view.setItems(obslist);
    }

    @FXML
    private void btn_deleteActionhandler(ActionEvent event) {
        if (notice_ != null) {
            if (dao.deleteNotice(notice_)) {
                System.out.println(notice_.getID() + " Succesfully deleted");
                settable();
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(notice_.getID() + " Succesfully deleted");
                a1.setHeaderText(null);
                a1.showAndWait();
            } else {
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Error");
                a1.setContentText("Delteing Error");
                a1.setHeaderText(null);
                a1.showAndWait();
//                System.out.println(notice_.getID() + " delete Error");
            }
        }
    }

    @FXML
    private void table_viewClickhnadler(MouseEvent event) {

        notice_ = table_view.getSelectionModel().getSelectedItem();

    }

    public void settable() {
        ObservableList<Notice> obslist = FXCollections.observableArrayList();
        ArrayList<Notice> notices = dao.getAllNotice();

        for (Notice notice : notices) {
            obslist.add(notice);
//            System.out.println(notice.getID()+" "+notice.getTitle()+" "+notice.getPublisher());
        }

        table_view.setItems(obslist);
    }

    @FXML
    private void btn_AllActionHandeler(ActionEvent event) {
        settable();
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
