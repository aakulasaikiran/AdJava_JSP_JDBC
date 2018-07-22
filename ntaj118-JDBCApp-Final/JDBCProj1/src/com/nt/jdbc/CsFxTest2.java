/*create or replace function Fx_VIEW_AND_DELETE_STUDENT
                            (no in number,count out number)return sys_refcursor
as
   details sys_refcursor;
begin
   open details  for
         select * from student where sno=no;
  delete from Student where sno=no;
count:=SQL%ROWCOUNT;
return details;
end;
/
*/
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsFxTest2 {
  private static final String  CALL_FUNCTION="{?=call Fx_VIEW_AND_DELETE_STUDENT(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int cnt=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Student no:");
				no=sc.nextInt();
			}
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create CallableStatment object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION);
			//register return,OUT params
			if(cs!=null){
				cs.registerOutParameter(1, OracleTypes.CURSOR); //return param
				cs.registerOutParameter(3,Types.INTEGER);
			}
			//set value to IN param
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather results from  return param
			if(cs!=null){
				rs=(ResultSet)cs.getObject(1);
			}
			
			//process the ResultSet
			if(rs.next())
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			else 
				System.out.println("Record not found");
			//check wheather record is deleted or not
			if(cs!=null)
				cnt=cs.getInt(3);
			
			if(cnt==0)
				System.out.println("Record not deleted");
			else
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
