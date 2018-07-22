package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try{
		/*	//register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");*/
			
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                                          ResultSet.CONCUR_UPDATABLE);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("select sno,sname,sadd from student");
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
			
		/*	//insert record
			rs.moveToInsertRow();
			rs.updateInt(1,5555);
			rs.updateString(2,"akhil");
			rs.updateString(3,"vizag");
			rs.insertRow();
			System.out.println("Record inserted...");*/
			
			/*//update the record
			rs.absolute(4);
			rs.updateString(3,"vizag");
			rs.updateRow();
			System.out.println("Record updated...");*/
			
			//delete record
			rs.absolute(2);
			rs.deleteRow();
			System.out.println("record deleted");
			
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
