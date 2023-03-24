package com.studentInformationSystem.io.consoleRead;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;

import com.studentInformationSystem.io.domain.Student;

public class StudentRead {
    Student student = new Student();

    public Student studentRead() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = in.next();
        student.setStudentName(name);

        System.out.print("Enter the course: ");
        String course = in.next();
        student.setCourse(course);

        System.out.print("Enter the Address: ");
        String address = in.next();
        student.setAddress(address);

        System.out.print("Enter Email Address: ");
        String email = in.next();
        student.setEmail(email);

        System.out.print("Enter Phone number: ");
        long phone = in.nextLong();
        student.setPhoneNumber(phone);

        System.out.print("Enter the Date (YY-MM-DD): ");
        String dateString = in.next();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
            format.setLenient(false);
            java.util.Date utilDate = format.parse(dateString);
            Date sqlDate = new Date(utilDate.getTime());
            student.setDate(sqlDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format YY-MM-DD");
        }
        System.out.println("Enter the Fees:");
        double fees=in.nextDouble();
        student.setFees(fees);
        
     
        

        return student;
    }
}
