/* SQL> select * from jdbc_account;

      ACNO HOLDER                  BALANCE
---------- -------------------- ----------
       101 raja                       5000
       102 rajesh                    12000
   */
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTxMgmtTest {
	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0,destNo=0,amt=0;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
			  System.out.println("Enter Source Account no:");
			  srcNo=sc.nextInt();
			  System.out.println("Enter Dest Account no:");
			  destNo=sc.nextInt();
			  System.out.println("Enter Amount Transfer:");
			  amt=sc.nextInt();
			}//if
			//register JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			
			//disable autocommit on Db s/w (Begin Tx)
			if(con!=null)
				 con.setAutoCommit(false);
			
			// Add Queries to the batch
			if(st!=null){
				//for withdraw operation
				st.addBatch("Update jdbc_account set balance=balance-"+amt+" where acno="+srcNo);
				//for depoiste operation
				st.addBatch("Update jdbc_account set balance=balance+"+amt+" where acno="+destNo);
			}
			
			//execute the batch
			if(st!=null){
				result=st.executeBatch();
			}
			//perform Transaciton management
			if(result!=null){
				for(int i=0;i<result.length;++i){
					if(result[i]==0){
						flag=true;
						break;
					}//if
				}//for
			}//if
			if(con!=null){
				if(flag==true){
					con.rollback();
					System.out.println("Tx Rolled Back-->Money not transffered");
				}
				else{
					con.commit();
					System.out.println("Tx Committed-->Money  transffered");
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
