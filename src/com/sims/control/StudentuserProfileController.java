/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.StaffDAO;
import com.sims.model.Student;
import com.sims.model.UserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class StudentuserProfileController implements Initializable {

    private Student student;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_nic;
    @FXML
    private TextField txt_dob;
    @FXML
    private TextArea txt_address;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_phone;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btn_update;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void setfields() {
        txt_lname.setText(student.getFirstName());
        txt_fname.setText(student.getLastname());
        txt_nic.setText(student.getUsernic());
        txt_dob.setText(student.getDob());
        txt_address.setText(student.getAddress());
        txt_email.setText(student.getEmail());
        txt_phone.setText(Integer.toString(student.getPhone()));
        txt_id.setText(student.getUserID());
    }

    public void setStudent(Student student) {
        this.student = student;
        setfields();
    }

    @FXML
    private void btn_updateActionHandel(ActionEvent event) {
        UserDAO dao = new StaffDAO();

        student.setAddress(txt_address.getText());
        student.setEmail(txt_email.getText());
        student.setPhone(Integer.parseInt(txt_phone.getText()));

        if (dao.updateUser(student)) {
            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
            a1.setTitle("Done");
            a1.setContentText("Succesfully Updated");
            a1.setHeaderText(null);
            a1.showAndWait();
            
            setfields();
        } else {
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Error");
            a1.setContentText("Updating Error");
            a1.setHeaderText(null);
            a1.showAndWait();
        }
    }


}
