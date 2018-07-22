import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;  

public class UpdateDemo extends HttpServlet
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
			ps = con.prepareStatement("update student_info set stu_name=?,stu_add=? where stu_id=?");
			ps.setString(1,sname);
			ps.setString(2,sadd);
			ps.setInt(3,sid);
			rs = ps.executeUpdate();
			if(rs!=1)
				pw.println("<h2>Student ID problem</h2>");
			else
				pw.println("<h2 style = 'position:absolute;left:50;top:50'>One Record Updated</h2>");

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