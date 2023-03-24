package com.studentInformationSystem.io.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DataBaseConnection implements DataBaseInformation {

    private Scanner sc = new Scanner(System.in);
    private String schema = "student_information";
    private String host = "localhost";
    private String port = "3306";
    private String username="root";
    private String password="root";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;

    public DataBaseConnection() {
        // private constructor to prevent instantiation from outside
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        
            try {
                Class.forName(DRIVER_NAME);
                conn = DriverManager.getConnection(getUrl());
            } catch (Exception ex) {
                System.out.println("error: " + ex.getMessage());
            
        }
        return conn;
    }

    public String getPassword() {
        System.out.println("Enter the password:");
        return sc.next();
    }

    public String getUsername() {
        System.out.println("Enter the username:");
        return sc.next();
    }

    public String getUrl() {
        return new StringBuilder().append("jdbc:mysql://").append(host)
                .append(":").append(port).append("/").append(schema)
                .append("?user=").append(username).append("&password=")
                .append(password).toString();
    }
}
