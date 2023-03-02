/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.UserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class LoginController implements Initializable {

    @FXML
    private Button ButtonLoginColse;
    @FXML
    private Button ButtonLogin;
    @FXML
    private TextField TxtFieldUserName;
    @FXML
    private PasswordField TxtFieldPassword;
    @FXML
    private Label lableUsername;
    @FXML
    private Label lablePassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ButtonLoginColseActionhandel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void ButtonLoginActionhandel(ActionEvent event) throws Exception {
        if (isFieldEmpty()) {

            UserDAO user = new UserDAO();

            String userid = user.verifyUserName(TxtFieldUserName.getText());

            //System.out.println(userid);
            if (userid != null && !userid.trim().isEmpty()) {
                changeField(TxtFieldUserName, "#0598ff;", lableUsername, "");
                if (user.verifyPassword(TxtFieldUserName.getText(), TxtFieldPassword.getText())) {
                    changeField(TxtFieldPassword, "#0598ff;", lablePassword, "");
                    String userType = user.getUserType(userid);

                    Stage primaryStage = new Stage();
                    FXMLLoader loder = null;
                    Parent root = null;

                    //seting fxml according to user type
                    switch (userType) {
                        case "student":
                            loder = new FXMLLoader(getClass().getResource("/com/sims/view/StudentDashboard.fxml"));
                            root = loder.load();
                            StudentDashboardController controler = loder.getController();
                            controler.setStudentid(userid);
                            break;
                        case "Lecturer":
                            loder = new FXMLLoader(getClass().getResource("/com/sims/view/LecturerDashboard.fxml"));
                            root = loder.load();
                            LecturerDashboardController lecdashcontrolee = loder.getController();
                            lecdashcontrolee.setUser(userid);
                            break;
                        case "Technical Officer":
                            loder = new FXMLLoader(getClass().getResource("/com/sims/view/TechnicalOfficerDashboard.fxml"));
                            root = loder.load();
//                            TechnicalOfficerDashboardController todashcontrolee = loder.getController();
//                            todashcontrolee.setStudentid(userid);
                            break;
                        case "Admin":
                            loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminDash.fxml"));
                            root = loder.load();
                            AdminDashboardController admindash = loder.getController();
                            admindash.setUserID(userid);
                            break;
                        default:
                            System.out.println("Somthing wrong");
                    }

                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    //close current window
                    Stage stage = (Stage) ButtonLogin.getScene().getWindow();
                    stage.close();

                } else {
                    TxtFieldPassword.setText("");
                    changeField(TxtFieldPassword, "#FF5733;", lablePassword, "Password is incorrect");
                    TxtFieldPassword.requestFocus();
                }
            } else {
                //System.out.println("Username is incorrect");
                TxtFieldUserName.setText("");
                changeField(TxtFieldUserName, "#FF5733;", lableUsername, "User name is incorrect");
                TxtFieldUserName.requestFocus();
            }
        }
    }

    private boolean isFieldEmpty() {
        if (TxtFieldUserName.getText().toString().equalsIgnoreCase("")) {
            changeField(TxtFieldUserName, "#FF5733", lableUsername, "Username is Requied");
            if (TxtFieldPassword.getText().toString().equalsIgnoreCase("")) {
                changeField(TxtFieldPassword, "#FF5733", lablePassword, "Password is Requied");
            } else {
                changeField(TxtFieldPassword, "#0598ff", lablePassword, "");
            }
            TxtFieldUserName.requestFocus();
            return false;
        } else {
            changeField(TxtFieldUserName, "#0598ff", lableUsername, "");
            if (TxtFieldPassword.getText().toString().equalsIgnoreCase("")) {

                changeField(TxtFieldPassword, "#FF5733", lablePassword, "Password is Requied");
                TxtFieldPassword.requestFocus();
                return false;
            } else {
                changeField(TxtFieldPassword, "#0598ff", lablePassword, "");
                return true;
            }
        }
    }

    private void changeField(TextField field, String colorCode, Label lable, String msg) {
        field.setStyle("-fx-border-color: " + colorCode + ";");
        lable.setText(msg);
    }

    @FXML
    private void TextFieldSetDefaultColor(InputMethodEvent event) {
        //if(TxtFieldUserName.isFocused()){
        TxtFieldUserName.setStyle("-fx-border-color: #0598ff;");
        //}
        //if(TxtFieldPassword.isFocused()){
        TxtFieldPassword.setStyle("-fx-border-color: #0598ff;");
        //}
    }

}
