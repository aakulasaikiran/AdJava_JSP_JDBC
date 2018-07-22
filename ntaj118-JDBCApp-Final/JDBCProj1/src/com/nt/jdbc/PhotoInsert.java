/*Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ENO                                       NOT NULL NUMBER(5)
 ENAME                                              VARCHAR2(20)
 ESALARY                                            NUMBER(9,2)
 EPHOTO                                             BLOB
*/
package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsert {
	private static final String INSERT_QUERY="INSERT INTO EMPALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0,salary=0;
		String name=null;
		String path=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		InputStream is=null;
		int length=0;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee number::");
				no=sc.nextInt(); //gives 101
				System.out.println("Enter employee name::");
				name=sc.next(); //gives raja
				System.out.println("Enter Employee salary::");
				salary=sc.nextInt();  //gives 9000
				System.out.println("Enter Employee Photo Path::");
				path=sc.next(); //gives d:/images/alia.gif
			}
			//create InputSTream pointing to Photo file
			 file=new File(path);
			 is=new FileInputStream(file);
			 //get length of the file
			 length=(int) file.length();
			
		/*	//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");*/
			 
			 Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql:///ntaj118db1","root","root");
			 
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//set values to Query params
			if(ps!=null){
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setInt(3, salary);
				ps.setBinaryStream(4,is,length);
			}//if
			//execute the Query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//process the Result
			if(result==0)
				System.out.println("Record insertion failed");
			else
				System.out.println("Records insertion succeded");
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
			
			try{
				if(is!=null)
					is.close();
			}
			catch(IOException se){
				se.printStackTrace();
			}
		}//finally
    
	}//main
}//class
