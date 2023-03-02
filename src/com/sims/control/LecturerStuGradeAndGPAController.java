/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.CourseDAO;
import com.sims.model.ExamDAO;
import com.sims.model.Grade;
import com.sims.model.Student;
import com.sims.model.StudentDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LecturerStuGradeAndGPAController implements Initializable {

    @FXML
    private ComboBox<String> cmb_Level;
    @FXML
    private ComboBox<String> cmb_Semester;
    @FXML
    private ComboBox<String> cmb_CourseCode;
    @FXML
    private Button btn_StuBack;
    @FXML
    private TableColumn<Grade, String> tbl_CourseCode;
    @FXML
    private TableColumn<Grade, String> tbl_StudentId;
    @FXML
    private TableColumn<Grade, String> tbl_Garde;
    @FXML
    private TableView<Grade> tableGrade;
    @FXML
    private Button btn_SearchGPA;
    @FXML
    private TableView<Student> tableGPA;
    @FXML
    private TableColumn<Student, Double> tbl_GPA;
    @FXML
    private TableColumn<Student, String> tbl_GPA_StudentId;
    @FXML
    private Button btn_SearchCourse;
    @FXML
    private Button btn_SearchStuID;
    @FXML
    private TextField txt_StuID;
    @FXML
    private ComboBox<String> cmb_GradeLevel;
    @FXML
    private ComboBox<String> cmb_GradeSemester;
    @FXML
    private VBox vbox_StuGradeAndGPA;
    
    VBox vBox;
    private FXMLLoader loder = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_Level.getItems().addAll("1", "2", "3", "4");
        cmb_Semester.getItems().addAll("1", "2");
        cmb_GradeLevel.getItems().addAll("1", "2", "3", "4");
        cmb_GradeSemester.getItems().addAll("1", "2");
        cmb_CourseCode.setItems(courseId);

    }    

    @FXML
    private void btn_SearchCourseActionPerformed(ActionEvent event) {
        if (cmb_CourseCode.getValue().isEmpty() || "Course Code".equals(cmb_CourseCode.getValue())) {
        
        } else {
            SetTabaleGarde(cmb_CourseCode.getValue());
            clearField();
        }
    }

    @FXML
    private void btn_SearchStuIDActionPerformed(ActionEvent event) {
        if (isFieldsEmpty()) {
        
        } else {
            SetTabaleGarde(txt_StuID.getText(),Integer.parseInt(cmb_GradeLevel.getValue()) ,Integer.parseInt(cmb_GradeSemester.getValue()));
            clearField();
        }
    }


    @FXML
    private void btn_StuBackActionPerformed(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStudent.fxml"));
        vBox = loder.load();
        vbox_StuGradeAndGPA.getChildren().setAll(vBox);
    }
    

    @FXML
    private void btn_SearchGPAActionPerformed(ActionEvent event) {
        if (cmb_Level.getValue().isEmpty() || "Level".equals(cmb_Level.getValue()) || cmb_Semester.getValue().isEmpty() || "Semester".equals(cmb_Semester.getValue())) {
        
        } else {
            SetTabaleGPA(Integer.parseInt(cmb_Level.getValue()), Integer.parseInt(cmb_Semester.getValue()));
            clearField();
        }
        
    }
    
    
    
    private void SetTabaleGPA(int year,int sem) {
        ObservableList<Student> obslist = FXCollections.observableArrayList();
        StudentDAO sdao = new StudentDAO();
        CourseDAO cdao = new CourseDAO();

        ArrayList<Student> studentlist = sdao.getAllStudent(year, sem);

        for (Student student : studentlist) {
            student.setGpa(cdao.getStudentAllCourseList(student.getUserID(), year, sem));
            obslist.add(student);
        }
        
        tbl_GPA_StudentId.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        tbl_GPA.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        
        tableGPA.setItems(obslist);
    }
    
    private void SetTabaleGarde(String CourseCode) {
        ObservableList<Grade> gobslist = FXCollections.observableArrayList();
        CourseDAO cdoa = new CourseDAO();
        ArrayList<Grade> grades = cdoa.getAllCoursegrade(CourseCode);
        
        for (Grade grade : grades) {
            gobslist.add(grade);
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        tbl_Garde.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        tableGrade.setItems(gobslist);
    }
    
    private void SetTabaleGarde(String studentId, int level, int sem) {
        ObservableList<Grade> gobslist = FXCollections.observableArrayList();
        CourseDAO cdoa = new CourseDAO();
        ArrayList<Grade> grades = cdoa.getStudentCoursegrade(studentId, level, sem);
        
        for (Grade grade : grades) {
            gobslist.add(grade);
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        tbl_Garde.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        tableGrade.setItems(gobslist);
    }
    
    
    private void clearField() {
        cmb_Level.setValue(null);
        cmb_Level.setPromptText("Level");
        cmb_Semester.setValue(null);
        cmb_Semester.setPromptText("Semester");
        cmb_CourseCode.setValue(null);
        cmb_CourseCode.setPromptText("Course Code");
        cmb_GradeLevel.setValue(null);
        cmb_GradeLevel.setPromptText("Level");
        cmb_GradeSemester.setValue(null);
        cmb_GradeSemester.setPromptText("Semester");
        txt_StuID.setText(null);
        txt_StuID.setPromptText("Student ID");
    }
    
    //ComboBox CourseID froms database
    private ArrayList<String> courseid() {
        ExamDAO examDAO = new ExamDAO();
        
        ArrayList<String> id = new ArrayList<String>();
        id = examDAO.getExamCourseId();
        return id;
    }
    ObservableList<String> courseId = FXCollections.observableArrayList(courseid());

    private boolean isFieldsEmpty() {
     return (cmb_GradeLevel.getValue().isEmpty() || "Level".equals(cmb_GradeLevel.getValue()) || 
             cmb_GradeSemester.getValue().isEmpty() || "Semester".equals(cmb_GradeSemester.getValue()) || 
             txt_StuID.getText().isEmpty() || "Student ID".equals(txt_StuID.getText()) );
    }
}
