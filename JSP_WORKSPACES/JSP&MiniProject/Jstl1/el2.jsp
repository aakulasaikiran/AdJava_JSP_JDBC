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
<jsp:useBean id="cust" class="com.Customer" scope="session"/> 
<%
	Address a = new Address();
	a.setCity("Hyderabad");
	a.setEmail("stud@email.com");
	cust.setName("StudentName");
	cust.setAddress(a);
%>
Customer name =====<c:out value="${cust.name}" /> <br><br>
City ==============<c:out value="${cust.address.city}" /> <br><br>
Email =============<c:out value="${cust.address.email}" />

</HTML>
