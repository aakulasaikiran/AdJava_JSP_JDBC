<html>
<!--
  Shows how to use standard tags (JSTL)
-->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
<table> 
<c:forEach var="x" begin="1" end="10" step="1">
 <tr><td>
<c:out value="2 * ${x}  = "/></td> <td>
<c:out value="${2 * x}"/></td></tr> 
</c:forEach>
 </table> 


</BODY>
</HTML>
