 /*create or replace procedure GET_EMP_DETAILS(initChars  in varchar,details out sys_refcursor)
   as
   begin
     open details for
           select empno,ename,job,sal from emp where ename like initChars;
  end;
*/
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CsCursorTest {
   private static final String  CALL_PROCEDURE="{CALL GET_EMP_DETAILS(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter initial chars of emp name::");
				initChars=sc.next(); //gives s
			}//if
			//convert input value as required for  SQL query
			initChars=initChars+"%";
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with JDBC types
			if(cs!=null)
				cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set values to IN params
			if(cs!=null)
				cs.setString(1,initChars);
			//call Pl/sql procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT params
			if(cs!=null)
				rs=(ResultSet)cs.getObject(2);
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"  "+rs.getInt(4));
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(cs!=null)
					cs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(con!=null)
					con.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
