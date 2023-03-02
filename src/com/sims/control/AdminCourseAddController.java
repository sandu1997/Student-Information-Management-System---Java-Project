/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Course;
import com.sims.model.CourseDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminCourseAddController implements Initializable {

    private TextField txt_field_;
    @FXML
    private ComboBox<String> combo_dpt;
    @FXML
    private TextField txt_field_name;
    @FXML
    private Button btn;

    private Course course_ = null;

    private AdminCourseController parent_ = null;
    @FXML
    private TextField txt_field_id;
    @FXML
    private Label Coodinator;
    @FXML
    private ComboBox<Integer> combo_year;
    @FXML
    private ComboBox<Integer> combo_sem;
    @FXML
    private TextField txt_field_crdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_year.getItems().addAll(1, 2,3, 4);
        combo_sem.getItems().addAll(1, 2);
        combo_dpt.getItems().addAll("dpt01","dpt02","dpt03","dpt04");
        
    }

    @FXML
    private void btn_Actionhandler(ActionEvent event) {
        CourseDAO dao = new CourseDAO();
        Course course = new Course();

        course.setCourseid(txt_field_id.getText());
        course.setCourseName(txt_field_name.getText());
        course.setCredit(Integer.parseInt(txt_field_crdit.getText()));
        course.setCourseyear(combo_year.getValue());
        course.setCourseSemester(combo_sem.getValue());
        course.setCourse_dpt(combo_dpt.getValue());

        if (course_ == null) {
            if (dao.saveCourse(course)) {
                System.out.println("Course Added");
                parent_.settable(course.getCourseyear(), course.getCourseSemester());
                Stage stage = (Stage) btn.getScene().getWindow();
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(course.getCourseid() + " Succesfully Added");
                a1.setHeaderText(null);
                a1.showAndWait();
                stage.close();
            } else {
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Error");
                a1.setContentText(course.getCourseid() + "Adding Error");
                a1.setHeaderText(null);
                a1.showAndWait();
            }
        } else {
            if (dao.updateCourse(course)) {
                System.out.println("Course Updated");
                parent_.settable(course.getCourseyear(), course.getCourseSemester());
                Stage stage = (Stage) btn.getScene().getWindow();
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Done");
                a1.setContentText(course.getCourseid() + " Succesfully Updated");
                a1.setHeaderText(null);
                a1.showAndWait();
                stage.close();
            } else {
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Error");
                a1.setContentText(course.getCourseid() + "Updating Error");
                a1.setHeaderText(null);
                a1.showAndWait();
//                System.out.println("Course Updating Error");
            }
        }
    }

    public void setAddScene(AdminCourseController parent) {
        this.parent_ = parent;
    }

    public void setUpdateScene(Course course_, AdminCourseController parent) {
        this.parent_ = parent;
        this.course_ = course_;

        txt_field_id.setText(course_.getCourseid());
        txt_field_name.setText(course_.getCourseName());
        txt_field_crdit.setText(Integer.toString(course_.getCredit()));

        combo_dpt.setValue(course_.getCourse_dpt());
        combo_year.setValue(course_.getCourseyear());
        combo_sem.setValue(course_.getCourseSemester());

        btn.setText("Update Course");
        btn.setStyle("-fx-background-color: #EDD817;");
    }

}
