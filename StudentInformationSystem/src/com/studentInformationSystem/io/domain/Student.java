package com.studentInformationSystem.io.domain;

import java.sql.Date;

public class Student {
	private int studentId;
	private String studentName;
	private String course;
	private String address;
	private String email;
	private long phoneNumber;
	private Date date;
	private Double fees;
	

	
	
	public Student(int studentId,String studentName,String course, String address,String email,long phoneNumber,Date date,Double fees) {
		super();
		this.studentId=studentId;
		this.studentName=studentName;
		this.course=course;
		this.address =address;
		this.email=email;
		this.phoneNumber=phoneNumber;
		this.date=date;
		this.fees=fees;
		
		
	}

	public Student() {
		
	}


public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId=studentId;
}

	public String getStudentName() {
		return studentName;
	}



	public void  setStudentName(String studentName) {
		this.studentName = studentName;
	}

	
	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course ;
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address ;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email =email; 
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber =phoneNumber; 
	}
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date =date; 
	}
	public Double getFees() {
		return fees;
	}


	public void setFees(Double fees) {
		this.fees =fees; 
	}
	public String toString() {
		return "Student [Student id="+studentId+" , student name=" + studentName + ", course=" + course 
				+ ",Student address="+ address +", Email Address="+email+", Phone number="+phoneNumber+" , Due Date"+date+
				" , course fees="+fees+"]";
	}




}
