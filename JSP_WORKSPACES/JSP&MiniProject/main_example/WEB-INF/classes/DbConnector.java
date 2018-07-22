package beans;

import java.io.*;
import java.sql.*;

public class DbConnector
{
	private Connection con = null;

	private int id;
	private String name;
	private String add;
	
	public DbConnector()
	{
	}

	public DbConnector(int id, String name, String add)
	{
		this.id = id;
		this.name = name;
		this.add = add;

		con = getConnection();
	}

	public void setID(int id)
	{
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setAdd(String add)
	{
		this.add = add;
	}

	public String getAdd()
	{
		return add;
	}

	public Connection getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:sathya","scott","tiger");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public boolean exists(int id)
	{
		PreparedStatement ps = null;
		try
		{
		  ps = con.prepareStatement("SELECT COUNT(*) FROM STUDENT_INFO " +
										" WHERE STU_ID = ?");
		  ps.setInt(1, id);

		  ResultSet rs = ps.executeQuery();
		  rs.next();
		  
		  if(rs.getInt(1) == 0)
			  return false;
		  else
			  return true;
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		  return false;
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
		}
	} // exists()

	public int insert()
	{
		PreparedStatement ps = null;
		try
		{
			boolean alreadyPresent = exists(this.getID());
				
			if(! alreadyPresent)
			{
				ps = con.prepareStatement("INSERT INTO STUDENT_INFO VALUES(?,?,?)");
				ps.setInt(1, id);	
				ps.setString(2, name);
				ps.setString(3, add);
				ps.executeUpdate();
				return 1;
			}
			else
				return 2;
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			return 7;
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
		}
	} // insert()

	public int delete()
	{
		PreparedStatement ps = null;
		try
		{
			boolean alreadyPresent = exists(this.getID());

			if(alreadyPresent)
			{
				ps = con.prepareStatement("DELETE FROM STUDENT_INFO WHERE STU_ID=?");
				ps.setInt(1,id);
				ps.executeUpdate();
				return 3;
			}	
			else
				return 4;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 7;
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
		}
	} // delete()

	public int update()
	{
		PreparedStatement ps = null;
		try
		{
			boolean alreadyPresent = exists(this.getID());

			if(alreadyPresent)
			{
				ps = con.prepareStatement("UPDATE STUDENT_INFO SET STU_NAME=?,STU_ADD=? WHERE STU_ID = ?");
				ps.setString(1,name);
				ps.setString(2,add);
				ps.setInt(3,id);
				ps.executeUpdate();
				return 5;
			}
			else
				return 4;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 7;
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
		}
	} // update()

	public int display()
	{
		PreparedStatement ps = null;
		
		boolean alreadyPresent = exists(this.getID());

		try
		{
			if(alreadyPresent)
			{
				ps = con.prepareStatement("SELECT STU_NAME,STU_ADD FROM STUDENT_INFO WHERE STU_ID=?");
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();

				if(rs.next())
				{
					String nm = rs.getString(1);
					this.setName(nm);
				
					String ad = rs.getString(2);
					this.setAdd(ad);
				}

				rs.close();

				return 6;
			}
			else 
				return 4;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 7;
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
		}
	} // display()
} // class