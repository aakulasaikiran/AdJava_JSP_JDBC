package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UdpateTest {

	public static void main(String[] args) {
		Scanner sc=null;
	   int no=0;
	   String newName=null,newAddrs=null;
	   Connection con=null;
	   Statement st=null;
	   String query=null;
	   int count=0;
		try{
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter existing student number::");
				no=sc.nextInt(); //gives 222
				System.out.println("Enter new  student name:::");
				newName=sc.next(); //gives krish
				System.out.println("Enter new  student addrss:::");
				newAddrs=sc.next(); //gives newyork
			}//if
			//convert  input vlaues as required for the SQL Query
			newName="'"+newName+"'";//gives 'krish'
			newAddrs="'"+newAddrs+"'"; //gives 'newyork'
			//register JDBC driver
			//register JDBC driver
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			  //create Statement object
			  if(con!=null)
				  st=con.createStatement();
			  //prepare SQL Query
			     //update student set sname='raja',sadd='vizag' where sno=101
			   query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
			   System.out.println(query);
			   //send and execute Query
			   if(st!=null)
				   count=st.executeUpdate(query);
			   //process the results
			   if(count==0)
				   System.out.println("Record not found for updation");
			   else
				   System.out.println(count+ "  no.of Records updated");
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
	}//mian
}//class
