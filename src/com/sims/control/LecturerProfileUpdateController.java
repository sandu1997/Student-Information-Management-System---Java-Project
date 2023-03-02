/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.LecturerDAO;
import com.sims.model.Staff;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LecturerProfileUpdateController implements Initializable {

    @FXML
    private ToggleGroup Gender;
    @FXML
    private TextField txt_Fname;
    @FXML
    private TextField txt_Lname;
    @FXML
    private RadioButton rad_Male;
    @FXML
    private RadioButton rad_Female;
    @FXML
    private TextField txt_NIC;
    @FXML
    private TextField txt_Contact;
    @FXML
    private TextField txt_Email;
    @FXML
    private TextArea txa_Address;
    @FXML
    private Button btn_Edit;
    @FXML
    private Button btn_Update;
    @FXML
    private Button btn_Cancel;
    @FXML
    private TextArea txa_Qualifications;
    @FXML
    private DatePicker datePicker_DOB;
    
    Staff staff = new Staff();
    LecturerDAO lecturerDAO = new LecturerDAO();
    String lecturerid = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFieldsDisable();
    }    

    @FXML
    private void btn_EditActionPerformed(ActionEvent event) {
        txt_Fname.setDisable(false);
        txt_Lname.setDisable(false);
        datePicker_DOB.setDisable(false);
        rad_Male.setDisable(false);
        rad_Female.setDisable(false);
        txt_NIC.setDisable(false);
        txt_Contact.setDisable(false);
        txt_Email.setDisable(false);
        txa_Address.setDisable(false);
        txa_Qualifications.setDisable(false);
        
        btn_Edit.setVisible(false);
        btn_Update.setVisible(true);
        btn_Cancel.setVisible(true);
    }

    @FXML
    private void btn_UpdateActionPerformed(ActionEvent event) {
        staff.setUserID(lecturerid);
        staff.setFirstName(txt_Fname.getText());
        staff.setLastname(txt_Lname.getText());
        staff.setDob(datePicker_DOB.getValue().toString());
        staff.setGender(rad_Male.isSelected() ? "M" : "F");
        staff.setUsernic(txt_NIC.getText());
        staff.setPhone(Integer.parseInt(txt_Contact.getText()));
        staff.setEmail(txt_Email.getText());
        staff.setAddress(txa_Address.getText());
        staff.setQualification(txa_Qualifications.getText());
        
        
        if (isFieldsEmpty()) {
            //JOptionPane.showMessageDialog(this, "Some fields are missing", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            if (lecturerDAO.updateLeturer(staff)) {
                //JOptionPane.showMessageDialog(this, "successfully updated");
                setfieldsView();
                setFieldsDisable();
            } else {
                //JOptionPane.showMessageDialog(this, "Error in Updating data", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void btn_CancelActionPerformed(ActionEvent event) {
        setfieldsView();
        setFieldsDisable();
    }
    
    private void setfieldsView() {
       staff = lecturerDAO.getLecturer(lecturerid);
        
        txt_Fname.setText(staff.getFirstName());
        txt_Lname.setText(staff.getLastname());
        datePicker_DOB.setValue(LocalDate.parse(staff.getDob()));
        if ("M".equals(staff.getGender())) {
            rad_Male.setSelected(true);
        } else if ("F".equals(staff.getGender())) {
            rad_Female.setSelected(true);
        }
        txt_NIC.setText(staff.getUsernic());
        txt_Contact.setText(String.valueOf(staff.getPhone()));
        txt_Email.setText(staff.getEmail());
        txa_Address.setText(staff.getAddress());
        txa_Qualifications.setText(staff.getQualification());
    }
    
    private boolean isFieldsEmpty() {
        return txt_Fname.getText().isEmpty() || txt_Lname.getText().isEmpty() || datePicker_DOB.getValue() == null || 
                Gender.getSelectedToggle() == null || txt_NIC.getText().isEmpty() || txt_Contact.getText().isEmpty() || 
                txt_Email.getText().isEmpty() || txa_Address.getText().isEmpty() || txa_Qualifications.getText().isEmpty();
    }
    
    private void setFieldsDisable() {
        txt_Fname.setDisable(true);
        txt_Lname.setDisable(true);
        datePicker_DOB.setDisable(true);
        rad_Male.setDisable(true);
        rad_Female.setDisable(true);
        txt_NIC.setDisable(true);
        txt_Contact.setDisable(true);
        txt_Email.setDisable(true);
        txa_Address.setDisable(true);
        txa_Qualifications.setDisable(true);
        
        btn_Edit.setVisible(true);
        btn_Update.setVisible(false);
        btn_Cancel.setVisible(false);
    }
    
    public void setUser(String userid){
        this.lecturerid = userid;
        setfieldsView();
    }

    
}
