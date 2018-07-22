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
<c:set var="start" value="10" scope="page" />
<c:set var="end" value="20" scope="page" />
list of numbers from <c:out value="${start}"/> to <c:out value="${end}"/> <br>
<c:forEach var="val" begin="${start}" end="${end}" step="1" >
	<c:out value="${val}" /> <br>
</c:forEach>
</HTML>
