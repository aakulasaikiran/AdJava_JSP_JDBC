package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ScrollableTestWithPropeties {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		InputStream is=null;
		Properties props=null;
		try{
			//load proeprties file
			is=new FileInputStream("src/com/nt/commons/DBCredentials.properties");
			//Load properties file content to java.util.Properties object
			 props=new Properties();
			props.load(is);
		//register Jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",props );
		//create Statement object
		if(con!=null){
		   // st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(1004,1007);
		}
		//send and execute SQL Query
		if(st!=null)
			rs=st.executeQuery("select * from student");
		//process the ResultSet 
		System.out.println("Top--->Bottom");
		if(rs!=null){
		 while(rs.next()){
			System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
		   }//while
		}//if
		 rs.afterLast();
		//process the ResultSet 
		System.out.println("Bottom---->Top");
		if(rs!=null){
			 while(rs.previous()){
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
				   }//while
		}//if
		
		//print records dynamically and randomly...
		if(rs!=null){
		rs.absolute(2);
		System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.first();
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.last();
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.absolute(-3);
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.absolute(4);
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.relative(2);
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
        rs.relative(-3);
        System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
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
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
