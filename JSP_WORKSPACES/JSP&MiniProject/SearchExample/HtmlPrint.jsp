<%@page import="java.util.ArrayList, beans.BookBean" %>
<%
	ArrayList al = (ArrayList)request.getAttribute("list");
	String cat = (String)request.getAttribute("category");
%>

<html>

	<script language='javascript'>
		function showprint()
		{
			frames.focus();
			frames.print();
		}
	</script>

<body>
	<form name='f'>
	<center><h2><u>
	Books belonging to category <%= cat.toUpperCase() %>
	<u></h2></center>
	<br>
		
	<table border="1" width="100%">
		<tr>
			<th>Sno</th>
			<th>BookId</th>
			<th>BookName</th>
			<th>AuthorName</th>
			<th>Status</th>
		</tr>
		<%
			for(int i = 0; i < al.size(); i++)
			{
				BookBean sb=(BookBean)al.get(i);
		%>
		<tr>
			<td><%= (i+1) %></td>
			<td><%= sb.getBookId() %></td>
			<td><%= sb.getBookName() %></td>
			<td><%= sb.getAuthorName() %></td>
			<td><%= sb.getStatus() %></td>
		</tr>
		<%
			}
		%>
	</table>

	<center>
	<a href="javascript:showprint()">Print</a>
	</center>
	</form>
</body>
</html>