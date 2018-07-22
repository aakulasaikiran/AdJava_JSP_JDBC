import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import beans.DbConnector;

public class StudentDemo extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session = null;		
		int flag = 0;
		int forward = 0;
		int output = 0;
		
		String store = null;
		String value = null;
		String dispval1,dispval2,dispval3;

		DbConnector dbc;

		try
		{
			session = req.getSession(true);		
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
		
			int s_id = Integer.parseInt(req.getParameter("stu_id"));
			String s_name = req.getParameter("stu_name");
			String s_add = req.getParameter("stu_add");
			
			String checkAction = req.getParameter("setVal");		
			
			dbc = new DbConnector(s_id,s_name,s_add);
			
			if(checkAction.equals("insert"))
				output = dbc.insert();				
			else if(checkAction.equals("delete"))
				output = dbc.delete();
			else if(checkAction.equals("update"))
				output = dbc.update();
			else if(checkAction.equals("display"))
			{
				output = dbc.display();
	
				dispval1 = String.valueOf(dbc.getID());
				dispval2 = dbc.getName();
				dispval3 = dbc.getAdd();				
				
				req.setAttribute("no", dispval1);
				req.setAttribute("name", dispval2);
				req.setAttribute("address", dispval3);
			}
			
			value = String.valueOf(output);
			req.setAttribute("msg", value);
			RequestDispatcher rd = req.getRequestDispatcher("StudentDetails.jsp");
			rd.forward(req,res);
		} // try
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} // doPost()
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		try{
			doPost(req,res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} // doGet()
} // class