package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
/*
  create table person_info(pid number(5) primary key,pname varchar2(20),DOB date,DOM date,DOJ date);
 */
import java.util.Scanner;
public class DateInsertTest {
  private static final String  INSERT_QUERY="INSERT INTO PERSON_INFO VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String pname=null,dob=null,doj=null,dom=null;
		Connection con=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.util.Date udob=null,udoj=null,udom=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		PreparedStatement ps=null;
		int result=0;
		
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Person Id::");
				no=sc.nextInt();
				System.out.println("Enter Person name::");
				pname=sc.next();
				System.out.println("Enter DOB(dd-MM-yyyy)::");
				dob=sc.next();
				System.out.println("Enter DOM(yyyy-MM-dd)::");
				dom=sc.next();
				System.out.println("Enter DOJ(MM-dd-yyyy)");
				doj=sc.next();
			}//if
		/*	//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			//register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj118db1", "root", "root");
			
			
			//Convert date values to  java.sql.Date calss objects
			  //For DOB (dd-MM-yyyy)
			sdf1=new SimpleDateFormat("dd-MM-yyyy");  
			udob=sdf1.parse(dob); //gives java.util.Date class object
			sqdob=new java.sql.Date(udob.getTime()); //gives java.sql.Date class object
			//For DOM (yyyy-MM-dd)
			sqdom=java.sql.Date.valueOf(dom); //gives java.sql.Date class object
			  //For DOJ (dd-MM-yyyy)
			sdf2=new SimpleDateFormat("MM-dd-yyyy");  
			udoj=sdf2.parse(doj); //gives java.util.Date class object
			sqdoj=new java.sql.Date(udoj.getTime());
			//create PreparedStatement object
			if(con!=null){
				ps=con.prepareStatement(INSERT_QUERY);
			}
			//set values to Query params
			if(ps!=null){
				ps.setInt(1,no);
				ps.setString(2,pname);
				ps.setDate(3,sqdob);
				ps.setDate(4,sqdom);
				ps.setDate(5,sqdoj);
			}
			//execute the Query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//proccess the result
			if(result==0)
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
