/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.control.AdminTimeTableController;
import com.sims.util.DBConnectionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author acer
 */
public class TimeTableDAO {

    private PreparedStatement pst;
    private ResultSet rs;

    public boolean setTimeTable(int level, int sem, FileInputStream fis, int len) {
        boolean val = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("insert into time_table VALUES(?,?,?);");
            pst.setInt(1, level);
            pst.setInt(2, sem);
            pst.setBinaryStream(3, (InputStream) fis, len);

            if (pst.executeUpdate() >= 1) {
                val = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return val;
    }
    
    public boolean updateTimeTable(int level, int sem, FileInputStream fis, int len) {
        boolean val = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("update time_table set timetable=?  where level=? and sememster=?;");
            pst.setInt(2, level);
            pst.setInt(3, sem);
            pst.setBinaryStream(1, (InputStream) fis, len);

            if (pst.executeUpdate() >= 1) {
                val = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return val;
    }
    
    public boolean delteTimeTable(int level, int sem) {
        boolean val = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from time_table where level=? and sememster=?;");
            pst.setInt(1, level);
            pst.setInt(2, sem);

            if (pst.executeUpdate() >= 1) {
                val = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return val;
    }

    public InputStream getTimeTable(int level, int sem) {
        InputStream is = null;
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select timetable from time_table where level=? and sememster=?;");

            pst.setInt(1, level);
            pst.setInt(2, sem);

            rs = pst.executeQuery();

            if (rs.next()) {
                is = rs.getBinaryStream(1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimeTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TimeTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return is;

    }

}
