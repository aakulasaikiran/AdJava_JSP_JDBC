package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
   private static final String STUDENT_SELECT="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		OracleConnectionPoolDataSource ds=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//set details
			ds=new OracleConnectionPoolDataSource();
			if(ds!=null){
			  ds.setDriverType("thin");
			   ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			   ds.setUser("system");
			   ds.setPassword("manager"); // By using alll these details it internally create jdbc con pool
			}
			
			//get connection obj from jdbc con pool
			if(ds!=null){
			  con=ds.getConnection();
			}
			//create PreparedStaement object
			if(con!=null){
				ps=con.prepareStatement(STUDENT_SELECT);
			}
			//send and execute SQL Query
			if(ps!=null){
				rs=ps.executeQuery();
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
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(con!=null)
					con.close();  //returns the con obj back to con pool
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
