package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class PrimeTag extends TagSupport
{
	private int n=10;
	
	public void setN(int n)
	{
		this.n=n;
	}
	public int getN()
	{
		return n;
	}

	private boolean isPrime(int x)
	{
		for (int k=2;k<x;k++)
		{
			if(x%k==0)
				return false;
		}//for
		return true;
	} //isPrime()
	public int doStartTag()
	{
		System.out.println("Inside doStartTag() of PrimeTag");
		try
		{
			JspWriter out=pageContext.getOut();
			for(int i=1;i<n;i++)
			{
				if(isPrime(i))
					out.print(i+" ");
			}//for
		}//try
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		return SKIP_BODY;
	}//doStartTag()

	public int doEngTag()
	{
			System.out.println("Inside doEngTag() of PrimeTag");
			return EVAL_PAGE;
	}//doEngTag()
}//class