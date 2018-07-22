package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCapabilities_DBMetaData {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			
			//create DatabaseMetaData object
			if(con!=null)
				dbmd=con.getMetaData();
			System.out.println("DatabaseMetaData obj class name::"+dbmd.getClass());
			//display DB s/w details
			if(dbmd!=null){
				System.out.println("DB name::"+dbmd.getDatabaseProductName());
				System.out.println("DB Version::"+dbmd.getDatabaseProductVersion());
				System.out.println("DB major and minor version::"+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion());
				System.out.println("DB SQL keywords::"+dbmd.getSQLKeywords());
				System.out.println("DB numberic functions::"+dbmd.getNumericFunctions());
				System.out.println("DB String functions::"+dbmd.getStringFunctions());
				System.out.println("DB system functions::"+dbmd.getSystemFunctions());
				System.out.println("Max Table  name length::"+dbmd.getMaxTableNameLength());
				System.out.println("Max Column  name length::"+dbmd.getMaxColumnNameLength());
				System.out.println("Max Cols in DB table::"+dbmd.getMaxColumnsInTable());
				System.out.println("Max Cols in GroupBy::"+dbmd.getMaxColumnsInGroupBy());
				System.out.println("Max RowSize::"+dbmd.getMaxRowSize());
				System.out.println("supports Procedures:::"+dbmd.supportsStoredProcedures());
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
			       if(con!=null)
			    	   con.close();
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
