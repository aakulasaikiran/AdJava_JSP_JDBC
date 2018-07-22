package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLToOracleTest {
   private static final String  ORA_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
   private static final  String  MYSQL_SELECT_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null,addrs=null;
		try{
			//regisetr jdbc drivers
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Class.forName("com.mysql.jdbc.Driver");
			 //Establish the connection
			 oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj118db","root","root");
			 //create Statement objects
			 if(mysqlCon!=null)
				 st=mysqlCon.createStatement();
			 if(oraCon!=null)
				 ps=oraCon.prepareStatement(ORA_INSERT_QUERY);
			 //execute Select Query on MySQL DB s/w and get ResultSet object
			 if(st!=null){
				 rs=st.executeQuery(MYSQL_SELECT_QUERY);
			 }
			 //copy records from Mysql ResultSet obj to Oracle DB table
			 if(rs!=null && ps!=null){
				 while(rs.next()){
					 //get each record from ResultSet (mysql)
					 no=rs.getInt(1);
					 name=rs.getString(2);
					 addrs=rs.getString(3);
					 //set each recod values to Oracle INSERT QUERY params
					 ps.setInt(1,no);
					 ps.setString(2, name);
					 ps.setString(3,addrs);
					 //execute the Query
					 ps.executeUpdate();
				 }//while
				 System.out.println("Records are copied from Mysql to ORacle");
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
				if(ps!=null)
					ps.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(oraCon!=null)
					oraCon.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(mysqlCon!=null)
					mysqlCon.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			
		}//finally
	}//main
}//class
