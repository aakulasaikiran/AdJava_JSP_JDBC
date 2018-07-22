package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
		Connection con=null;
		Statement st=null;
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
			//Convert input values as required for the SQL Query
			name="'"+name+"'";   //gives 'raja'
			addrs="'"+addrs+"'";  //gives 'hyd'
			//register JDBC driver
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			  //create Statement object
			  if(con!=null)
				  st=con.createStatement();
			  //prepare Query
			        //insert into student values(111,'ramesh','hyd')
			  query="insert into student values("+no+","+name+","+addrs+")";
			  System.out.println(query);
			  
			  //send and execute  SQL Query in Db s/w
			  if(st!=null)
				  count=st.executeUpdate(query);
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
