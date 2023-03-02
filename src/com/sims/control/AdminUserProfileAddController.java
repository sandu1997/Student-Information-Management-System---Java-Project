/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Student;
import com.sims.model.StudentDAO;
import com.sims.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminUserProfileAddController implements Initializable {

    @FXML
    private ComboBox combox_department;
    @FXML
    private ComboBox combox_job_staf;
    @FXML
    private ComboBox combox_department_staf;

    ObservableList<String> dpt = FXCollections.observableArrayList("ICT", "ET", "BST");

    ObservableList<String> job = FXCollections.observableArrayList("Admin", "Lecturer", "Technical Officer");
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_nic;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_contact;
    @FXML
    private TextField txt_batch;
    @FXML
    private DatePicker date_dob;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_address;
    @FXML
    private Button btn_add;

    private User user_ = null;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton radio_btn_male;
    @FXML
    private RadioButton radio_btn_female;
    
    AdminUserprofileController parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combox_department.setItems(dpt);
        combox_department_staf.setItems(dpt);
        combox_job_staf.setItems(job);

    }

    @FXML
    private void btn_addActionHandler(ActionEvent event) {

        Student st1 = new Student();

        st1.setUserID(txt_id.getText());
        st1.setFirstName(txt_fname.getText());
        st1.setLastname(txt_lname.getText());
        st1.setUsernic(txt_nic.getText());
        st1.setAddress(txt_address.getText());
        st1.setDepartment("dpt01");
        System.out.println(date_dob.getValue().toString());
        st1.setDob(date_dob.getValue().toString());
        st1.setEmail(txt_email.getText());
        st1.setYear(1);
        st1.setSemester(1);
        st1.setPhone(763234443);

        if (radio_btn_male.isSelected()) {
            st1.setGender("M");
        }
        if (radio_btn_female.isSelected()) {
            st1.setGender("F");
        }
        
        StudentDAO dao = new StudentDAO();
        if (user_ == null) {
            if (dao.saveStudent(st1)) {
                System.out.println("Add");
                alertINFORMATION("Successfully Added");
            } else {
                alertError("Adding Error");
                System.out.println("Add Error");
            }
        } else {
            if (dao.updateStudent(st1)) {
                System.out.println("Updated");
                alertINFORMATION("Successfully Updated");
            } else {
                alertError("Updating Error");
                System.out.println("Updated Error");
            }
        }
        
        parent.setTable(null);
        parent.setUserCount();
        
        Stage stage = (Stage) btn_add.getScene().getWindow();
        stage.close();
    }
    
    public void setAddScene(AdminUserprofileController parent) {
        this.parent = parent;
    }

    public void setUpdateScene(User user,AdminUserprofileController parent) {
        this.parent = parent;
        this.user_ = user;

        txt_id.setText(user.getUserID());
        txt_email.setText(user.getEmail());
        txt_nic.setText(user.getUsernic());
        txt_fname.setText(user.getFirstName());
        txt_contact.setText(Integer.toString(user.getPhone()));
        txt_batch.setText("Batch 03");
        date_dob.setValue(get_DATE("1992-01-26"));
        txt_lname.setText(user.getLastname());
        txt_address.setText(user.getAddress());

        String gender = user.getGender();

        if ("M".equals(gender) || "M".equals(gender)) {
            radio_btn_male.setSelected(true);
        } else {
            radio_btn_female.setSelected(true);
        }

        btn_add.setText("Update User");
        btn_add.setStyle("-fx-background-color: #EDD817;");
    }

    public static final LocalDate get_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
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
