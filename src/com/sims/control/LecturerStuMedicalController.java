/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.ExamDAO;
import com.sims.model.Medical;
import com.sims.model.MedicalDAO;
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
public class LecturerStuMedicalController implements Initializable {

    @FXML
    private ComboBox<String> cmb_SearchBy;
    @FXML
    private TextField txt_SearchBy;
    @FXML
    private Button btn_Search;
    @FXML
    private Button btn_StuBack;
    @FXML
    private TableColumn<Medical, String> tbl_StudentId;
    @FXML
    private TableColumn<Medical, String> tbl_StartDate;
    @FXML
    private TableColumn<Medical, String> tbl_EndDate;
    @FXML
    private TableColumn<Medical, String> tbl_CourseCode;
    @FXML
    private TableColumn<Medical, String> tbl_Type;
    @FXML
    private TableColumn<Medical, String> tbl_Status;
    @FXML
    private TableView<Medical> table_medical;
    @FXML
    private VBox vbox_StuMedical;
    @FXML
    private ComboBox<String> cmb_CourseCode;
    @FXML
    private ComboBox<String> cmb_type;
    
    VBox vBox;
    private FXMLLoader loder = null;
    ExamDAO examDAO = new ExamDAO();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_SearchBy.getItems().addAll("Student ID", "Course Code");
        cmb_type.getItems().addAll("T", "P");
        txt_SearchBy.setVisible(true);
        cmb_CourseCode.setVisible(false);
        cmb_CourseCode.setPromptText("Course Code");
        txt_SearchBy.setPromptText("Student ID");
        cmb_CourseCode.setItems(courseId);
    }    

    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
        if (cmb_SearchBy.getValue().isEmpty() && cmb_type.getValue().isEmpty() && txt_SearchBy.getText().isEmpty() && cmb_CourseCode.getValue().isEmpty()) {
        
        } else {
            if (null != cmb_SearchBy.getValue()) switch (cmb_SearchBy.getValue()) {
                case "Student ID":
                    SetTabaleByStudenID(txt_SearchBy.getText(), cmb_type.getValue());
                    clearField();
                    break;
                case "Course Code":
                    SetTabaleByCourseID(cmb_CourseCode.getValue(), cmb_type.getValue());
                    clearField();
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    private void btn_StuBackActionPerformed(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStudent.fxml"));
        vBox = loder.load();
        vbox_StuMedical.getChildren().setAll(vBox);
    }

    @FXML
    private void cmb_SearchByActionPerformed(ActionEvent event) {
        if ("Student ID".equals(cmb_SearchBy.getValue())) {
            cmb_CourseCode.setVisible(false);
            txt_SearchBy.setVisible(true);
        } else if ("Course Code".equals(cmb_SearchBy.getValue())) {
            cmb_CourseCode.setVisible(true);
            txt_SearchBy.setVisible(false);
        } else {
            cmb_CourseCode.setVisible(false);
            txt_SearchBy.setVisible(true);
        }
    }
    
    
    private void clearField() {       
        cmb_SearchBy.setValue(null);
        txt_SearchBy.setText(null);
        cmb_CourseCode.setValue(null);
        cmb_CourseCode.setPromptText("Course Code");
        txt_SearchBy.setPromptText("Student ID");
        cmb_type.setValue(null);
        cmb_type.setPromptText("Type");
        txt_SearchBy.setVisible(true);
        cmb_CourseCode.setVisible(false);
    }
 
    private void SetTabaleByStudenID(String StudentID, String type) {
        ObservableList<Medical> sobslist = FXCollections.observableArrayList();
        MedicalDAO medicalDAO = new MedicalDAO();
        ArrayList<Medical> medicals = medicalDAO.viewStudentMedicalByStudentID(StudentID, type);
        
        for (Medical medical : medicals) {
            sobslist.add(medical);
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tbl_StartDate.setCellValueFactory(new PropertyValueFactory<>("medicalStartDate"));
        tbl_EndDate.setCellValueFactory(new PropertyValueFactory<>("medicalEndDate"));
        tbl_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_Status.setCellValueFactory(new PropertyValueFactory<>("medicalStatus"));
        
        table_medical.setItems(sobslist);
    }
    
    private void SetTabaleByCourseID(String CourseCode, String type) {
        ObservableList<Medical> sobslist = FXCollections.observableArrayList();
        MedicalDAO medicalDAO = new MedicalDAO();
        ArrayList<Medical> medicals = medicalDAO.viewStudentMedicalByCourseID(CourseCode, type);
        
        for (Medical medical : medicals) {
            sobslist.add(medical);
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tbl_StartDate.setCellValueFactory(new PropertyValueFactory<>("medicalStartDate"));
        tbl_EndDate.setCellValueFactory(new PropertyValueFactory<>("medicalEndDate"));
        tbl_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_Status.setCellValueFactory(new PropertyValueFactory<>("medicalStatus"));
        
        table_medical.setItems(sobslist);
    }
   
    
    //ComboBox database
    private ArrayList<String> courseid() {
        ArrayList<String> id = new ArrayList<String>();
        id = examDAO.getExamCourseId();
        return id;
    }
    ObservableList<String> courseId = FXCollections.observableArrayList(courseid());
    
}
