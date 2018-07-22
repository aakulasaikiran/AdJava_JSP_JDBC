package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TextInsertTest {
	private static final String INSERT_QUERY="INSERT INTO file1.csv  VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		String query=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no:::");
				no=sc.nextInt(); //gives 101
				System.out.println("Enter student name:::");
				name=sc.next(); //gives  raja
				System.out.println("Enter student address::");
				addrs=sc.next(); //gives  hyd
			}//if
			//register JDBC driver
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:odbc:txtdsn");
			  //create Statement object
			  if(con!=null)
				  ps=con.prepareStatement(INSERT_QUERY);
			  //set values to query params
			  if(ps!=null){
				  ps.setInt(1,no);
				  ps.setString(2,name);
				  ps.setString(3,addrs);
			  }
			  //send and execute  SQL Query in Db s/w
			  if(ps!=null)
				  count=ps.executeUpdate();
			  
			  //process the result
			  if(count==0)
				  System.out.println("Record not inserted");
			  else
				  System.out.println("Record inserted");
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
