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
 * @author acer
 */
public class StaffDAO extends UserDAO {

    public boolean saveStaff(Staff staff) {
        boolean value = false;

        if (saveUser(staff)) {
            try {
                Connection con = DBConnectionUtil.getDBConnection();

                pst = con.prepareStatement("insert into staf values(?,?,?,?);");
                pst.setString(1, staff.getUserID());
                pst.setString(2, staff.getTitle());
                pst.setFloat(3, staff.getSalary());
                pst.setString(4, staff.getJobtype());

                if (pst.executeUpdate() >= 1) {
                    value = true;
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return value;
    }

    public boolean updateStaff(Staff stafff) {
        boolean value = false;

        try {
            updateUser(stafff);

            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("update staf set st_title=?,st_salary=?,st_job_type=? where stfid=?;");
            pst.setString(4, stafff.getUserID());
            pst.setString(1, stafff.getTitle());
            pst.setFloat(2, stafff.getSalary());
            pst.setString(3, stafff.getJobtype());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean deleteStaff(Staff user) {
        boolean value = false;
        try {
            deleteUser(user);
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from staf where stfid=?;");
            pst.setString(1, user.getUserID());

            if (pst.executeUpdate() >= 1) {

                deleteUser(user);
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }
    
    public boolean deleteStaffbyuser(User user) {
        boolean value = false;
        try {
            deleteUser(user);
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from staf where stfid=?;");
            pst.setString(1, user.getUserID());

            if (pst.executeUpdate() >= 1) {

                deleteUser(user);
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public Staff getStaff(String userid) {
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

            pst = con.prepareStatement("select * from staf where stfid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, userid);

            rs = pst.executeQuery();

            if (rs.first()) {
                staff.setTitle(rs.getString(2));
                staff.setSalary(rs.getFloat(3));
                staff.setJobtype(rs.getString(4));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staff;
    }

}
