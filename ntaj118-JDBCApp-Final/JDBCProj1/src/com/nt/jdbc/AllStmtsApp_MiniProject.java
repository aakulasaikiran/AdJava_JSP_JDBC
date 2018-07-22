/*create or replace procedure P_FIND_PASS_FAIL(m1 in number,m2 in number,m3 in number,result out varchar)
as 
begin
  if(m1<35 or m2<35 or m3<35)then
       result:='Fail';
 else 
     result:='Pass';
end if;
end;
/
*/
package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllStmtsApp_MiniProject extends JFrame implements ActionListener {
	private static final String  GET_ALL_SNOs="SELECT SNO FROM ALL_STUDENT";
	private static final String  GET_ALL_STUDENTS_BY_NO="SELECT * FROM ALL_STUDENT WHERE SNO=?";
	private static final String  CALL_PROCEDURE="{ call P_FIND_PASS_FAIL(?,?,?,?)}";
     private JLabel  lno,lname,lm1,lm2,lm3,lres;
     private JTextField tname,tm1,tm2,tm3,tres;
     private JButton bDetails,bResult;
     private JComboBox tno;
     private Connection con;
     private Statement st;
     ResultSet rs=null,rs1=null;
     PreparedStatement ps=null;
     CallableStatement cs=null;
     
     public AllStmtsApp_MiniProject() {
    	 System.out.println("constructor");
    	 setTitle("AllStatements-->MiniProject");
    	 setSize(300,300);
    	 setLayout(new FlowLayout());
    	 setBackground(Color.GRAY);
    	 // add comps
    	 lno=new JLabel("student Id::");
    	 add(lno);
    	 tno=new JComboBox();
    	 add(tno);
    	 
    	 bDetails=new JButton("Details");
    	 bDetails.addActionListener(this);
    	 add(bDetails);
    	 
    	 lname=new JLabel("student name::");
    	 add(lname);
    	 tname=new JTextField(10);
    	 add(tname);
    	 
    	 lm1=new JLabel("Marks1::");
    	 add(lm1);
    	 tm1=new JTextField(10);
    	 add(tm1);
    	 
    	 lm2=new JLabel("Marks2::");
    	 add(lm2);
    	 tm2=new JTextField(10);
    	 add(tm2);
    	 

    	 lm3=new JLabel("Marks3::");
    	 add(lm3);
    	 tm3=new JTextField(10);
    	 add(tm3);
    	 
    	 bResult=new JButton("result");
    	 bResult.addActionListener(this);
    	 add(bResult);
    	 
    	 lres=new JLabel("Result::");
    	 add(lres);
    	 tres=new JTextField(10);
    	 add(tres);
    	 
    	 setVisible(true);
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 initialize();
    	 //Disable editing of text boxes
    	 tname.setEditable(false);
    	 tm1.setEditable(false);
    	 tm2.setEditable(false);
    	 tm3.setEditable(false);
    	 tres.setEditable(false);
    	 addWindowListener(new MyWindowAdapter());
	}//constructor
     
     private void initialize(){
    	 System.out.println("initialize()");
    	 try{
    		 //register jdbc driver
    		 Class.forName("oracle.jdbc.driver.OracleDriver");
    		 //Establish the connection
    		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
    				                                                              "system","manager");
    		 //get All  student numbers to ComboBox
    		 
    		 st=con.createStatement();
    		 rs=st.executeQuery(GET_ALL_SNOs);
    		 while(rs.next()){
    			 tno.addItem(rs.getInt(1));
    		 }
    		 System.out.println("all student numbers are added to comboBox during the applicatiion startup");
    		 //create PreparedStatement object
    		 ps=con.prepareStatement(GET_ALL_STUDENTS_BY_NO);
    		 //create CallableStatement objet
    		 cs=con.prepareCall(CALL_PROCEDURE);
    		 //register OUT parameter with JDBC type
    		 cs.registerOutParameter(4,Types.VARCHAR);
    	 }//try
    	 catch(SQLException se){
    		 se.printStackTrace();
    	 }
    	 catch(ClassNotFoundException cnf){
    		 cnf.printStackTrace();
    	 }
     }//initialize()
     
     
	public static void main(String[] args) {
		System.out.println("main method(-)");
		new AllStmtsApp_MiniProject(); 
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
		String result=null;
		System.out.println("actionPerformed(-)");
		if(ae.getSource()==bDetails){
			 System.out.println("details button is clicked");
			 //read selected item from ComboBox
			 no=(Integer)tno.getSelectedItem();
			 //set above no to query parameter
			 try{
				//set above no to query parameter
				 ps.setInt(1,no);
				 //execute Query
				 rs1=ps.executeQuery();
				 //process the ResultSet
				 if(rs1.next()){
					 //set record values to text boxes
					 tname.setText(rs1.getString(2));
				     tm1.setText(rs1.getString(3));
				     tm2.setText(rs1.getString(4));
				     tm3.setText(rs1.getString(5));
				 }//if
			 }//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		}//if
		else{
			System.out.println("Result Button is clicked");
			//read m1,m2,m3 text box values
			m1=Integer.parseInt(tm1.getText());
			m2=Integer.parseInt(tm2.getText());
			m3=Integer.parseInt(tm3.getText());
			try{
			//set the above marks to PL/SQL PROCEDURE IN param value
			cs.setInt(1,m1);
			cs.setInt(2,m2);
			cs.setInt(3,m3);
			//call  PL/SQL Procedure
			cs.execute();
			//gather result from OUT params
			result=cs.getString(4);
			//set result to TExtbox (tres)
			tres.setText(result);
		}//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }	
		}//else
	}//actionPerformed(-)
	private   class MyWindowAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("windowClosing(-)");
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(rs1!=null)
					rs1.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(cs!=null)
					cs.close();
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

		}
	}
}//class
	
