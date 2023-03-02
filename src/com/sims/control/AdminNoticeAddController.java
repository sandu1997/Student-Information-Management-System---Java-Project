/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Notice;
import com.sims.model.NoticeDOA;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminNoticeAddController implements Initializable {

    @FXML
    private TextField txt_notice_title;
    @FXML
    private TextArea txt_notice_content;
    @FXML
    private Button btn_notice_add;

    private Notice notice_ = null;

    @FXML
    private TextField txt_notice_ID;

    private String publiser = "Admin";

    AdminDashboardController admindash;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_notice_addActionhandel(ActionEvent event) throws IOException {
        Notice notice = new Notice();

        notice.setID(txt_notice_ID.getText());
        notice.setTitle(txt_notice_title.getText());
        notice.setContent(txt_notice_content.getText());

        NoticeDOA noticedoa = new NoticeDOA();

        if (notice_ == null) {
            notice.setPublisher(publiser);
            if (noticedoa.saveNotice(notice)) {
                System.out.println("Add");
                alertINFORMATION("Succesfully added");
                done();
            } else {
                alertError("Adding Error");
                System.out.println("Add Error");
            }
        } else {
            notice.setPublisher(notice_.getPublisher());
            if (noticedoa.updateNotice(notice)) {
                alertINFORMATION("Succesfully Updated");
                System.out.println("Updated");
                done();
            } else {
                alertError("Updating Error");
                System.out.println("Update Error");
            }
        }

    }

    public void setAddScene(String publiser, AdminDashboardController admindash) {
        this.publiser = publiser;
        this.admindash = admindash;
    }

    public void setUpdateScene(Notice notice_, AdminDashboardController admindash) {
        this.admindash = admindash;
        this.notice_ = notice_;
        txt_notice_ID.setText(notice_.getID());
        txt_notice_content.setText(notice_.getContent());
        txt_notice_title.setText(notice_.getTitle());
        publiser = notice_.getPublisher();

        btn_notice_add.setText("Update Notice");
        btn_notice_add.setStyle("-fx-background-color: #EDD817;");

    }

    private void done() throws IOException {
//        Stage primaryStage = new Stage();
//        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminDash.fxml"));
//        Parent root = loder.load();
//        AdminDashboardController admindash = loder.getController();
//        admindash.setUserID(publiser);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        admindash.settable();

        Stage stage = (Stage) btn_notice_add.getScene().getWindow();
        stage.close();
    }

    private void alertINFORMATION(String msg) {
        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("Done");
        a1.setContentText(msg);
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    
    private void alertError(String msg) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Error");
        a1.setContentText(msg);
        a1.setHeaderText(null);
        a1.showAndWait();
    }

}
