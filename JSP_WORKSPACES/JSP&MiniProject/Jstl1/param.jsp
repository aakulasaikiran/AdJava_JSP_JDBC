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
First name: <c:out value="${param.fname}" />
<c:out value="Last name: ${param.lname}" />
<br><br>
<c:out value="user agent ${header['User-Agent']}"/>

</BODY>
</HTML>
