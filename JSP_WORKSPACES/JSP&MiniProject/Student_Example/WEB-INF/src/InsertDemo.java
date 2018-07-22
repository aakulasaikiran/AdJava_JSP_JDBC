import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;  

public class InsertDemo extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		try
		{
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			pw.println("<html><form target = 'display'>");
			int sid = Integer.parseInt(req.getParameter("stu_id"));
			String sname = req.getParameter("stu_name");
			String sadd = req.getParameter("stu_add");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:satya","scott","tiger");
			System.out.println("connection");
			ps = con.prepareStatement("insert into student_info values(?,?,?)");
			ps.setInt(1,sid);
			ps.setString(2,sname);
			ps.setString(3,sadd);
			rs = ps.executeUpdate();
			if(rs!=1)
				pw.println("<h2>Record is problem</h2>");
			else
				pw.println("<h2 style = 'position:absolute;left:50;top:50'>One Record Inserted Successfully</h2>");

			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			doPost(req,res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}