<%!
int n1,n2,n3;
%>
<%
n1=Integer.parseInt(request.getParameter("t1"));
n2=Integer.parseInt(request.getParameter("t2"));
n3=n1+n2;
%>
<html>
<body bgcolor=yellow>
<h2>Sums<%=n3 %></h2>
</body>

</html>