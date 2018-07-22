package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBatchInsert {
  private static final String INSERT_QUERY="INSERT INTO  STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
	  Connection con=null;
	  PreparedStatement ps=null;
	  Scanner sc=null;
	  int count=0;
	  int no=0;
	  String name=null,addrs=null;
	  int result[]=null;
	  //read inputs
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter number of students::");
				count=sc.nextInt();
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement object
			if(con!=null)
				 ps=con.prepareStatement(INSERT_QUERY);
			//add multiple set  Query params values to the batch
			if(ps!=null){
				for(int i=1;i<=count;++i){
					System.out.println("Enter"+i+" Student Details");
					System.out.println("Enter number::");
					no=sc.nextInt();
					System.out.println("Enter name ::");
					name=sc.next();
					System.out.println("Enter Address::");
					addrs=sc.next();
					//add inputs to query params batch
					ps.setInt(1,no);
					ps.setString(2,name);
					ps.setString(3, addrs);
					ps.addBatch();
				}
			}//if
			//execute Batch
			if(ps!=null){
				result=ps.executeBatch();
			}
			//process the result
			if(result==null)
				 System.out.println("Group Registration failed");
			else
				 System.out.println("Group Registration succeded");
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
