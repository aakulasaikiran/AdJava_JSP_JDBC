package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.SocketSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {
  private static final String  GET_EMP_BY_NO="SELECT * FROM EMPALL WHERE ENO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String destFilePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		String name=null;
		int salary=0;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead=0;
		
		try{
			sc=new Scanner(System.in);
			//read inputs
			if(sc!=null){
               System.out.println("Enter employee no::");
               no=sc.nextInt();
               System.out.println("Enter Employee Dest File Path::");
               destFilePath=sc.next();
			}//if
			
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj118db1","root", "root");
			
		/*	//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");*/
			//create PreparedStatement object
			if(con!=null){
				ps=con.prepareStatement(GET_EMP_BY_NO);
			}
			//set param values to query
			if(ps!=null){
				ps.setInt(1,no);
			}
		
			// execute SQL Query in DB s/w
			if(ps!=null){
				rs=ps.executeQuery();
			}
			//process the ResultSet
			if(rs!=null){
				if(rs.next()){
					name=rs.getString(2);
					salary=rs.getInt(3);
					is=rs.getBinaryStream(4);
				}
			}//if
			
			//create OutputStream point to dest file
			os=new FileOutputStream(destFilePath);
			
			//write buffer based logic to copy ResultSet BLOB value to Dest file
			buffer=new byte[4096];
			bytesRead=0;
			while((bytesRead=is.read(buffer))!=-1){
				os.write(buffer, 0,bytesRead);
			}//while
			System.out.println("Photo Retrived completely....");
			System.out.println("EmpDetails:::"+no+"  "+name+"  "+salary);
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
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
			
			try{
				if(os!=null)
					os.close();
			}//try
			catch(IOException se){
				se.printStackTrace();
			}
			
			try{
				if(is!=null)
					is.close();
			}//try
			catch(IOException se){
				se.printStackTrace();
			}
			
			try{
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
