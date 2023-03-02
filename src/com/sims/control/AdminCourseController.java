/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Course;
import com.sims.model.CourseDAO;
import com.sims.model.Notice;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminCourseController implements Initializable {

    @FXML
    private ComboBox<Integer> combo_year;
    @FXML
    private ComboBox<Integer> combo_sem;
    @FXML
    private Button btn_select;
    @FXML
    private TableView<Course> tableview_course;
    @FXML
    private TableColumn<Course, String> col_courseid;
    @FXML
    private TableColumn<Course, String> col_coursename;
    @FXML
    private TableColumn<Course, String> col_dpt;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private TableColumn<Course, Integer> col_courseCredit;

    CourseDAO dao = new CourseDAO();

    Course course_ = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        combo_year.getItems().addAll(1, 2,3, 4);
        combo_sem.getItems().addAll(1, 2);

        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_dpt.setCellValueFactory(new PropertyValueFactory<>("course_dpt"));
        col_courseCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));

        

    }

    @FXML
    private void btn_selectActionhandel(ActionEvent event) {
        settable(combo_year.getValue(), combo_sem.getValue());
    }

    @FXML
    private void tableviewOnMouseclicked(MouseEvent event) {
        course_ = tableview_course.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btn_addActionhandler(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminCourseAdd.fxml"));
        Parent root = loder.load();
        AdminCourseAddController child = loder.getController();
        child.setAddScene(this);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    private void btn_updateActionhandler(ActionEvent event) throws IOException {
        if (course_ != null) {
            Stage primaryStage = new Stage();
            FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminCourseAdd.fxml"));
            Parent root = loder.load();
            AdminCourseAddController child = loder.getController();
            child.setUpdateScene(course_, this);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    @FXML
    private void btn_deleteActionhandler(ActionEvent event) {
        if (course_ != null) {
            if (dao.deleteCourse(course_)) {
                System.out.println(course_.getCourseid() + " Succesfully deleted");
                alertINFORMATION(course_.getCourseid() + " Succesfully deleted");
                settable(course_.getCourseyear(),course_.getCourseSemester());
            } else {
                alertError(course_.getCourseid() + " delete Error");
                System.out.println(course_.getCourseid() + " delete Error");
            }
        }
    }

    public void settable(int level, int sem) {
        ObservableList<Course> obslist = FXCollections.observableArrayList();
        ArrayList<Course> notices = dao.getLveltAllCourseList(level, sem);

        for (Course notice : notices) {
            obslist.add(notice);
//            System.out.println(notice.getID()+" "+notice.getTitle()+" "+notice.getPublisher());
        }

        tableview_course.setItems(obslist);
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
