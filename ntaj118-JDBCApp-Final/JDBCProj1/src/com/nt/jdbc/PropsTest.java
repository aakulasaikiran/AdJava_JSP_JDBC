package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try{
			//Locate Properties file
			is=new FileInputStream("src\\com\\nt\\commons\\myfile.properties");
			//load the content of Properties file to  java.util.Properties class object
			props=new Properties();
			props.load(is);
			//display props obj data
			System.out.println("props obj data::"+props);
			System.out.println("age key value:::"+props.getProperty("age"));
		}//try
		catch(Exception e){
			e.printStackTrace();
		}
	}//main
}//class
