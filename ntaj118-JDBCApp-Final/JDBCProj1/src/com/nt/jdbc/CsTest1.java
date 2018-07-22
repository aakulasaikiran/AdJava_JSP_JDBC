/*
 create or replace procedure  FIRST_PRO(x in number, y out number)as
begin
  y:=x*x;
end;
 */
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest1 {
   private static final String CALL_FIRST_PRO="{call first_pro(?,?)}"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
			  System.out.println("Enter number:::");
			  no=sc.nextInt(); //gives 10
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				cs=con.prepareCall(CALL_FIRST_PRO);
			//register OUT params with JDBC types
			if(cs!=null)
				cs.registerOutParameter(2,Types.INTEGER);
			//set values to IN parameter
			if(cs!=null)
				cs.setInt(1,no);
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			 //gather results from OUT params
			if(cs!=null)
				result=cs.getInt(2);
			//display the result 
			System.out.println("Result ::"+result);
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
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
