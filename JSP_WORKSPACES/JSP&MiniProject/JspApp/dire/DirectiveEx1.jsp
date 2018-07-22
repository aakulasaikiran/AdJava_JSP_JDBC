<%--
   Page Directive
--%>

<%@page import="java.util.*"%>
<%!
  Hashtable ht=new Hashtable();
%>

<%
String s=request.getParameter("s1");

if (s.equals("Add"))
{
ht.put(request.getParameter("name"),request.getParameter("value"));
%>

Value Added

<%
}
else if (s.equals("remove"))
{
ht.remove(request.getParameter("name"));
%>

Value Removed

<%
}
else
{
%>

<%@include file="ViewJsp.jsp"%>

<%
}

session.setAttribute("Ht",ht);
%>