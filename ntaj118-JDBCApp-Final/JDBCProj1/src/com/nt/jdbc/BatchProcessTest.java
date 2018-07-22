package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTest {

	public static void main(String[] args) {
	   Connection con=null;
	   Statement st=null;
	   int result[]=null;
	   int sum=0;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//add Queries to the batch
			if(st!=null){
				st.addBatch("insert into Student values(5366,'raja','manipur')");
				st.addBatch("update Student set sadd='assam1' where sno>=1000");
				st.addBatch("delete from Student where sno=6");
			}
			//execute the Batch
			if(st!=null){
				result=st.executeBatch();
			}
			//process the result
			if(result!=null){
				 for(int i=0;i<result.length;++i)
					 sum=sum+result[i];
			}//if
			System.out.println("No.of records that are effected:::"+sum);
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
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		try{
			if(con!=null)
				con.close();
		}
		catch(Exception se){
			se.printStackTrace();
		}
	}//finally
  }//main
}//class