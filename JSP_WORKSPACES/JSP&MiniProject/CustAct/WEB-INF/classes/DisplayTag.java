package tags;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class DisplayTag extends TagSupport
{
	private String font;
	private int size=20;

	public void setFont(String font)
	{
		this.font=font;
	}
	public void setSize(int fontSize)
	{
		this.size=fontSize;
	}
	
	public int doStartTag()
	{
		System.out.println("Inside doStartTag() of DisplayTag !!!.....");
		String status=null;
		try
		{
			JspWriter out=pageContext.getOut();
			ServletRequest req=pageContext.getRequest();
			status=req.getParameter("print");
			out.print("<table border=\"0\">");
			out.print("<tr><th><span style= \" font-size:"+size+"px; " + "font-family: " +font+ "; \">");
		}
		catch(IOException ioe)
		{
			System.out.println("Error in ExampleTag: "+ioe);
		}
		if(status==null)
			return(SKIP_BODY);
		else 
			if(status.equalsIgnoreCase("YES"))
			return(EVAL_BODY_INCLUDE);
		else
			return(SKIP_BODY);
	}//doStartTag()
	
	public int doEngTag()
	{
		System.out.println("Inside doEngTag()");
		try
		{
			JspWriter out=pageContext.getOut();
			out.print("</SPAN></TABLE>");
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		return EVAL_PAGE;
	}//doEngTag()
}//class