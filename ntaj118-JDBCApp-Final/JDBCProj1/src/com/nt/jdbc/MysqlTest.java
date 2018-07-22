package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try{
			//register jdbc driver
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.gjt.mm.mysql.Driver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:mysql:///ntaj118db","root","root");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ntaj118db","root","root");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("select count(*) from student");
			//process the ResultSet
			if(rs!=null){
				if(rs.next()){
					//count=rs.getInt(1);
					count=rs.getInt("count(*)");
					System.out.println("records count::"+count);
				}//if
				else{
					System.out.println("No records are found");
				}
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
				//close jdbc objs
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
