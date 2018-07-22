package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class ExampleTag extends TagSupport
{
	public int doStartTag()
	{
		System.out.println("Inside doStartTag() ExampleTag");
		try
		{
			JspWriter out=pageContext.getOut();
			out.print("Prime numbers"+"<br>");
		}
		catch(IOException e)
		{
			System.out.println("Error in ExampleTag: "+ e);
		}
		return(SKIP_BODY);
	}//doStartTag()
	public int doEngTag()
	{
		System.out.println("Inside doEngTag() ExampleTag");
		return EVAL_PAGE;
	}//doEngTag()
}//class