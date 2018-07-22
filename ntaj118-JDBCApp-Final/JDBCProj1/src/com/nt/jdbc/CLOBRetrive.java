package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrive {
  private static final String GET_CLOB_BY_NO="SELECT * FROM STUDENTALL WHERE STNO=?";
	public static void main(String[] args) {
		 Scanner sc=null;
		 int no=0;
		 String destFilePath=null;
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String name=null,addrs=null;
		 Reader reader=null;
		 Writer writer=null;
		 char[] buffer=null;
		 int charsRead=0;
		 try{
			 sc=new Scanner(System.in);
			 if(sc!=null){
				 System.out.println("Enter student no:");
				 no=sc.nextInt();
				 System.out.println("Enter Dest File Path");
				 destFilePath=sc.next();
			 }
			 //register DRiver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			 //create PreparedStatement object
			 if(con!=null)
				 ps=con.prepareStatement(GET_CLOB_BY_NO);
			 //set values to query params
			 if(ps!=null){
				 ps.setInt(1,no);
			 }
			 //execute Query 
			 if(ps!=null)
				 rs=ps.executeQuery();
			 //process the ResultSet
			 if(rs!=null){
				 if(rs.next()){
					 name=rs.getString(2);
					 addrs=rs.getString(3);
					 reader=rs.getCharacterStream(4);
				 }
			 }
			 //create Writer STeam pointing to Dest file
			 writer=new FileWriter(destFilePath);
			 //copy CLOB content to Dest file using Streams and buffer
			 buffer=new char[1024];
			 if(reader!=null && writer!=null){
				 while((charsRead=reader.read(buffer))!=-1){
					 writer.write(buffer,0,charsRead);
				 }//while
			 }//if
				 System.out.println("resume retrieved");
				 System.out.println("STudent Details::"+no+"  "+name+"  "+addrs);
		 }//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(ClassNotFoundException cnf){
				cnf.printStackTrace();
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		 catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					if(rs!=null)
						rs.close();
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
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
					if(sc!=null)
						sc.close();
				}//try
				catch(Exception se){
					se.printStackTrace();
				}
				
				try{
					if(writer!=null)
						writer.close();
				}//try
				catch(IOException se){
					se.printStackTrace();
				}
				
				try{
					if(reader!=null)
						reader.close();
				}//try
				catch(IOException se){
					se.printStackTrace();
				}
				
				
			}//finally

	}

}
