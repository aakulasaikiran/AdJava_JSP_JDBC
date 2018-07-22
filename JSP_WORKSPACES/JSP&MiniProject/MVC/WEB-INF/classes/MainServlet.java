import beans.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import  java.util.ArrayList;

public class MainServlet extends HttpServlet
{
	ModelBean  mb = null;
	HttpSession hs = null;

	public void init(ServletConfig  config)throws ServletException
	{
		super.init(config);
		mb = new ModelBean();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	{
		doPost(req, res);
	} // doGet()



	public void doPost(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			hs = req.getSession();

			String   sourcePage, target= null;
			
			sourcePage = req.getParameter("source");

			if(sourcePage.equalsIgnoreCase("login"))
	     	{
				String un = req.getParameter("uname");
				String pw = req.getParameter("pwd");		

				boolean  userExists = mb.authenticate(un, pw);

				if(userExists)
				{
					ArrayList  list = mb.getAllDepartmentNames();
					hs.setAttribute("DeptNameDetails", list);
					target = "Process.html";
				}
				else
				{
					req.setAttribute("msg", new String("Invalid Username/Password"));
					target = "Login.jsp";
				}
			} // if
			else  if	(sourcePage.equalsIgnoreCase("search"))
			{
				String eno = req.getParameter("eid");
				String dname = req.getParameter("dept");
				String ename = req.getParameter("ena");
				String desg = req.getParameter("desg");

				if(dname==null || dname.length() == 0)
					dname = "%";
				else
					dname.concat("%");

				ename = (ename==null||ename.length() == 0 ?  "%" : ename.concat("%"));
				desg = (desg==null||desg.length() == 0 ?	"%" : desg.concat("%"));

				ArrayList  list = mb.getSearchResults(eno, ename, desg, dname);
				req.setAttribute("SearchResults", list);

				target = "Results.jsp";
			} // else if

			// Forwarding the control to "target" page.
			RequestDispatcher rd = req.getRequestDispatcher(target);
			rd.forward(req, res);
		} // try
		catch(Exception  e)
		{
			e.printStackTrace();
		}
	} // doPost()

	public void destroy()
	{
		mb = null;
	}

} // MainServlet