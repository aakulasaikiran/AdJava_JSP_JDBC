package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParameterMetaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		ParameterMetaData pmd=null;
		
		
		try{
/*			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
*/
			//register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement object
			if(con!=null)
				ps=con.prepareStatement("insert into student values(?,?,?)");
			//create ParameterMetaData object
			if(ps!=null){
			   pmd=ps.getParameterMetaData();
			}
			//get Parameter detaiols
			count=pmd.getParameterCount();
			System.out.println("parmeter count::"+count);
			for(int i=1;i<=count;++i){
				System.out.println("parameter number:"+i);
				System.out.println("parameter mode:"+pmd.getParameterMode(i));
				System.out.println("parameter type:"+pmd.getParameterTypeName(i));
				System.out.println("parameter is signed?"+pmd.isSigned(i));
				System.out.println("parameter is nullable?"+pmd.isNullable(i));
			}
			 
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
