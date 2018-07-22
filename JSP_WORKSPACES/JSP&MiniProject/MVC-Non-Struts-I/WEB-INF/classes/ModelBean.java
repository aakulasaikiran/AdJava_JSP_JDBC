import  java.sql.*;
import  java.io.*;
import  java.util.*;
import  beans.Employee;

public class ModelBean 
{
	static Connection  con = null;

	static
	{
		try
		{
			InputStream is = new FileInputStream("d:\\details.props");
		    Properties p = new Properties();
		    p.load(is);

		    String dname = p.getProperty("driver");
			String  url = p.getProperty("url");
		    String user= p.getProperty("user");
		    String pass = p.getProperty("password");

			Class c =	 Class.forName(dname); 
		    con = DriverManager.getConnection(url,user, pass);
		} // try
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}			
	} // static block

	public  boolean  authenticate(String  username,  String  password)
	{
		PreparedStatement  ps = null;
		ResultSet rs  = null;
		boolean  result = false;

		try
		{
			String  query;
			query = "SELECT COUNT(*) FROM MY_USERS " +
									" WHERE USERNAME=? AND PWD=? ";

			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			rs.next();				// Moves the record pointer to the first record.

			if(rs.getInt(1) != 0)
				result = true;

		} // try
		catch(SQLException  se)
		{
			se.printStackTrace();
		}
		finally
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}

			if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		} // finally

		return result;
	} // authenticate() method

	public  ArrayList  getAllDepartmentNames()
	{
		PreparedStatement  ps = null;
		ResultSet rs = null;
		ArrayList list=new ArrayList();

		try
		{
			String  query;
			query = "SELECT DISTINCT DNAME FROM MY_DEPT";
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			 // All department  names  are  added to ArrayList.
		    while(rs.next())
			{
				  list.add(rs.getString("DNAME"));
			}
		} // try
		catch(SQLException  se)
		{
			se.printStackTrace();
		}
		finally
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}

			if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		} // finally

		return  list;
	} // getAllDepartmentNames() method
	
	public  ArrayList  getSearchResults(String eno, String  ename, 
																							String  desg, String dname)
	{
		PreparedStatement  ps = null;
		ResultSet rs = null;
		ArrayList list=new ArrayList();

		try
		{
			String  query;
			query = " SELECT MY_EMP.DEPTNO, ENO, ENAME,SAL, DESG, DNAME " +
								" FROM MY_EMP,MY_DEPT " +
								" WHERE MY_EMP.DEPTNO = MY_DEPT.DEPTNO "  +
								" AND MY_EMP.ENAME LIKE ? " +
								" AND MY_EMP.DESG LIKE ? " +
								" AND MY_DEPT.DNAME LIKE	? ";

			if(eno != null && eno.length() != 0)
				query.concat(" AND MY_EMP.ENO = ? ");

			ps = con.prepareStatement(query);
			ps.setString(1, ename);
			ps.setString(2, desg);
			ps.setString(3, dname);

			if(eno != null && eno.length() != 0)
				ps.setInt(4, Integer.parseInt(eno));
			
			rs = ps.executeQuery();

			 // Each record data present in the ResultSet object, 
			 //			is  stored in a Employee instance, and all instances
			 //			are added to ArrayList.

		    while(rs.next())
			{
				  Employee emp=new Employee();
				  emp.setDeptno(rs.getInt("deptno"));
				  emp.setId(rs.getInt("eno"));
				  emp.setName(rs.getString("ename"));
				  emp.setDesg(rs.getString("desg"));
				  emp.setSalary(rs.getDouble("sal"));
				  emp.setDname(rs.getString("dname"));

				  list.add(emp);
			}  
		} // try
		catch(SQLException  se)
		{
			se.printStackTrace();
		}
		finally
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}

			if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}	// finally

		return  list;
	} // getSearchResults() method	
} // ModelBean
