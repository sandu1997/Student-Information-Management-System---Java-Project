/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Exam;
import com.sims.model.ExamDAO;
import com.sims.model.ExamsMarks;
import com.sims.model.ExamsMarksDAO;
import com.sims.model.Marks;
import com.sims.model.MarksDAO;
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
public class LecturerStuMarksController implements Initializable {

    @FXML
    private ComboBox<String> cmb_CourseCode;
    @FXML
    private ComboBox<String> cmb_ExamType;
    @FXML
    private Button btn_Search;
    @FXML
    private TextField txt_StudentId;
    @FXML
    private Button btn_StuBack;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_CourseCode;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_ExamType;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_StudentId;
    @FXML
    private TableColumn<ExamsMarks, String> tbl_Marks;
    @FXML
    private TableView<ExamsMarks> table;
    
    @FXML
    private VBox vbox_StuMarks;
    
    VBox vBox;
    private FXMLLoader loder = null;
    
    ExamsMarksDAO examsMarksDAO = new ExamsMarksDAO();
    ExamDAO examDAO = new ExamDAO();
    MarksDAO marksDAO = new MarksDAO();
    Exam exam = new Exam();
    Marks marks = new Marks();
    
    
     //ComboBox database
    private ArrayList<String> courseid() {
        ArrayList<String> id = new ArrayList<String>();
        id = examDAO.getExamCourseId();
        return id;
    }
    ObservableList<String> courseId = FXCollections.observableArrayList(courseid());
    
    //ComboBox database
    private ArrayList<String> examType() {
        ArrayList<String> type = new ArrayList<String>();
        type = examDAO.getExamType();
        return type;
    }
    ObservableList<String> examType = FXCollections.observableArrayList(examType());
    
    //Table View Default
    ObservableList<ExamsMarks> obslist = FXCollections.observableArrayList();
    private void tableViewDefault() {
        ArrayList<ExamsMarks> marksData = examsMarksDAO.getMarks();
        
        for(ExamsMarks mark : marksData){
            obslist.add(mark);
            //System.out.println(" "+mark.getExamCourseId()+" "+mark.getType()+" "+mark.getMarksStuId()+" "+mark.getMarks());
        }
        
        tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("examCourseId"));
        tbl_ExamType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("marksStuId"));
        tbl_Marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        
        table.setItems(obslist);
    
    }
    
    private void clearField() {
        txt_StudentId.setText("");
        cmb_CourseCode.setValue("");
        cmb_ExamType.setValue("");
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_CourseCode.setItems(courseId);  //getItems().addAll(courseId)
        cmb_ExamType.setItems(examType);
        tableViewDefault();
        clearField();
    }    

    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
        exam.setExamCourseId(cmb_CourseCode.getValue().toUpperCase());
        exam.setType(cmb_ExamType.getValue().toUpperCase());
        marks.setMarksStuId(txt_StudentId.getText());
        
        if (txt_StudentId.getText().isEmpty() &&  !exam.getExamCourseId().isEmpty() && !exam.getType().isEmpty()) {

            ObservableList<ExamsMarks> obslist = FXCollections.observableArrayList();

            ArrayList<ExamsMarks> marksData = examsMarksDAO.getMarks(exam);

            for(ExamsMarks mark : marksData){
                obslist.add(mark);
                //System.out.println(" "+mark.getExamCourseId()+" "+mark.getType()+" "+mark.getMarksStuId()+" "+mark.getMarks());
            }

            tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("examCourseId"));
            tbl_ExamType.setCellValueFactory(new PropertyValueFactory<>("type"));
            tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("marksStuId"));
            tbl_Marks.setCellValueFactory(new PropertyValueFactory<>("marks"));

            table.setItems(obslist);
            clearField();

        } else if (!txt_StudentId.getText().isEmpty() &&  !exam.getExamCourseId().isEmpty() && !exam.getType().isEmpty()) {

            ObservableList<ExamsMarks> obslist = FXCollections.observableArrayList();

            ExamsMarks marksData = examsMarksDAO.getMark(marks, exam);

            obslist.add(marksData);
            //System.out.println(" "+marksData.getExamCourseId()+" "+marksData.getType()+" "+marksData.getMarksStuId()+" "+marksData.getMarks());


            tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("examCourseId"));
            tbl_ExamType.setCellValueFactory(new PropertyValueFactory<>("type"));
            tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("marksStuId"));
            tbl_Marks.setCellValueFactory(new PropertyValueFactory<>("marks"));

            table.setItems(obslist);
            clearField();

        } else if (!txt_StudentId.getText().isEmpty() &&  exam.getExamCourseId().isEmpty() && exam.getType().isEmpty()){
            ObservableList<ExamsMarks> obslist = FXCollections.observableArrayList();

            ArrayList<ExamsMarks> marksData = examsMarksDAO.getMarksForAStudent(marks);

            for(ExamsMarks mark : marksData){
                obslist.add(mark);
                //System.out.println(" "+mark.getExamCourseId()+" "+mark.getType()+" "+mark.getMarksStuId()+" "+mark.getMarks());
            }

            tbl_CourseCode.setCellValueFactory(new PropertyValueFactory<>("examCourseId"));
            tbl_ExamType.setCellValueFactory(new PropertyValueFactory<>("type"));
            tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("marksStuId"));
            tbl_Marks.setCellValueFactory(new PropertyValueFactory<>("marks"));

            table.setItems(obslist);
            clearField();
            
        } else {
                //JOptionPane.showMessageDialog(this, "Please check the student ID", "Warning", JOptionPane.ERROR_MESSAGE);
                System.out.println("Not match");
        }
 
    }

    @FXML
    private void btn_StuBackActionPerformed(ActionEvent event) throws IOException {
        loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerStudent.fxml"));
        vBox = loder.load();
        vbox_StuMarks.getChildren().setAll(vBox);
    }
    
}
