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
List of parameters <br><br>
<c:forEach var="p" items="${paramValues}">
	parameter name = <c:out value="${p.key}" /><br>
	values =
	<c:forEach var="pv" items="${p.value}">
		<c:out value="${pv}" /><br>
	</c:forEach>
</c:forEach>
</HTML>
