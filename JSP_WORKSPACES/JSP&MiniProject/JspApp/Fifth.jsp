<%@ page import="beans.StudentBean"%>
<jsp:useBean id="stud" class="beans.StudentBean"></jsp:useBean>
<jsp:setProperty name="stud" property="sno" value="101"/>
<jsp:setProperty name="stud" property="sname" value="SATHYA"/>
<jsp:setProperty name="stud" property="result" value="Pass"/>

<html>

		<head><title>MY FIFTH JSP</title></head>

		 <body>
		 student number is<%=stud.getSno()%><br>
		 student name is <%=stud.getSname()%><br>
		 student result is <jsp:getProperty name="stud" property="result"/>
		 </body>

</html>
