package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PstSelectTest5 {
  private static final String GET_EMP_COUNT_QUERY="SELECT COUNT(*) FROM EMP";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				ps=con.prepareStatement(GET_EMP_COUNT_QUERY);
			//send and execute SQL Query in DB s/w
			if(ps!=null)
				rs=ps.executeQuery();
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
