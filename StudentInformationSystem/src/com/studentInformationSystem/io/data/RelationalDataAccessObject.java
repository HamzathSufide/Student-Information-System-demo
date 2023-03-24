package com.studentInformationSystem.io.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.studentInformationSystem.io.connection.DataBaseConnection;
import com.studentInformationSystem.io.connection.DataBaseInformation;
import com.studentInformationSystem.io.domain.Student;

public abstract class RelationalDataAccessObject implements DataAccessObject {
	DataBaseInformation dataConnection=new DataBaseConnection();
	protected Connection connection;
	protected PreparedStatement statement;
	
	public RelationalDataAccessObject() {
		
	}

	public void insert(Object object)  {
		
		connection=dataConnection.getConnection();
		write(object);
		
	}
	@Override
	public ArrayList<Object> listAll() {
		ArrayList<Object> list = new ArrayList<>();
	    
	    try {
	        connection = dataConnection.getConnection();
	       
	        statement = connection.prepareStatement("SELECT * FROM "+getTableName());
	        ResultSet rs = statement.executeQuery();
	        
	        while (rs.next()) {
	        	 
	        	Object obj=read(rs);
	        	list.add(obj);
	        	 
	        }
	        
	    }catch(SQLException m) {
	    	System.out.println("error:"+ m.getMessage());
	    }
	        
	   
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	        	System.out.println("error:"+ e.getMessage());
	        }
	      
	        return list;
	    }
	    
	

	@Override
	public void update(Object object) {
		connection=  dataConnection.getConnection();
		modify(object);
		   try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	        	System.out.println("error:"+ e.getMessage());
	        }
	    }
	public void updateByStudent() {
		connection=  dataConnection.getConnection();
		modifyByStudent();
		   try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	        	System.out.println("error:"+ e.getMessage());
	        }
	    }
	public Object findByPrimaryKey() {
		 connection = dataConnection.getConnection();
		 findPrimaryKey();
		 try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	        	System.out.println("error:"+ e.getMessage());
	        }
	    
		return null;
		
	}
	public void delete() {
	connection = dataConnection.getConnection();
	deleteThis();
	
	 try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
           	
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("error:"+ e.getMessage());
        }

	}
	protected abstract  String write(Object object);
	protected abstract Object read(ResultSet resultSet);
	protected abstract String modify(Object object);
	protected abstract Student findPrimaryKey();
	protected abstract String getTableName();
	protected abstract String deleteThis();
	protected abstract boolean modifyByStudent();
}
