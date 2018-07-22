package beans;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DbConnector
{
	private int found=0;

	public Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:sathya","scott","tiger");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public ArrayList search(String category)
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList al = new ArrayList();

		try
		{
			String searchQuery;
			searchQuery = "SELECT BOOKID, BOOKNAME, AUTHORNAME, STATUS " +
									" FROM SELECT_BOOKS WHERE CATEGORY = ? ";
			ps = con.prepareStatement(searchQuery);

			ps.setString(1, category);
			rs = ps.executeQuery();

			while(rs.next())
			{
				BookBean b = new BookBean();
				b.setBookId(rs.getString(1));
				b.setBookName(rs.getString(2));
				b.setAuthorName(rs.getString(3));
				b.setStatus(rs.getString(4));
				al.add(b);
			}
			rs.close();
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		}
		finally
		{
			if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		} // finally
		return al;
	} // search()
} // class