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
<jsp:useBean id="cust1" class="com.Customer" scope="session"/> 
<jsp:useBean id="cust2" class="com.Customer" scope="session"/>
 
<jsp:setProperty name="cust1" property="name" value="studone"/> 
<jsp:setProperty name="cust2" property="name" value="studtwo"/> 
<jsp:setProperty name="cust1" property="age" value="19"/> 
<jsp:setProperty name="cust2" property="age" value="21"/> 

<c:out value="${cust1.age}" />
<c:out value="${cust1.name}" />
<br>
<c:if test="${cust1.age < 20 }"> 
	Age of cust1 less than 20<br>
</c:if>

<c:if test="${cust1.name == 'studone'}"> 
	Name of cust1 is studone <br>
</c:if>
<c:if test="${cust1.name == 'studone' && cust1.age <20}"> 
	Name of cust1 is studone and age less than 20
</c:if>
</HTML>
