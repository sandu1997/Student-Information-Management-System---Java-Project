/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Course;
import com.sims.model.CourseDAO;
import com.sims.model.Student;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC PLUS
 */
public class StudentCourseDetailsController implements Initializable {
    private Student student;
    @FXML
    private VBox notice_vbox;
    @FXML
    private Label label1;
    @FXML
    private Label label_level;
    @FXML
    private Label label_sem;
    @FXML
    private TableView<Course> tableview;
    @FXML
    private TableColumn<Course, String> colcoursecode;
    @FXML
    private TableColumn<Course, String> colcoursename;
    @FXML
    private TableColumn<Course, String> colcredit;
    
    CourseDAO dao = new CourseDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colcoursecode.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        colcoursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colcredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }

public void settable(int level, int sem) {
        ObservableList<Course> obslist = FXCollections.observableArrayList();
        ArrayList<Course> notices = dao.getLveltAllCourseList(level, sem);

        for (Course notice : notices) {
            obslist.add(notice);
//            System.out.println(notice.getID()+" "+notice.getTitle()+" "+notice.getPublisher());
        }

        tableview.setItems(obslist);
    }    
    
    public void setStudent(Student student) {
        this.student = student;
        
        settable(student.getYear(), student.getSemester());
        label_level.setText("Level "+student.getYear());
        label_sem.setText("Semester "+student.getSemester());
    }
    
}
