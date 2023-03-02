/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.StaffDAO;
import com.sims.model.StudentDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import com.sims.model.User;
import com.sims.model.UserDAO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminUserprofileController implements Initializable {

    @FXML
    private Button btn_addProfile;
    @FXML
    private TableView<User> table_view;
    @FXML
    private TableColumn<User, String> table_cl_id;
    @FXML
    private TableColumn<User, String> table_cl_name;
    @FXML
    private TableColumn<User, String> table_cl_email;
    @FXML
    private TableColumn<User, String> table_cl_dpt;
    @FXML
    private TableColumn<User, String> table_cl_;

    UserDAO dao = new UserDAO();
    @FXML
    private Label lable_std_count;
    @FXML
    private Label lable_lec_count;
    @FXML
    private Label lable_to_count;
    @FXML
    private Label lable_admin_count;
    @FXML
    private ComboBox<String> combo_sortuser;

//    ObservableList<String> type = FXCollections.observableArrayList();
    @FXML
    private Button btn_updateProfile;
    @FXML
    private Button btn_delteProfile;

    User user_ = null;
    @FXML
    private TextField txt_field_search;
    @FXML
    private Button btn_field_search;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_sortuser.getItems().addAll("All","Admin", "Lecturer", "Technical Officer", "Student");
        
        
        table_cl_id.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        table_cl_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        table_cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table_cl_dpt.setCellValueFactory(new PropertyValueFactory<>("gender"));
        table_cl_.setCellValueFactory(new PropertyValueFactory<>("usernic"));

        setUserCount();

        setTable(null);

    }

    @FXML
    private void btn_addProfileActionHandel(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/com/sims/view/AdminUserProfileAdd.fxml"));
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminUserProfileAdd.fxml"));
        Parent root = loder.load();
        AdminUserProfileAddController apf = loder.getController();
        apf.setAddScene(this);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void btn_updateProfileActionhandler(ActionEvent event) throws IOException {
        if (user_ != null) {
            Stage primaryStage = new Stage();
            FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/sims/view/AdminUserProfileAdd.fxml"));
            Parent root = loder.load();
            AdminUserProfileAddController apf = loder.getController();
            apf.setUpdateScene(user_, this);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void setTable(String type) {
        ObservableList<User> obslist = FXCollections.observableArrayList();
        ArrayList<User> users = null;
        if (null == type) {
            users = dao.getAllUser();
        } else {
            users = dao.getAllUser(type);
        }

        for (User user : users) {
            obslist.add(user);
            //System.out.println(" " + user.getUserID() + " " + user.getAddress() + " " + user.getEmail() + " " + user.getFirstName());
        }

        table_view.setItems(obslist);
    }

    public void setUserCount() {
        lable_std_count.setText(Integer.toString(dao.getUserCount("Student")));
        lable_lec_count.setText(Integer.toString(dao.getUserCount("Lecturer")));
        lable_admin_count.setText(Integer.toString(dao.getUserCount("TO")));
        lable_admin_count.setText(Integer.toString(dao.getUserCount("Admin")));
    }

    @FXML
    private void tableonclickActioonhandler(MouseEvent event) {
        user_ = table_view.getSelectionModel().getSelectedItem();
//        System.out.println("in tableckick " + user_.getUserID() + " " + user_.getAddress() + " " + user_.getEmail() + " " + user_.getDob());
    }

    @FXML
    private void combo_sortuserActionhandler(ActionEvent event) {
        String type = combo_sortuser.getValue();

        switch (type) {
            case "Admin":
                setTable(type);
                break;
            case "Lecturer":
                setTable(type);
                break;
            case "Technical Officer":
                setTable("TO");
                break;
            case "Student":
                setTable(type);
                break;
            case "All":
                setTable(null);
                break;
            default:
                setTable(null);
        }
    }

    @FXML
    private void btn_delteProfileActionHandler(ActionEvent event) {
        if (user_ != null) {

            String type = dao.getUserType(user_.getUserID());

            if ("student".equals(type)) {
                StudentDAO sdao = new StudentDAO();
                if (sdao.deleteStudentbyUser(user_)) {
                    System.out.println(user_.getUserID() + " Succesfully deleted");
                    alertINFORMATION(user_.getUserID() + " Succesfully deleted");
                    setTable(null);
                    setUserCount();
                } else {
                    alertError(user_.getUserID() + " deleting Error");
                    System.out.println(user_.getUserID() + " delete Error");
                }
            } else {
                StaffDAO sdao = new StaffDAO();
                if (sdao.deleteStaffbyuser(user_)) {
                    System.out.println(user_.getUserID() + " Succesfully deleted");
                    alertINFORMATION(user_.getUserID() + " Succesfully deleted");
                    setTable(null);
                    setUserCount();
                } else {
                    alertError(user_.getUserID() + " deleting Error");
                    System.out.println(user_.getUserID() + " delete Error");
                }
            }
        }
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

    @FXML
    private void btn_field_searchActionhandel(ActionEvent event) {
        
        ObservableList<User> obslist = FXCollections.observableArrayList();
        ArrayList<User> users = dao.searchUserbyID(txt_field_search.getText());
       
        for (User user : users) {
            obslist.add(user);
            //System.out.println(" " + user.getUserID() + " " + user.getAddress() + " " + user.getEmail() + " " + user.getFirstName());
        }

        table_view.setItems(obslist);
        
    }

}
