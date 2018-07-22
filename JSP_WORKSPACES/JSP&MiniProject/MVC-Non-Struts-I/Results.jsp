<%@  page import="java.util.ArrayList, beans.Employee"%>

<%ArrayList list = (ArrayList)request.getAttribute("SearchResults"); %>

<html>
	<body>
		<table align="center" width="100%" border="1"> 
			 <tr>
					<th align="center">Employee Id </th>
					<th align="center">Name </th>
					<th align="center">Designation </th>
					<th align="center">Salary </th>
					<th align="center">Department </th>
			</tr>

			  <% 
						if(list.size()==0)
					   {
				%>
						  <tr>
							  <td align="center" colspan="5">
									 No matching records for the given search criterion !!!
							 </td>
						 </tr>
			<% 
					}
					else
					{
						for(int i=0;i<list.size();i++)
						{
							Employee emp = (Employee)list.get(i);
			%>

							<tr>
								  <td align="center"><%=emp.getId()%></td>
								  <td align="center"><%=emp.getName()%></td>
								  <td align="center"><%=emp.getDesg()%></td>
								  <td align="center"><%=emp.getSalary()%></td>
								  <td align="center"><%=emp.getDname()%></td>
							</tr>
			<%
						} //for
					} // else
			%>

		</table>
	</body>
</html>
	

   
