package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTxMgmtTest {

	public static void main(String[] args) {
	   Connection con=null;
	   Statement st=null;
	   int result[]=null;
	   int sum=0;
		boolean flag=false;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//disable AutoCommit mode on DB s/w (Begin Transaction)
			if(con!=null)
				 con.setAutoCommit(false);
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//add Queries to the batch
			if(st!=null){
				st.addBatch("insert into Student values(1426,'raja','manipur')");
				st.addBatch("update Student set sadd='nagaland' where sno>=1000");
				st.addBatch("delete from Student where sno=5");
			}
			//execute the Batch
			if(st!=null){
				result=st.executeBatch();
			}
		//perform Tx mgmt
		    for(int i=0;i<result.length;++i){
		        if(result[i]==0){
		             flag=true;
		             break;
		          }//if
		     }//for
		   if(flag==true){
		         con.rollback();
		             System.out.println("Tx is rolled back");
		      }
		   else{
		      con.commit();
		           System.out.println("Tx is committed");
		     }
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