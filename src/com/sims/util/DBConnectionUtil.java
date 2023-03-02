/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class DBConnectionUtil {
    private static Connection connection;
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lms_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    // This works according to singleton pattern
    private DBConnectionUtil() {
    }

    /**
     * Create Database connection for the given URL, Username and Password
     *
     * @return Connection this returns SQL connection for MySql Database
     *
     * @throws ClassNotFoundException - Thrown when an application tries to load
     * in a class through its string name
     * @throws SQLException - An exception that provides information on a
     * database access error or other errors
     */
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        /*
		 * This create new connection objects when connection is closed or it is
		 * null
         */
        if (connection == null || connection.isClosed()) {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
    
}
