/*SQL> create table studentall(stno number(5) primary key,stname varchar2(20),stadd varchar2(20),stResume CLOB);
*/
package com.nt.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {
  private static final String  CLOB_INSERT="INSERT INTO STUDENTALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
	    String name=null;
	    String addrs=null;
	    String resumePath=null;
	    Connection con=null;
	    PreparedStatement ps=null;
	    Reader reader=null;
	    int result=0;
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Student no::");
				no=sc.nextInt(); //gives 1001
				System.out.println("Enter Student name::");
				name=sc.next();
				System.out.println("Enter student Address::");
				addrs=sc.next();
				System.out.println("Enter Resume Path::");
				resumePath=sc.next();
			}//if
			//create Reader Object pointing ot Dest Resume Doc
			reader=new FileReader(resumePath);
			
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the conneciton
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(CLOB_INSERT);
			//set values to query params
			if(ps!=null){
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setString(3,addrs);
				ps.setCharacterStream(4, reader);
			}
			
			//execut the Query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the resultt
			if(result==0)
				System.out.println("Record  not inserted");
			else
				System.out.println("Reord inserted");
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
			try{
				if(reader!=null)
					reader.close();
			}//try
			catch(IOException se){
				se.printStackTrace();
			}
			try{
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception se){
				se.printStackTrace();
			}
			
		}//finally
	
	}//main
}//class
