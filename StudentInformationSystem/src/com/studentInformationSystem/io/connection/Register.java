package com.studentInformationSystem.io.connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.studentInformationSystem.io.consoleRead.StudentRead;
import com.studentInformationSystem.io.domain.Student;

public class Register {
    private final DataBaseConnection dbc = new DataBaseConnection();
    private final StudentRead studentRead = new StudentRead();

    public boolean registerStudent() {
    	
        try {
        Scanner scanner = new Scanner(System.in);
            Connection conn = dbc.getConnection();
            String username;
            boolean validUsername = false;
            do {
                System.out.print("Enter NEW username: ");
                username = scanner.next();
                try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
                    statement.setString(1, username);
                    ResultSet rs = statement.executeQuery();
                    validUsername = !rs.next(); // username not found in database
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (!validUsername) {
                    System.out.println("Username already exists. Please enter a different username.");
                }
            } while (!validUsername);

            System.out.print("Enter NEW password: ");
            String password = scanner.next();
            Student student = studentRead.studentRead();

            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO users (username,password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            int result = statement.executeUpdate();
            if (result > 0) {
                // If the user is successfully registered, get the userid and create a corresponding student record
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    PreparedStatement statement2 = conn.prepareStatement(
                        "INSERT INTO student (studentName,course,address,email,phoneNumber,feesDate,fees,userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                    statement2.setString(1, student.getStudentName());
                    statement2.setString(2, student.getCourse());
                    statement2.setString(3, student.getAddress());
                    statement2.setString(4, student.getEmail());
                    statement2.setLong(5, student.getPhoneNumber());
                    statement2.setDate(6, new Date(student.getDate().getTime()));
                    statement2.setDouble(7, student.getFees());
                    statement2.setInt(8, userId);
                    int result2 = statement2.executeUpdate();
                    if (result2 > 0) {
                        System.out.println("Student registered successfully.");
                        return true;
                    } else {
                        System.out.println("Sorry, there was a problem creating the student record.");
                        return false;
                    }
                } else {
                    System.out.println("Sorry, there was a problem retrieving the user ID.");
                    return false;
                }
            } else {
                System.out.println("Sorry, there was a problem registering the user.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("error:"+e.getMessage());
            return false;
        }
    }
}
