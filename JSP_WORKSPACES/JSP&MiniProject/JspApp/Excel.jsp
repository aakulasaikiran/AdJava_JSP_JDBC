<%-- JSP which sends employee details as response,back to the client.
           we can view the details, in EXCEL worksheet, if we give "excel" as value to "format" request parameter.  --%>


		   <HTML>
		                 <HEAD>
								<TITLE>Employees" Details</TITLE>
			</HEAD>
			<BODY>
						<CENTER>
									<H2>Details of Employees</H2>
					   <%

					             String format= request.getParameter("format");
								 if((format !=null) && (format.equals("excel")))
								 {
									 response.setContentType("application/vnd.ms-excel");
								 }
								 %>
								 
											<TD><TH>Id<TH>Name</TH>Salary</TH></TR>
											<TR><TD>115</TD><TD>Sachin</TD><TD>50000</TD></TR>
											<TR><TD>175</TD><TD>Rahul</TD><TD>48000</TD></TR>
											<TR><TD>135</TD><TD>Saurv</TD><TD>10000</TD></TR>
											<TR><TD>225</TD><TD>Anil</TD><TD>45000</TD></TR>
								</CENTER>
							</BODY>
						</HTML>