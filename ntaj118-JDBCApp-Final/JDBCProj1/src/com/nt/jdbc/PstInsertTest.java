package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PstInsertTest {
  private static  final String  INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String  name=null,addrs=null;
		int result=0;
		try{
          //read inputs			
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Student Count::");
				count=sc.nextInt(); //gives 4
			}//if
			
		   //register jdbc driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establis the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			 //create PreparedStatement object
			 if(con!=null)
				 ps=con.prepareStatement(INSERT_QUERY);
			 
			 if(sc!=null && ps!=null){
				 for(int i=1;i<=count;++i){
					 System.out.println("Enter student "+i+" student details::");
					 System.out.println("Enter number::");
					 no=sc.nextInt(); 
					 System.out.println("Enter name::");
					 name=sc.next();
					 System.out.println("Enter address::");
					 addrs=sc.next();
					 //set values to query params
					 ps.setInt(1,no);
					 ps.setString(2,name);
					 ps.setString(3,addrs);
					 //exeecute the query
					 result=ps.executeUpdate();
					 //process the result
					 if(result==0)
						 System.out.println("Record not inserted");
					 else
						 System.out.println("Record inserted");
				 }//for
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
