package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetTest {

	public static void main(String[] args) {
	    WebRowSet wrowset=null;
	    FileWriter writer=null;
        try{
	    //create JdbcRowSet object
	    wrowset=new   OracleWebRowSet();
	    if(wrowset!=null){
	    //set properties
	    wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    wrowset.setUsername("system");
	    wrowset.setPassword("manager");
	    //set query
	    wrowset.setCommand("select * from student");
	    //execute Query
	    wrowset.execute();
	    //process the RowSet
	    while(wrowset.next()){
	    	System.out.println(wrowset.getInt(1)+"  "+wrowset.getString(2)+"  "+wrowset.getString(3));
     	    }
	     }//while
	    //writing to XML file
	    writer=new FileWriter("d:/student.xml");
	    wrowset.writeXml(writer);
	    writer.close();
	    
  	    }//try
	    catch(SQLException se){
	    	se.printStackTrace();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}//main
}//class
