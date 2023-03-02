/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import com.sims.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class NoticeDOA {

    PreparedStatement pst;
    ResultSet rs;

    public boolean saveNotice(Notice notice) {
        boolean value = false;

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("insert into notice values(?,?,?,?);");
            pst.setString(1, notice.getID());
            pst.setString(2, notice.getContent());
            pst.setString(3, notice.getTitle());
            pst.setString(4, notice.getPublisher());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean updateNotice(Notice notice) {
        boolean value = false;

        try {

            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("update notice set noticeTitle=?,noticeContent=?, noticeAdminid=? where noticeid=?;");
            pst.setString(4, notice.getID());
            pst.setString(1, notice.getTitle());
            pst.setString(2, notice.getContent());
            pst.setString(3, notice.getPublisher());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public boolean deleteNotice(Notice notice) {
        boolean value = false;
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("delete from notice where noticeid=?;");
            pst.setString(1, notice.getID());

            if (pst.executeUpdate() >= 1) {
                value = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public Notice getNotice(String noticeid) {
        Notice notice = new Notice();

        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from notice where noticeid=?;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, noticeid);

            rs = pst.executeQuery();

            if (rs.first()) {
                notice.setID(rs.getString(1));
                notice.setContent(rs.getString(2));
                notice.setTitle(rs.getString(3));
                notice.setPublisher(rs.getString(4));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return notice;
    }

    public ArrayList<Notice> getAllNotice() {
        ArrayList<Notice> userlist = new ArrayList<Notice>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from notice;", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = pst.executeQuery();

            while (rs.next()) {
                Notice user = new Notice();

                user.setID(rs.getString(1));
                user.setContent(rs.getString(2));
                user.setTitle(rs.getString(3));
                user.setPublisher(rs.getString(4));

                userlist.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userlist;
    }

    public ArrayList<Notice> searchNotice(String title) {
        ArrayList<Notice> userlist = new ArrayList<Notice>();
        try {
            Connection con = DBConnectionUtil.getDBConnection();

            pst = con.prepareStatement("select * from notice where noticeid like '%" + title + "%';", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = pst.executeQuery();

            while (rs.next()) {
                Notice user = new Notice();

                user.setID(rs.getString(1));
                user.setContent(rs.getString(2));
                user.setTitle(rs.getString(3));
                user.setPublisher(rs.getString(4));

                userlist.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userlist;
    }

}
