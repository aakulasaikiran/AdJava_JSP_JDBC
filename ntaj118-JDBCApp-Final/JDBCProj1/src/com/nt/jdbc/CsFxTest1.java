/*create or replace function Fx_GET_EMP_DETAILS(no in number,
                       name out varchar,salary out number)return varchar
as
  desg varchar2(15);
 begin
   select ename,job,sal into name,desg,salary from emp where empno=no;
return desg;
end;
/
*/
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFxTest1 {
	private static final String CALL_FUNCTION="{ ?= call Fx_GET_EMP_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		CallableStatement cs=null;
		int no=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter emp number::");
				no=sc.nextInt();
			}
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION);
			//register return ,OUT params with JDBC types
			if(cs!=null){
				cs.registerOutParameter(1, Types.VARCHAR); //return param
				cs.registerOutParameter(3,Types.VARCHAR); //out param
				cs.registerOutParameter(4,Types.INTEGER); //out param
			}
			//set values to IN params
			if(cs!=null){
				cs.setInt(2,no);
			}
			//call Pl/sql Function
			if(cs!=null)
				cs.execute();
			//gather results from OUT params,return Params
			if(cs!=null){
				System.out.println("Desg:::"+cs.getString(1)); //return Param
				System.out.println("Name:::"+cs.getString(3));// Out param 
				System.out.println("Salary:::"+cs.getInt(4)); // out param
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
		
	}//method
}//class
