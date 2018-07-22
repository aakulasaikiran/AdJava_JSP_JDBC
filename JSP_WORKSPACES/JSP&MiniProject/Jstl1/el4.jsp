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
<jsp:useBean id="cust1" class="com.Customer" scope="page"/> 
<jsp:useBean id="cust2" class="com.Customer" scope="session"/> 

<jsp:setProperty name="cust1" property="name" value="studone"/> 
<jsp:setProperty name="cust2" property="name" value="studtwo"/> 
<jsp:setProperty name="cust1" property="age" value="19"/> 
<jsp:setProperty name="cust2" property="age" value="21"/> 

<jsp:useBean id="cust3" class="com.Customer" scope="application"/> 

<jsp:setProperty name="cust3" property="name" value="studthree"/> 
<jsp:setProperty name="cust3" property="age" value="25"/> 

<c:out value="${applicationScope.cust3.name}" /><br>
<c:out value="${applicationScope.cust3.age}" /><br>

<c:out value="${sessionScope.cust2.name}" /><br>
<c:out value="${sessionScope.cust2.age}" /><br>

<c:out value="${pageScope.cust1.name}" /> <br>
<c:out value="${pageScope.cust1.age}" />

</HTML>
