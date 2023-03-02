/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LecturerStudentController implements Initializable {

    @FXML
    private Button btn_stuDetails;
    @FXML
    private Button btn_StuEligibility;
    @FXML
    private Button btn_StuAttendance;
    @FXML
    private Button btn_StuMarks;
    @FXML
    private Button btn_StuGPAGrade;
    @FXML
    private Button btn_StuMedical;
    @FXML
    private VBox vbox_Student;
    VBox vBox;
    
    private FXMLLoader loder = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_stuDetailsActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStuDetails.fxml"));
        vBox = loder.load();
        vbox_Student.getChildren().setAll(vBox);
    }

    @FXML
    private void btn_StuEligibilityActionHandel(ActionEvent event) {
    }

    @FXML
    private void btn_StuAttendanceActionHandel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStuAttendance.fxml"));
        vBox = loder.load();
        vbox_Student.getChildren().setAll(vBox);
    }

    @FXML
    private void btn_StuMarksActionHadel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStuMarks.fxml"));
        vBox = loder.load();
        vbox_Student.getChildren().setAll(vBox);
    }

    @FXML
    private void btn_StuGPAGradeActionHadel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStuGradeAndGPA.fxml"));
        vBox = loder.load();
        vbox_Student.getChildren().setAll(vBox);
    }

    @FXML
    private void btn_StuMedicalActionHadel(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStuMedical.fxml"));
        vBox = loder.load();
        vbox_Student.getChildren().setAll(vBox);
    }
    
}
