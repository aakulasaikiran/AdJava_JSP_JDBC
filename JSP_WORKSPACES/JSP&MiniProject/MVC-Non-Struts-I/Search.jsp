<%@  page import="java.util.ArrayList"%>
<html>
	<body>
		<form action="controller"  method="post"  target="resultFrame">
			<center><h3> Search  Screen</h3></center>
			<table border="0"  align="center" width="100%"> 
				 <tr>
				   <td align="right">Employee Id </td>
				   <td align="left"><input type="text" name="eid" size="15"></td>
				   <td align="right">Employee Name </td>
				   <td align="left"><input type="text" name="ena" size="25"></td>
				 </tr>
				 <tr>
					<td align="right">Designation </td>
					<td align="left"><input type="text" name="desg" size="15"></td>
					<td align="right">Department Name </td>
					<td align="left">
						<select name="dept">
							<option value="" selected> Select a value </option>
							<% ArrayList list;
									list = (ArrayList)session.getAttribute("DeptNameDetails");

									for(int i=0;i<list.size();i++)
									{ 
							%>
										<option value="<%=(String)list.get(i)%>"><%=(String)list.get(i)%></option>
							<%
								   }
							%>
						   </select>
					</td>
				</tr>

				<tr>
					<td colspan=4 align=center>
						<input type="submit"  value="SEARCH">&nbsp;&nbsp;
						<input type="reset" value="CANCEL">
						</center>
					</td>
				</tr>
			</table>
				
			<input type="hidden" name="source" value="search">
			
		</form>
	</body>
</html>

	