
<%@page import="java.sql.*;"%>

<%
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   Connection con=DriverManager.getConnection("jdbc:odbc:accdsn814");
   Statement st=con.createStatement();
%>

<% int sno=Integer.parseInt(request.getParameter("stno"));
   String sname=request.getParameter("stname");
   String addr=request.getParameter("stresult");


%>

<%int res=st.executeUpdate("INSERT INTO STUDENT VALUES("+ sno +",    '"+ sname+"','"+ addr+"')") ;%>
   <html>
		<head>
			<title>
				   My fifth jsp
			</title>
		</head>

		<body>		
				<center><h3>
			   
			   <%
			   if(res==0)
			   {
				   %>
				   Problem in inserting record
				   <%
				}
				else
				{
					 %>
					   Sucessfully Inserted
					  <%
				   }
						%>
		</body>
	</html>
					   <%con.close();
						}
		catch(ClassNotFoundException cnf)
		{}
	catch(Exception e)
	{}
						
			%>
