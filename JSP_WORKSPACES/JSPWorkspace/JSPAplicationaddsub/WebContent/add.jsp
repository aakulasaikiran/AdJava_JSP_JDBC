<%!
int n1,n2,n3;
%>
<%
n1=Integer.parseInt(application.getInitParameter("p1"));
n2=Integer.parseInt(application.getInitParameter("p2"));
n3=n1+n2;
%>
<html>
<body bgcolor=yellow>
<h2>Add is=<%=n3 %></h2>
</body>

</html>