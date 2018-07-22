package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrame extends JFrame  implements ActionListener{
	private static final String  GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
	 private JLabel lno,lname,laddrs;
	 private JTextField tno,tname,tadd;
	 private JButton bFirst,bNext,bPrevious,bLast;
	 private Connection con=null;
	 private Statement st=null;
	 private  ResultSet rs=null;
	 
	public ScrollFrame() {
		System.out.println("constructor....");
		setTitle("Scroll Frame App");
		setSize(400,400);
		setBackground(Color.GRAY);
		setLayout(new FlowLayout());
		//add components to Frame
		lno=new JLabel("student number::");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("student name::");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		laddrs=new JLabel("student address::");
		add(laddrs);
		tadd=new JTextField(10);
		add(tadd);
		
		bFirst=new JButton("First");
		bFirst.addActionListener(this);
		add(bFirst);
		bNext=new JButton("Next");
		bNext.addActionListener(this);
		add(bNext);
		bPrevious=new JButton("Previous");
		bPrevious.addActionListener(this);
		add(bPrevious);
		bLast=new JButton("Last");
		bLast.addActionListener(this);
		add(bLast);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
		tno.setEditable(false); 
		tname.setEditable(false);
		tadd.setEditable(false);
		addWindowListener(new MyWindowAdapter());
	}//constructor
	
	private void initialize(){
		 System.out.println("ScrollFrame:: initialize()");
		 try{
			 //register JDBC Driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create  Statement obj with type,mode
			 st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 //send and execute SQL Query in DB s/w and get Scrollable ResultSet object
			 rs=st.executeQuery(GET_STUDENTS_QUERY);
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
	 }//method

	public static void main(String[] args) {
		System.out.println("main(-)");
          new ScrollFrame();
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		boolean flag=false;
   try{
	  System.out.println("ScrollFrame:: actionPerformed(-)");
	  if(ae.getSource()==bFirst){
			  rs.first();
			  flag=true;
   	  }
	  else if(ae.getSource()==bNext){
		  if(!rs.isLast()){
			  rs.next();
			  flag=true;
		  }
	  }
	  else if(ae.getSource()==bPrevious){
		  if(!rs.isFirst()){
			  rs.previous();
			  flag=true;
		  }
	  }
	  else{
		  rs.last();
		  flag=true;
	  }
	  
	  //set values to Textboxes
	  if(flag==true){
		  tno.setText(rs.getString(1));
		  tname.setText(rs.getString(2));
		  tadd.setText(rs.getString(3));
	  }//if
	}//try
   catch(SQLException se){
	   se.printStackTrace();
   }
   catch(Exception e){
	   e.printStackTrace();
    }
 }//actionPerformed(-)
	
	private class  MyWindowAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("WindowClosing(-)");
			try{
				if(rs!=null)
					rs.close();
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//windowClosing(-)
	}//Adapter class
}//class
