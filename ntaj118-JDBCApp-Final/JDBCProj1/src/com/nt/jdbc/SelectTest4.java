package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest4 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send adn execute SQL Query
			if(st!=null)
				rs=st.executeQuery("select empno,ename,job,sal from emp where sal=(select max(sal) from emp)");
			 //process the ResultSet
			System.out.println("Highest salary emp details are");
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));
				}//while
				
				if(flag==false)
					System.out.println("Records not found");
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf){
		  cnf.printStackTrace();
		}*/
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
