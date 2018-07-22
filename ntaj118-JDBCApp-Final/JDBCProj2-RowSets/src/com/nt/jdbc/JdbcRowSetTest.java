package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetTest {

	public static void main(String[] args) {
	    JdbcRowSet jrowset=null;
        int count=0;  
	    try{
	    //create JdbcRowSet object
	    jrowset=new   OracleJDBCRowSet();
	    if(jrowset!=null){
	    //set properties
	    jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    jrowset.setUsername("system");
	    jrowset.setPassword("manager");
	    //set query
	    jrowset.setCommand("select * from student");
	    //execute Query
	    jrowset.execute();
	    //process the RowSet
	    while(jrowset.next()){
	    	 if(count==2){
	    		 System.out.println("App is about to sleep");
	    		 Thread.sleep(40000);
	    	 }
	    	System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
	    	count++;
     	    }
	     }//while
	    }//try
	    catch(SQLException se){
	    	se.printStackTrace();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}//main
}//class
