package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("select * from "+args[0]);
			//get ResultSetMetaData object
			  if(rs!=null)
				  rsmd=rs.getMetaData();
			  //display col labels
			  colCount=rsmd.getColumnCount();
			  for(int i=1;i<=colCount;++i)
				  System.out.print(rsmd.getColumnLabel(i)+"\t\t");
			  System.out.println();
			  //display col types
			  for(int i=1;i<=colCount;++i)
				  System.out.print(rsmd.getColumnTypeName(i)+"\t");
			  System.out.println();
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
					for(int i=1;i<=colCount;++i){
						System.out.print(rs.getString(i)+"\t\t ");
					}
					System.out.println();
				}//while
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
