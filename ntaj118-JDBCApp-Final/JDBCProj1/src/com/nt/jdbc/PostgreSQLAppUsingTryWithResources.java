package com.nt.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLAppUsingTryWithResources {

	public static void main(String[] args) {
		
		try(Connection con=
				 DriverManager.getConnection("jdbc:postgresql:NtAj118","postgres" ,"root");
               Statement st=con.createStatement();
		      ResultSet rs=st.executeQuery("select * from student");
             ){
			
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//main
}//class
