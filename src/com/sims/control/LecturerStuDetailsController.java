/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

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
public class LecturerStuDetailsController implements Initializable {

    @FXML
    private ComboBox<String> cmb_SearchBy;
    @FXML
    private TextField txt_SearchBy;
    @FXML
    private Button btn_Search;
    @FXML
    private TableColumn<Student, String> tbl_StudentId;
    @FXML
    private TableColumn<Student, String> tbl_Fname;
    @FXML
    private TableColumn<Student, String> tbl_Lname;
    @FXML
    private TableColumn<Student, String> tbl_Dpt;
    @FXML
    private TableColumn<Student, String> tbl_Contact;
    @FXML
    private TableColumn<Student, String> tbl_Email;
    @FXML
    private TableColumn<Student, String> tbl_Address;
    @FXML
    private Button btn_StuBack;
    @FXML
    private TableView<Student> tableStuDetails;
    @FXML
    private ComboBox<String> cmb_Level;
    @FXML
    private VBox vbox_StuDetails;
    
    
    VBox vBox;
    private FXMLLoader loder = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_SearchBy.getItems().addAll("Student ID", "Level");
        cmb_Level.getItems().addAll("1", "2", "3", "4");
        txt_SearchBy.setVisible(true);
        cmb_Level.setVisible(false);
        cmb_Level.setPromptText("Level");
    }   

    @FXML
    private void btn_SearchActionPerformed(ActionEvent event) {
        if (cmb_SearchBy.getValue().isEmpty() && txt_SearchBy.getText().isEmpty() || cmb_Level.getValue().isEmpty()) {
        
        } else {
            if (null != cmb_SearchBy.getValue()) switch (cmb_SearchBy.getValue()) {
                case "Student ID":
                    SetTabaleByStudenID(txt_SearchBy.getText());
                    clearField();
                    break;
                case "Level":
                    SetTabaleByLevel(Integer.parseInt(cmb_Level.getValue()));
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
        vbox_StuDetails.getChildren().setAll(vBox);
    }
    
    @FXML
    private void cmb_SearchByActionPerformed(ActionEvent event) {
        if ("Student ID".equals(cmb_SearchBy.getValue())) {
            cmb_Level.setVisible(false);
            txt_SearchBy.setVisible(true);
        } else if ("Level".equals(cmb_SearchBy.getValue())) {
            cmb_Level.setVisible(true);
            txt_SearchBy.setVisible(false);
        } else {
            cmb_Level.setVisible(false);
            txt_SearchBy.setVisible(true);
        }
    }
    
    private void clearField() {
        cmb_SearchBy.setValue(null);
        txt_SearchBy.setText(null);
        cmb_Level.setValue(null);
        cmb_Level.setPromptText("Level");
        txt_SearchBy.setVisible(true);
        cmb_Level.setVisible(false);
    }
    
    private void SetTabaleByStudenID(String StudentID) {
        ObservableList<Student> sobslist = FXCollections.observableArrayList();
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudent(StudentID);

        sobslist.add(student);
       
        
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        tbl_Fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tbl_Lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tbl_Dpt.setCellValueFactory(new PropertyValueFactory<>("department"));
        tbl_Contact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tbl_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbl_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        
        tableStuDetails.setItems(sobslist);
    }
    
    private void SetTabaleByLevel(int level) {
        ObservableList<Student> sobslist = FXCollections.observableArrayList();
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> students = studentDAO.getAllStudent(level);
        
        for (Student student : students) {
            sobslist.add(student);
        }
        
        tbl_StudentId.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        tbl_Fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tbl_Lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tbl_Dpt.setCellValueFactory(new PropertyValueFactory<>("department"));
        tbl_Contact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tbl_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbl_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        
        tableStuDetails.setItems(sobslist);
    }

    
    
}
