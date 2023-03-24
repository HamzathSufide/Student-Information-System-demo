package com.studentInformationSystem.io.main;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.studentInformationSystem.io.connection.Register;
import com.studentInformationSystem.io.consoleRead.StudentRead;
import com.studentInformationSystem.io.data.AdminDataAccessObject;
import com.studentInformationSystem.io.data.DataAccessObject;
import com.studentInformationSystem.io.data.StudentDataAccessObject;
import com.studentInformationSystem.io.domain.Student;
import com.studentInformationSystem.io.services.AdminServices;
import com.studentInformationSystem.io.services.StudentService;

public class Main {
	
	public static void main(String arf[])  {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("======== STUDENT INFORMATION SYSTEM========");
		System.out.println("\n");

		StudentRead studentRead=new StudentRead();
		DataAccessObject dao=new StudentDataAccessObject();
		DataAccessObject dao1=new AdminDataAccessObject();
		StudentService studentService= new StudentService(dao);
		AdminServices adminService=new AdminServices(dao1);
Register register=new Register();
int home = 0;
do {
System.out.println("1 For Login");
System.out.println("2 For Registration");


System.out.println("Enter the Page:");

home=sc.nextInt();

if(home==1) {
	int login;	
	do {
		System.out.println("================================Catagory page leads to login page=======================");
System.out.println("1 For admin");
System.out.println("2 For Students");

System.out.println();
System.out.println("Choose your Catagory:");
 login=sc.nextInt();
if(login==1) {
	System.out.println("==================Admin log in page======================");
	adminService.adminlogin();
int PageNo ;
do{
		System.out.println("=====================WELCOME TO ADMIN PAGE=====================");
		System.out.println("\n");
		
		System.out.println("Page 1 : List All Students");
		System.out.println("Page 2 : Update the Students Details");
		System.out.println("Page 3 : Find the Student by Student Id ");
		System.out.println("Page 4 : Delete the Student by Student Id ");
		System.out.println("Page 5 : Find the total fine exceeded by the student search with id");
		System.out.println("Page 6 : Logout");
		System.out.println("\t");
		System.out.println("Choose the Page:");
		System.out.println("\t");
		 PageNo=sc.nextInt();

	
		switch(PageNo) {
		
		
		
			
		case 1:
			System.out.println("========Page for List Student Details ========");
			System.out.println("\n");
			System.out.println("\t");
			adminService.listAllStudents();
			System.out.println("==================================================================================");
		
			break;
		case 2:
			System.out.println("========Page for Update Student Details ========");
			System.out.println("\n");
			System.out.println("\t");
			Student students=studentRead.studentRead();
			adminService.updateStudents(students);
			System.out.println("==================================================================================");
		
			break;
		case 3:
			System.out.println("========Page for Search Student Details ========");
			System.out.println("\n");
			System.out.println("\t");
			adminService.findStudentByStudentId();
			System.out.println("==================================================================================");
			
			break;
			
		case 4:
			System.out.println("========Page for Delete Student Details ========");
			System.out.println("\n");
			System.out.println("\t");
			adminService.deleteStudent();
			System.out.println("==================================================================================");
			
			break;
			
		case 5:
			System.out.println("========Page for View Fine Student Details ========");
			System.out.println("\n");
			System.out.println("\t");
			adminService.calculateTotalFine();
			System.out.println("==================================================================================");
			
			break;
			
		case 6:
			System.out.println("\t");
			System.out.println("Logged out");
			System.out.println("==================================================================================");
			
			break;
			
			default:
				System.out.println("\t");
				System.out.println("The page ended");
				break;
			}
		

}while (PageNo!=6);

	
}else {
	if(login==2) {
		System.out.println("==================================================================================");
		System.out.println("=========================WELCOME TO STUDENT LOGIN PAGE=====================");
		System.out.println("\n");
studentService.studentlogin();
		int studentPage;
		do {
			
			System.out.println("Page 1 : List All Students");
			System.out.println("Page 2 : Update the Students Details");
			System.out.println("Page 3 : Find the total fine exceeded by the student search with id");
			System.out.println("Page 4 : Logout");
			System.out.println("\t");
			System.out.println("Choose the Page:");
			System.out.println("\t");
			 studentPage=sc.nextInt();
			 switch(studentPage) {
				
				
				case 1:
					System.out.println("========Page for list Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					studentService.listAllStudents();
					System.out.println("==================================================================================");
					
					break;
				case 2:
					System.out.println("========Page for Update Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					
					studentService.updatebyStudents();
					System.out.println("==================================================================================");
					
					break;
				
				case 3:
					System.out.println("========Page for View Fine Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					studentService.calculateTotalFine();
					System.out.println("==================================================================================");
					
					break;
				case 4:
					System.out.println("\t");
					System.out.println("Logged out");
					
					break;
					
					default:
						System.out.println("\t");
						System.out.println("The page ended");
						break;
					}
			 }while (studentPage!=4);
	}
	
		else {
			System.out.println("wrong Number");
		}
			
		}
	}while(login!=2);
}
else {
	if(home==2) {
			
			System.out.println("\t");
			
			System.out.println("===============================Use Admin for the registration==============");
			adminService.adminlogin();
			System.out.println("==============================Register your Account======================");
			System.out.println("\t");
			register.registerStudent();
			int login;	
			do {
				System.out.println("================================Catagory page leads to login page=======================");
		System.out.println("1 For admin");
		System.out.println("2 For Students");

		System.out.println();
		System.out.println("Choose your Catagory:");
		 login=sc.nextInt();
		if(login==1) {
			System.out.println("==================Admin log in page======================");
			adminService.adminlogin();
		int PageNo ;
		do{
				System.out.println("=====================WELCOME TO ADMIN PAGE=====================");
				System.out.println("\n");
			
				System.out.println("Page 1 : List All Students");
				System.out.println("Page 2 : Update the Students Details");
				System.out.println("Page 3 : Find the Student by Student Id ");
				System.out.println("Page 4 : Delete the Student by Student Id ");
				System.out.println("Page 5 : Find the total fine exceeded by the student search with id");
				System.out.println("Page 6 : Logout");
				System.out.println("\t");
				System.out.println("Choose the Page:");
				System.out.println("\t");
				 PageNo=sc.nextInt();

			
				switch(PageNo) {
				
				
				
					
				case 1:
					System.out.println("========Page for List Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					adminService.listAllStudents();
					System.out.println("==================================================================================");
				
					break;
				case 2:
					System.out.println("========Page for Update Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					Student students=studentRead.studentRead();
					adminService.updateStudents(students);
					System.out.println("==================================================================================");
				
					break;
				case 3:
					System.out.println("========Page for Search Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					adminService.findStudentByStudentId();
					System.out.println("==================================================================================");
					
					break;
					
				case 4:
					System.out.println("========Page for Delete Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					adminService.deleteStudent();
					System.out.println("==================================================================================");
					
					break;
					
				case 5:
					System.out.println("========Page for View Fine Student Details ========");
					System.out.println("\n");
					System.out.println("\t");
					adminService.calculateTotalFine();
					System.out.println("==================================================================================");
					
					break;
					
				case 6:
					System.out.println("\t");
					System.out.println("Logged out");
					System.out.println("==================================================================================");
					
					break;
					
					default:
						System.out.println("\t");
						System.out.println("The page ended");
						break;
					}
				

		}while (PageNo!=6);

			
		}else {
			if(login==2) {
				System.out.println("==================================================================================");
				System.out.println("=========================WELCOME TO STUDENT LOGIN PAGE=====================");
				System.out.println("\n");
		studentService.studentlogin();
				int studentPage;
				do {
					
					System.out.println("Page 1 : List All Students");
					System.out.println("Page 2 : Update the Students Details");
					System.out.println("Page 3 : Find the total fine exceeded by the student search with id");
					System.out.println("Page 4 : Logout");
					System.out.println("\t");
					System.out.println("Choose the Page:");
					System.out.println("\t");
					 studentPage=sc.nextInt();
					 switch(studentPage) {
						
						
						case 1:
							System.out.println("========Page for list Student Details ========");
							System.out.println("\n");
							System.out.println("\t");
							studentService.listAllStudents();
							System.out.println("==================================================================================");
							
							break;
						case 2:
							System.out.println("========Page for Update Student Details ========");
							System.out.println("\n");
							System.out.println("\t");
							
							studentService.updatebyStudents();
							System.out.println("==================================================================================");
							
							break;
						
						case 3:
							System.out.println("========Page for View Fine Student Details ========");
							System.out.println("\n");
							System.out.println("\t");
							studentService.calculateTotalFine();
							System.out.println("==================================================================================");
							
							break;
						case 4:
							System.out.println("\t");
							System.out.println("Logged out");
							
							break;
							
							default:
								System.out.println("\t");
								System.out.println("The page ended");
								break;
							}
					 }while (studentPage!=4);
			}
			
				else {
					System.out.println("wrong Number");
				}
					
				}
			}while(login!=2);
	}
}
	}while(home!=2);
}
	
	
	
}	

