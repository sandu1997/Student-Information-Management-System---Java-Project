/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.TimeTableDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author pragith
 */
public class TechnicalOfficerTimeTableController implements Initializable {

    @FXML
    private ImageView Imageview_select;
    @FXML
    private Button btn_select;
    @FXML
    private ComboBox<Integer> combo_select_sem;
    @FXML
    private ComboBox<Integer> combo_select_level;

    TimeTableDAO tdao = new TimeTableDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_select_sem.getItems().addAll(1, 2);
        combo_select_level.getItems().addAll(1, 2,3, 4);
    }    

    private void gettimetable(int year,int sem){
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
    private void btn_selectActionhandler(ActionEvent event) {
        gettimetable(combo_select_level.getValue(),combo_select_sem.getValue());
    }
    
}
