package com.nt.jdbc;

import java.text.SimpleDateFormat;

public class DateBasicsTest {

	public static void main(String[] args) throws Exception{
		String s1="20-10-1980"; // dd-MM-yyyy
		String s2="1999-10-20"; //yyyy-MM-dd
		SimpleDateFormat sdf=null,sdf1=null;
		java.util.Date ud1=null,ud2=null;
		java.sql.Date sqd1=null,sqd2=null;
		String s3=null;
		
		long ms=0;
		//Converting String Date value to java.util.Date class object
		sdf=new SimpleDateFormat("dd-MM-yyyy"); //specifies String date pattern
		ud1=sdf.parse(s1);
		System.out.println("util date::"+ud1);
		//Converting java.util.Date class object to java.sql.Date class object
		ms=ud1.getTime();  //gives java.util.Date class obj's date and time in ms
		sqd1=new java.sql.Date(ms);
		System.out.println("sql date::"+sqd1);
		//we can convert String date value directly to java.sql.Date class object if given
		//String Date pattern is in "yyyy-MM-dd"
		 sqd2=java.sql.Date.valueOf(s2);
		 System.out.println("sql date:::"+sqd2);
		 //Converting java.sql.Date class object to java.util.Date class object
		 ud2=(java.util.Date)sqd2;
		 System.out.println("util date:::"+ud2);
		 
		 //create SimpleDateFormat class object
		 sdf1=new SimpleDateFormat("MMM-yyyy-dd");
		 s3=sdf1.format(ud2);
		 System.out.println("String date :::"+s3);
	}//main
}//class
