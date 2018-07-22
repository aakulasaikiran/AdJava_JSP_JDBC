package com.nt.jdbc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrameUsingEclipseGUIBuilder {
   private static final String  GET_STUDENTS="SELECT * FROM STUDENT";
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final Action action = new SwingAction();
	private Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	
	private void myInitialize(){
		try{
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object 
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//create Scrollable ResultSet object
			rs=st.executeQuery(GET_STUDENTS);
		}//try
		catch(Exception e ){
			e.printStackTrace();
		}
	}//MyInitialize()

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollFrameUsingEclipseGUIBuilder window = new ScrollFrameUsingEclipseGUIBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrollFrameUsingEclipseGUIBuilder() {
		myInitialize();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student number");
		lblNewLabel.setBounds(51, 36, 99, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(180, 33, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("student name");
		lblNewLabel_1.setBounds(58, 88, 99, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 85, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Student Address");
		lblNewLabel_2.setBounds(58, 151, 99, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 148, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("first");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      try{
			    	  rs.first();
			    	  textField.setText(rs.getString(1));
			    	  textField_1.setText(rs.getString(2));
			    	  textField_2.setText(rs.getString(3));
			      }//try
			      catch(SQLException se){
			    	  se.printStackTrace();
			      }
			}
		});
		btnNewButton.setBounds(0, 197, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(!rs.isLast()){
						rs.next();
						  textField.setText(rs.getString(1));
				    	  textField_1.setText(rs.getString(2));
				    	  textField_2.setText(rs.getString(3));
					 }
				}//try
					catch(SQLException se){
						se.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(109, 197, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(!rs.isFirst()){
						rs.previous();
						  textField.setText(rs.getString(1));
				    	  textField_1.setText(rs.getString(2));
				    	  textField_2.setText(rs.getString(3));
					 }
				}//try
					catch(SQLException se){
						se.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(233, 197, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnLast = new JButton("last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					   rs.last();
 					  textField.setText(rs.getString(1));
			    	  textField_1.setText(rs.getString(2));
			    	  textField_2.setText(rs.getString(3));
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
			}
		});
		btnLast.setBounds(335, 197, 97, 25);
		frame.getContentPane().add(btnLast);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
