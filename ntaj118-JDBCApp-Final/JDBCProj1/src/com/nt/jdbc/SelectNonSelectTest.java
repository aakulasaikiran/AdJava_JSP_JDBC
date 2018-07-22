package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
	  Scanner sc=null;	
	  String query=null;
	  Connection con=null;
	  Statement st=null;
	  boolean flag=false;
	  ResultSet rs=null;
	  int count=0;
	   try{
		   sc=new Scanner(System.in);
		   if(sc!=null){
		   System.out.println("Enter SQL Query(select or non-select");
		   query=sc.nextLine();
	       }//if
	   //register jdbc driver
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    //establish the connection
	    con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
	    //create Statement object
	    if(con!=null)
	    	st=con.createStatement();
	    //send and execute SQL Query in Db s/w
	    if(st!=null)
	    	flag=st.execute(query);
	    //process the results
	    if(flag==true){
	    	System.out.println("Select Query executed");
	    	rs=st.getResultSet();
	    	while(rs.next()){
	    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
	    	}
	    }
	    else{
	    	System.out.println("Non-Select Query Executed");
	    	count=st.getUpdateCount();
	    	System.out.println(count+" no.of records are updated");
	    }//else
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
