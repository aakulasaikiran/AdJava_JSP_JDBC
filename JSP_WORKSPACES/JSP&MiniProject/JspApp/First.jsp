<!--  this is HTML comment -->  
<%-- this is Jsp comments --%>
<%@ page session="true" errorPage="xyz.jsp" %>

<html>
  <head>
  <title>
    My First JSP
	</title>
	</head>
	<%! int x,y;%>
	<%! int mul(int a,int b)
	{ 
		int z;
		z = a*b;
		return z;
		//int abc;
	} %>

	<body>
	  <center><h2>WELCOME TO JAVA SERVER PAGES1</h2>
	  Today's date is<%= new java.util.Date()%>
	  <%Class.forName("java.lang.System1"); %>
	  <br>
	  <br>
	  <%x=70;y=69;%>Multiplication of <%=x%>&nbsp;&nbsp; and&nbsp;&nbsp;<%=y%>&nbsp;&nbsp;is&nbsp;<%=mul(x,y)%>
	</center>
	</body>
</html>
