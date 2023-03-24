package com.studentInformationSystem.io.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.studentInformationSystem.io.connection.DataBaseConnection;
import com.studentInformationSystem.io.data.DataAccessObject;
import com.studentInformationSystem.io.domain.Student;

public class AdminServices {
	private static final Connection PreparedStatement = null;
	private DataAccessObject dao;
	DataBaseConnection dbc=new DataBaseConnection();
	Scanner sc=new Scanner(System.in);
	public AdminServices(DataAccessObject dao) {
		super();
		this.dao=dao;
	}
	public void addStudents(Student student){
		dao.insert(student);

}
	public ArrayList<Student> listAllStudents() {
	    ArrayList<Student> students = new ArrayList<>();
	    ArrayList<Object> records = dao.listAll();
	    
	    for (Object record : records) {
	        
	    	  Student student = (Student) record;
	    	  students.add(student);
	    }
	    return students;
	}
	public void updateStudents(Student student) {
		dao.update(student);
	}
	public Student findStudentByStudentId() {
		dao.findByPrimaryKey();
		return null;
	}
	public void deleteStudent() {
		dao.delete();
	}


    double DAILY_FINE = 0.50;
    double INITIAL_FINE = 50.00;
   double ADDITIONAL_FEE = 10.00;
	   public void calculateTotalFine() {
		    try {
		        Connection connection = dbc.getConnection();
		        PreparedStatement statement = connection.prepareStatement("SELECT studentId, studentName, feesDate,fees FROM student WHERE studentId = ?");
		        System.out.println("Enter the Student Id for find the total");
		        int studentId = sc.nextInt();
		        statement.setInt(1, studentId);
		        ResultSet resultSet = statement.executeQuery();

		        if (resultSet.next()) {
		            String studentName = resultSet.getString("studentName");
		            Date dueDate = resultSet.getDate("feesDate");
		            float fees=resultSet.getFloat("fees");

		            // Get the current date
		            long currentTime = System.currentTimeMillis();
		            Date currentDate = new Date(currentTime);

		            // Calculate the difference in days between the current date and the due date
		            long diffMillis = currentDate.getTime() - dueDate.getTime();
		            int diffDays = (int) (diffMillis / (24 * 60 * 60 * 1000));
if(currentDate.getTime()>=dueDate.getTime()) {
	System.out.println("No Fine For This student");
}
else {   // Calculate the fine based on the difference in days
		            double fine = diffDays * DAILY_FINE;

		            // Add the initial fine and additional fee
		            double Fine = fine + INITIAL_FINE + ADDITIONAL_FEE;
		            double totalFine=fine + INITIAL_FINE + ADDITIONAL_FEE+fees;

		            // Print table header
		            System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "StudentID", "StudentName", "Fine","fees","totalFeesWithFine");

		            // Print table row
		            System.out.printf("%-20d%-20s%-20.2f%-20.2f%-20.2f%n", studentId, studentName,Fine,fees,totalFine);
}
		        } else {
		            System.out.println("No student found with ID " + studentId);
		        }
		    } catch (SQLException e) {
		        System.out.println("error :"+e.getMessage());
		    }
		}
	   public boolean adminlogin() {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    boolean loginSuccess = false;
		    while (!loginSuccess) {
		        System.out.print("Enter username: ");
		        String username = sc.next();
		        System.out.print("Enter password: ");
		        String password = sc.next();

		        try {
		            conn = dbc.getConnection();
		            String query = "SELECT COUNT(*) FROM admin WHERE username = ? AND password = ?";
		            stmt = conn.prepareStatement(query);
		            stmt.setString(1, username);
		            stmt.setString(2, password);
		            rs = stmt.executeQuery();
		            if (rs.next()) {
		                int count = rs.getInt(1);
		                if (count == 1) {
		                    // login successful
		                    loginSuccess = true;
		                } else {
		                    System.out.println("Incorrect username or password. Please try again.");
		                }
		            }
		        } catch (SQLException e) {
		            System.out.println("Error: " + e.getMessage());
		        } finally {
		            try {
		                if (rs != null) {
		                    rs.close();
		                }
		                if (stmt != null) {
		                    stmt.close();
		                }
		                
		            } catch (SQLException e) {
		                System.out.println("Error: " + e.getMessage());
		            }
		        }
		    }
		    return true;
		}
}
