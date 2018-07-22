<%--
Error Page
--%>

<%@page isErrorPage="true"%>

Output From Error.jsp
<br>
Error : 
<%=
exception.toString()
%>
<br>
Desc
<%=
exception.getMessage()
%>