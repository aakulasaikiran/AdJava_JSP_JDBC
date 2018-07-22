package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJDBCRowSet;

public class CachedRowSetTest {

	public static void main(String[] args) {
	    CachedRowSet crowset=null;
        int count=0;  
	    try{
	    //create JdbcRowSet object
	    crowset=new   OracleCachedRowSet();
	    if(crowset!=null){
	    //set properties
	    crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    crowset.setUsername("system");
	    crowset.setPassword("manager");
	    //set query
	    crowset.setCommand("select * from student");
	    //execute Query
	    crowset.execute();
	    //process the RowSet
	    while(crowset.next()){
	    	System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
     	    }
	     }//while
	      crowset.setReadOnly(false);
	       System.out.println("App is sleeping -->Stop DB s/w");
	        Thread.sleep(30000);
	        crowset.absolute(5);
	        crowset.updateString(3,"vizag3");
	        crowset.updateRow();
	        System.out.println("Rowset is modfied -->Inoffline mode");
	        System.out.println("App is sleeping -->Start DB s/w");
	        Thread.sleep(50000);
	        crowset.acceptChanges(); //chanages will reflect to DB s/w
	        //process ResultSet
	        while(crowset.next()){
		    	System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
			  }
	    }//try
	    catch(SQLException se){
	    	se.printStackTrace();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}//main
}//class
