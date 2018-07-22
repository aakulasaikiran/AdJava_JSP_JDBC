package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToOracleTest {
 private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
 private static final  String SELECT_STUDENT_QUERY="SELECT * FROM [Sheet1$]";
	public static void main(String[] args) {
		Connection excelCon=null,oraCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sno=0;
		String sname=null,addrs=null;
		int result=0;
		
		try{
			//register jdbc drivers
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connections
			excelCon=DriverManager.getConnection("jdbc:odbc:xlsdsn");
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create Statement,PreparedStatement objs
			if(excelCon!=null)
				st=excelCon.createStatement();
			if(oraCon!=null)
				ps=oraCon.prepareStatement(INSERT_STUDENT_QUERY);
			//send and execute Select Query on Excel DB s/w
			if(st!=null)
				rs=st.executeQuery(SELECT_STUDENT_QUERY);
			//Copy records from Excel sheet to Oracle DB table..
			if(rs!=null){
				while(rs.next()){
					//get each record from ResultSet (Excel)
					sno=rs.getInt(1);
					sname=rs.getString(2);
					addrs=rs.getString(3);
					//set values to Query params (insert record to Oracle ) 
					ps.setInt(1,sno);
					ps.setString(2,sname);
					ps.setString(3,addrs);
					//execute the Query
					result=ps.executeUpdate();
				}//while
				System.out.println("Records are copied...");
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
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
			try{
				if(excelCon!=null)
					excelCon.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
			try{
				if(oraCon!=null)
					oraCon.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
