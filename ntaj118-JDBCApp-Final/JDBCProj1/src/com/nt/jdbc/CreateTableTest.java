package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int count=0;
		try{
		//register JDBC driver
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //send and execute SQL Query in Db s/w
		  if(st!=null)
			  count=st.executeUpdate("create table temp(col1 number(5))");
		  //process the result
		  if(count==0)
			  System.out.println("Table not created");
		  else
			  System.out.println("Table created");
		}//try
	catch(ClassNotFoundException cnf){
		cnf.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
			if(st!=null)
				st.close();
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
