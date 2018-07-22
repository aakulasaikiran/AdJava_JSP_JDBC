package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class LoginApp1WithPsAndProperties {
  private static final String  AUTHENTICATE_QUERY="SELECT COUNT(*) FROM USERLIST WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		Properties props=null;
		int count=0;
	  try{
		  //locate properties file
		  is=new FileInputStream("src/com/nt/commons/DBDetails.properties");
		  //load properties file content to java.util.Properties class object
		  props=new Properties();
		  props.load(is);
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter username::");
			  user=sc.nextLine(); //gives raja
			  System.out.println("Enter Password:");
			  pass=sc.nextLine(); //gives rani
		  }//if
		  //Load jdbc driver
		  Class.forName(props.getProperty("jdbc.driver"));
		  //establish the connection
		  con=DriverManager.getConnection(props.getProperty("jdbc.url") ,
				                                                              props.getProperty("jdbc.user") ,
				                                                              props.getProperty("jdbc.pwd") );
		  //create PreparedStatement object
		  if(con!=null){
			  ps=con.prepareStatement(AUTHENTICATE_QUERY);
		  }
		  //set values to Query IN param
		  if(ps!=null){
			  ps.setString(1,user);
			  ps.setString(2,pass);
		  }
		  // execute SQL query
		  if(ps!=null){
			  rs=ps.executeQuery();
		  }
		  //process the ResultSet
		  if(rs!=null){
			  if(rs.next()){
				  count=rs.getInt(1);
			  }
		  }//if
		  if(count==0)
			  System.out.println("Invalid Credentials");
		  else
			  System.out.println("Valid Credentials");
	    }//try
	   catch(SQLException se){
		   se.printStackTrace();
	   }
	  catch(ClassNotFoundException cnf){
		  cnf.printStackTrace();
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
	  finally{
		  //close jdbc objs
			try{
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
	  }//finally
	}//main
}//class
