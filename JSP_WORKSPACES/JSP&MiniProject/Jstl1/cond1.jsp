<html>
<!--
  Shows how to use standard tags (JSTL), We can use implict variables
like param,header,cookie,pageScope,requestScope,sessionScope,applicationScope,pageContext
-->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
<c:set var="var1" value="Welcome" /> 

<c:if test="${!empty param.uname}">
    <c:out value="${var1}" />
    <c:out value="${param.uname}" />
</c:if> 
</BODY>
</HTML>
