<html>
<!--
  Adding java code directly is not recomended. used here for demo purpose only.
-->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<%@ page import="com.*" %> 
<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
</BODY>
1)List of students<br><br>
<c:set var="str" value="studone.studtwo.studthree.studfour" scope="page" />
<c:forTokens var="sname" items="${str}" delims="." >
	<c:out value="${sname}" /> <br>
</c:forTokens>

2)List of students<br><br>
<c:set var="str" value="studone,studtwo,studthree,studfour" scope="page" />
<c:forTokens var="sname" items="${str}" delims="," >
	<c:out value="${sname}" /> <br>
</c:forTokens>

3)List of students<br><br>
<c:set var="str" value="studone,studtwo,studthree,studfour" scope="page" />
<c:forEach var="sname" items="${str}" >
	<c:out value="${sname}" /> <br>
</c:forEach>
</HTML>
