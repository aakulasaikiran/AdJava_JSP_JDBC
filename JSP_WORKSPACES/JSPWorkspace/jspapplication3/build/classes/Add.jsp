<%!
int n1,n2,n3;
%>
<%
n1=Integer.parseInt(application.getInitParameter("p1"));
n2=Integer.parseInt(application.getInitParameter("p2"));
n3=n1+n2;
%>
<h2>sum is <%=n3 %></h2>