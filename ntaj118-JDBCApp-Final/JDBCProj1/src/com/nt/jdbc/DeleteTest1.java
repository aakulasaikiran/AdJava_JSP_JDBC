package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		String city=null;
		Connection con=null;
		Statement st=null;
		int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter  student city name:");
				city=sc.next(); //hyd
			}//if
			//convert input value as required for the SQL Query
			city="'"+city+"'";  //gives 'hyd'
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and exceute SQL Query in Db s/w
			if(st!=null)
				count=st.executeUpdate("delete from student where sadd="+city);
			//process the Result
			if(count==0)
				System.out.println("Records not found  for deletion");
			else
				System.out.println(count+" no.of records are deleted");
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
