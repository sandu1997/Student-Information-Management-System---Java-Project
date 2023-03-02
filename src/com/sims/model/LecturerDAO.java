/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class LecturerDAO extends StaffDAO{
    
    public boolean updateLeturer(Staff staff) {
        boolean value = false;

        try {
            
            updateUser(staff);

            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("UPDATE staf_qulification SET qulification = ? WHERE stfid = ?;");
            pst.setString(1, staff.getQualification());
            pst.setString(2, staff.getUserID());
            

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }
    
    public Staff getLecturer(String userid) {
        Staff staff = new Staff();
        User user = getUser(userid);

        staff.setUserID(user.getUserID());
        staff.setFirstName(user.getFirstName());
        staff.setLastname(user.getLastname());
        staff.setUsernic(user.getUsernic());
        staff.setPhone(user.getPhone());
        staff.setEmail(user.getEmail());
        staff.setAddress(user.getAddress());
        staff.setDob(user.getDob());
        staff.setGender(user.getGender());
        
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("SELECT * FROM staf_qulification where stfid = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, userid);

            rs = pst.executeQuery();

            if (rs.first()) {
                staff.setQualification(rs.getString(2));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staff;
    }
    
}
