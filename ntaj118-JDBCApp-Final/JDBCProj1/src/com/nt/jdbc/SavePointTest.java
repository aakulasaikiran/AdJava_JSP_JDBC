package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int result1=0,result2=0;		
		Savepoint sp=null;
	  try{
		  //Load jdbc driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		  //create Statement object
		  if(con!=null){
			  st=con.createStatement();
		  }
		  //Begin Transaction
		   if(con!=null)
			   con.setAutoCommit(false);
		   //send and execute SQL Queries
		   if(st!=null){
			   result1=st.executeUpdate("insert into student values(6219,'rajesh','hyd')");
			   //create SavePoint
			   sp=con.setSavepoint("p1");
			   result2=st.executeUpdate("update student set sadd='vizag' where sno=5555");
			    //rollback upto Savepoint
			   con.rollback(sp);
			   con.commit();
			   System.out.println("Query1 is committed and Query2 is rolledback");
		   }//if
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
