/*create sequence sno_sequence start with 1 increment by 1;*/
package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIStudentRegistration extends JFrame implements ActionListener,WindowListener {
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	private static final String  GET_SNO_USING_SEQUENCE="SELECT SNO_SEQUENCE.NEXTVAL FROM DUAL";
   private JLabel lno,lname,ladd,lresult;
   private JTextField tno,tname,tadd;
   private JButton btn=null;
   private Connection con=null;
   private PreparedStatement ps=null,ps1=null;
   private ResultSet rs=null;
   
   
	//constructor
	public GUIStudentRegistration() {
		System.out.println("constructor");
	  setTitle("GUI Student Regsitration");
	  setSize(300,300);
	  setLayout(new FlowLayout());
	  //add comps
	  lno=new JLabel("Enter student number:");
	  add(lno);
	  tno=new JTextField(10);
	  tno.setEditable(false);
	  add(tno);
	  
	  lname=new JLabel("Enter student name:");
	  add(lname);
	  tname=new JTextField(10);
	  add(tname);
	  
	  ladd=new JLabel("Enter student address:");
	 add(ladd);
	  tadd=new JTextField(10);
	  add(tadd);
	  
	  btn=new JButton("register");
	  btn.addActionListener(this);
	  add(btn);
	  
	  lresult=new JLabel("");
	 add(lresult);
	  
	  setVisible(true);
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  //add windowListener to FRamewindow
	  addWindowListener(this);
	  initialize();
	}//constructor
	
	private  void initialize(){  //user-defined method
		System.out.println("initialize() method");
		//create connection
		try{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_STUDENT_QUERY);
			ps1=con.prepareStatement(GET_SNO_USING_SEQUENCE);
		}
		catch(SQLException  se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//initialize()
	
	public static void main(String[] args) {
		System.out.println("main --->");
		 new GUIStudentRegistration();
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("actionPerformed(-)");
		int no=0;
		String name=null,addrs=null;
		int result=0;
		
		try{
		// get Student number from Sequence
		rs=ps1.executeQuery();
		if(rs.next()){
			no=rs.getInt(1);
		}
		//read text box values
		name=tname.getText();
		addrs=tadd.getText();
		//set values to Query params
		ps.setInt(1,no);
		ps.setString(2,name);
		ps.setString(3,addrs);
		//execute the Query
		result=ps.executeUpdate();
		//set student number to text box
		tno.setText(String.valueOf(no));
		tno.setForeground(Color.CYAN);
		
		//process the Result
		if(result==0){
			lresult.setText("Registration failed");
			lresult.setForeground(Color.RED);
		}
		else {
			lresult.setText("Registration succeded");
			lresult.setForeground(Color.GREEN);
		}
		}//try
		catch(SQLException se){
			lresult.setForeground(Color.RED);
			lresult.setText("Registration Failed::"+se.getMessage());
			se.printStackTrace();
		}
		catch(Exception e){
			lresult.setForeground(Color.RED);
			lresult.setText("Internal problem");
			e.printStackTrace();
		}
	}//method

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUIStudentRegistration:windowClosing(-)");
		try{
			if(rs!=null)
				rs.close();
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
			if(ps1!=null)
				ps1.close();
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

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class
