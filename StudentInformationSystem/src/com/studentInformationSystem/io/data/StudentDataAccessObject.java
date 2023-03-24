package com.studentInformationSystem.io.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


import com.studentInformationSystem.io.domain.Student;




public class StudentDataAccessObject extends RelationalDataAccessObject{
Student student=new Student();
	Scanner sc=new Scanner(System.in);
	public StudentDataAccessObject() {
		
	}

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
	return null;	
	}
	

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "student";
	}
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
	protected  String deleteThis() {
		 return null;
	}
	protected boolean modifyByStudent() {
		
		 
	     try {
	         PreparedStatement statement = connection.prepareStatement(
	                 "UPDATE student SET studentName=?, course=?, address=?, email=? ,phoneNumber=? ");
	        /* System.out.println("enter Student Id For Updation=");
	         statement.setInt(1,sc.nextInt());
	         System.out.println();*/
	         System.out.println("Enter the Student name");
	         statement.setString(1, sc.next());
	         System.out.println("Enter the Course name");
	         statement.setString(2, sc.next());
	         System.out.println("Enter the Address");
	         statement.setString(3, sc.next());
	         System.out.println("Enter the EmailAddress");
	         statement.setString(4, sc.next());
	         System.out.println("Enter the Phone number");
	         statement.setLong(5, sc.nextLong());
	         
	      
	         
	        int result= statement.executeUpdate();
	        if (result > 0) {
	             System.out.println("Student details updated successfully.");
	             return true;
	             
	        }else {
	                 System.out.println("Sorry, there was a problem updating your details.");
	                 return false;
	               }
	             } catch (SQLException e) {
	               System.out.println("error :"+ e.getMessage());
	               return false;
	             }
	     
	}
}
