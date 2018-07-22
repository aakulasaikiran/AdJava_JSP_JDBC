import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import beans.DbConnector;

public class SelectedBooks extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			String cat = req.getParameter("category");
			String checkAction=req.getParameter("source");

			DbConnector dbc = new DbConnector();

			ArrayList al = dbc.search(cat);
			req.setAttribute("list", al);
			req.setAttribute("category", cat);

			String target;
			if(checkAction.equalsIgnoreCase("Html"))
					target = "HtmlPrint.jsp";
			else
					target = "ExcelScreen.jsp";

			RequestDispatcher rd  = null;
			rd = req.getRequestDispatcher(target);
			if(rd != null)
				rd.forward(req,res);
		} // try
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} // doPost()
} // class