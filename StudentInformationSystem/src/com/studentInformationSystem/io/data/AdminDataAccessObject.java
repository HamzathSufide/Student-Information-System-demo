package com.studentInformationSystem.io.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.studentInformationSystem.io.domain.Student;

public class AdminDataAccessObject extends RelationalDataAccessObject {
Scanner sc=new Scanner(System.in);
Student student=new Student();
	@Override
	protected String write(Object object) {
		try {
			Student student  = (Student) object;
        
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO student (studentName,course,address,email,phoneNumber,feesDate,fees) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getStudentName());
        statement.setString(2, student.getCourse());
        statement.setString(3, student.getAddress());
        statement.setString(4, student.getEmail());
        statement.setLong(5, student.getPhoneNumber());
        statement.setDate(6, student.getDate());
        statement.setDouble(7, student.getFees());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();

        
        System.out.println("Successfully inserted");
        return String.format("Student %d inserted successfully",student.getStudentId());
        
    }catch(InputMismatchException e ) {
   	 System.out.println("error :"+e.getMessage());
    }
		catch (SQLException e) {
    	System.out.println("error:"+ e.getMessage());
        System.out.println("Failed to insert Student");
        
    }
		return "Failed to insert Student";
}

	

	

	@Override
	protected Object read(ResultSet resultSet) {
		Student student = null;
	       
        try {
        	 
        
        	
        	
        	int studentId=	resultSet.getInt("studentId");
	           String studentName=  resultSet.getString("studentName");
	             String course=resultSet.getString("course");
	            String address= resultSet.getString("address");
	            String email= resultSet.getString("email");
	            long Phone= resultSet.getLong("PhoneNumber");
	            Date date= resultSet.getDate("feesDate");
	            double fees=resultSet.getDouble("fees");
student=new Student(studentId,studentName,course,address,email,Phone,date,fees);
	           
	        
        } catch(InputMismatchException e ) {
          	 System.out.println("error :"+e.getMessage());
        }
        catch (SQLException e) {
        	System.out.println("error:"+ e.getMessage());
        }
    System.out.println(student);
        return student;
	}

	

	@Override
	protected String modify(Object object) {
	    Student student = (Student) object;
	    try {
	        System.out.println("Enter student ID to update: ");
	        int studentId = sc.nextInt();
	        
	        PreparedStatement statement = connection.prepareStatement(
	                 "UPDATE student SET studentName=?, course=?, address=?, email=? ,phoneNumber=?, feesDate=?, fees=? WHERE studentId=?");
	        
	        statement.setString(1, student.getStudentName());
	        statement.setString(2, student.getCourse());
	        statement.setString(3, student.getAddress());
	        statement.setString(4, student.getEmail());
	        statement.setLong(5, student.getPhoneNumber());
	        statement.setDate(6, student.getDate());
	        statement.setDouble(7, student.getFees());
	        statement.setInt(8, studentId);
	        
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Student information updated successfully");
	            return "Student information updated successfully";
	        } else {
	            System.out.println("Student ID not found. Update failed.");
	            return "failed";
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Error: " + e.getMessage());
	        return "failed";
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        return "failed";
	    }
	}


	@Override
	protected Student findPrimaryKey() {
		try {
			
			statement = connection.prepareStatement("SELECT * FROM student WHERE studentId = ?");
			System.out.println("Enter the Student Id to search Student");
		        statement.setInt(1, sc.nextInt());
		        ResultSet resultSet = statement.executeQuery();
		        if(resultSet.next()) {
		        int studentId=	resultSet.getInt("studentId");
		           String studentName=  resultSet.getString("studentName");
		             String course=resultSet.getString("course");
		            String address= resultSet.getString("address");
		            String email= resultSet.getString("email");
		            long Phone= resultSet.getLong("PhoneNumber");
		            Date date= resultSet.getDate("feesDate");
		            double fees=resultSet.getDouble("fees");
		        
	student=new Student(studentId,studentName,course,address,email,Phone,date,fees);
		           
		        }
			
			}catch(InputMismatchException e ) {
			   	 System.out.println("error :"+e.getMessage());
		    }
			catch(SQLException e) {
				System.out.println("error:"+ e.getMessage());
			}
			System.out.println(student);
			return student;
		
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "student";
	}

	@Override
	protected String deleteThis() {
	    try {
	        System.out.println("Enter the student ID to delete: ");
	        int studentId = sc.nextInt();
	        
	        if (studentId <= 0) {
	            System.out.println("Invalid student ID. Deletion failed.");
	            return "failed";
	        } else {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + getTableName() + " WHERE studentId=?");
	            statement.setInt(1, studentId);
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println(rowsDeleted + " rows deleted successfully.");
	                return "deleted";
	            } else {
	                System.out.println("No rows deleted. Please check if the student ID is valid.");
	                return "failed";
	            }
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Error: " + e.getMessage());
	        return "failed";
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        return "failed";
	    } 
	    }
	






	@Override
	protected boolean modifyByStudent() {
		// TODO Auto-generated method stub
		return null != null;
	}


	

}
