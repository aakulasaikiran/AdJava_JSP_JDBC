<html>
<!--
  Adding java code directly is not recomended. used here for demo purpose only.
we can iterate thr'u the list for all implementations of enumeration
(List,LinkedList,ArrayList,Vector,stack,set) and
implementations of java.util.Map (HashMap,HashTable,Properties,Attributes).

-->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<%@ page import="com.*" %> 
<%@ page import="java.util.*" %> 
<jsp:useBean id="lst" class="java.util.Vector" scope="page"/> 
<%
	com.Customer a;
	a = new com.Customer();
	a.setName("sone");
	lst.add(a);
	a = new com.Customer();
	a.setName("stwo");
	lst.add(a);
	a = new com.Customer();
	a.setName("sthree");
	lst.add(a);
%>
<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
</BODY>
List of students<br><br>
<c:forEach var="cus" items="${lst}" >
	<c:out value="${cus.name}" /> <br>
</c:forEach>
</HTML>
