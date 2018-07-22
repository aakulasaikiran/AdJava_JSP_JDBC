import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;  

public class DeleteDemo extends HttpServlet
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:satya","scott","tiger");
			ps = con.prepareStatement("delete from student_info where stu_id=?");
			ps.setInt(1,sid);
			rs = ps.executeUpdate();
			if(rs!=1)
				pw.println("<h2>Student id  is problem</h2>");
			else
				pw.println("<h2 style = 'position:absolute;left:50;top:50'>One Record Deleted</h2>");

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
		try{
			doPost(req,res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}