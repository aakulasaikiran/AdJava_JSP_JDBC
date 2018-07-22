package com.nt.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLApp {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//register jdbc driver
		//	Class.forName("org.postgresql.Driver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:postgresql:NtAj118","postgres","root");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NtAj118","postgres" ,"root");
			//ccreate STatement object
			if(con!=null)
			  st=con.createStatement();
			//send and execute SQL Query in Db s/w
			if(st!=null){
				rs=st.executeQuery("select * from student");
			}
			//process the ResultSet 
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}*/
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
