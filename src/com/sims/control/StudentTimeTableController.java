/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.control;

import com.sims.model.Student;
import com.sims.model.TimeTableDAO;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class StudentTimeTableController implements Initializable {

    private Student student;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox timetable_vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
    
    void showtimetable(int year,int sem){
        
        TimeTableDAO tdao = new TimeTableDAO();

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
                imageView.setImage(image_);

            } else {
                imageView.setImage(null);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminTimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        showtimetable(student.getYear(),student.getSemester());
    }

}
