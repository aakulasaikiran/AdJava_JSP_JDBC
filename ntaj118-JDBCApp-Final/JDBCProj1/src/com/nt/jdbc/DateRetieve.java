package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetieve {
  private static final String GET_PERSONS_QUERY="SELECT * FROM PERSON_INFO"; 
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sno=0;
		String name=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		SimpleDateFormat sdf=null;
		String dob=null,dom=null,doj=null;
		try{
		/* //register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			*/
			 //register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj118db","root","root");
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_PERSONS_QUERY);
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
					sno=rs.getInt(1);
					name=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdom=rs.getDate(4);
					sqdoj=rs.getDate(5);
					//Convert java.sql.Date class objects to java.util.Date class objects
					udob=(java.util.Date)sqdob;
					udom=(java.util.Date)sqdom;
					udoj=(java.util.Date)sqdoj;
					//Convert java.util.Date class objects to String date values
					sdf=new SimpleDateFormat("dd-MM-yyyy");
					dob=sdf.format(udob);
					dom=sdf.format(udom);
					doj=sdf.format(udoj);
					System.out.println(sno+"  "+name+"  "+dob+"  "+doj+"   "+dom);
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
