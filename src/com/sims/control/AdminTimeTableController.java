/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.TimeTableDAO;
import com.sims.model.UserDAO;
import com.sims.util.DBConnectionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminTimeTableController implements Initializable {

    @FXML
    private Button btn_choose;
    @FXML
    private Button btn_add;
    @FXML
    private ImageView Imageview;

    private File file = null;
    private Image image = null;
    private FileInputStream fis = null;

    TimeTableDAO tdao = new TimeTableDAO();
    @FXML
    private ImageView Imageview_select;
    @FXML
    private Button btn_select;
    @FXML
    private ComboBox<Integer> combo_select_sem;
    @FXML
    private ComboBox<Integer> combo_select_level;
    @FXML
    private Button btn_update;
    @FXML
    private ComboBox<Integer> combo_manage_sem;
    @FXML
    private ComboBox<Integer> comobo_manage_level;
    @FXML
    private Button btn_delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo_select_sem.getItems().addAll(1, 2);
        combo_manage_sem.getItems().addAll(1, 2);
        combo_select_level.getItems().addAll(1, 2, 3, 4);
        comobo_manage_level.getItems().addAll(1, 2, 3, 4);

    }

    private void gettimetable(int year, int sem) {
        String imagename = Integer.toString(year) + Integer.toString(year);
        try {
            InputStream is = tdao.getTimeTable(year, sem);

            if (is != null) {

                OutputStream os = new FileOutputStream(new File("photo" + imagename + ".png"));

                byte[] content = new byte[5120];

                int size = 0;

                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }

                os.close();
                is.close();

                Image image_ = new Image("file:photo" + imagename + ".png");
                Imageview_select.setImage(image_);

            } else {
                Imageview_select.setImage(null);
            }

        } catch (IOException ex) {
            Logger.getLogger(AdminTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_chooseActionhandler(ActionEvent event) {
        FileChooser flc = new FileChooser();
        flc.setTitle("Choose Time Table");
        file = flc.showOpenDialog(null);

        image = new Image(file.toURI().toString());

        Imageview.setImage(image);
    }

    @FXML
    private void btn_addActionhandler(ActionEvent event) throws FileNotFoundException, IOException {

        fis = new FileInputStream(file);

        if (tdao.setTimeTable(comobo_manage_level.getValue(), combo_manage_sem.getValue(), fis, (int) file.length())) {
            alertINFORMATION("image succesfull Added");
            System.out.println("File Done");
        } else {
            alertError("image Adding Error");
            System.out.println("File error");
        }
        fis.close();
        file = null;
        gettimetable(comobo_manage_level.getValue(), combo_manage_sem.getValue());

    }

    @FXML
    private void btn_updateActionhandler(ActionEvent event) throws FileNotFoundException {
        fis = new FileInputStream(file);

        if (tdao.updateTimeTable(comobo_manage_level.getValue(), combo_manage_sem.getValue(), fis, (int) file.length())) {
            String imagename = Integer.toString(comobo_manage_level.getValue()) + Integer.toString(combo_manage_sem.getValue());
            File dfile = new File("photo" + imagename + ".png");

            if (dfile.delete()) {
                System.out.println("File Update Done");
                alertINFORMATION("image succesfull Update");
            }
        } else {
            alertError("image updating Error");
            System.out.println("File update error");
        }

        image = new Image(file.toURI().toString());

        Imageview_select.setImage(image);
    }

    @FXML
    private void btn_selectActionhandler(ActionEvent event) {
        gettimetable(combo_select_level.getValue(), combo_select_sem.getValue());
    }

    @FXML
    private void btn_deleteActionhandler(ActionEvent event) {
        if (tdao.delteTimeTable(comobo_manage_level.getValue(), combo_manage_sem.getValue())) {
            String imagename = Integer.toString(comobo_manage_level.getValue()) + Integer.toString(combo_manage_sem.getValue());
            File dfile = new File("photo" + imagename + ".png");

            if (dfile.delete()) {
                System.out.println(" Succesfully deleted");
                alertINFORMATION("image succesfull deleted");
            } else {
                alertError("File deleting Error");
                System.out.println(" delete Error");
            }
            Imageview.setImage(null);
            Imageview_select.setImage(null);
        } else {
            alertError("image deleting Error");
            System.out.println(" delete Error");
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

}
