<html>
<!--
we can use jndi resource name or different params like driver,jdbcurl,uname,pwd
to set up data source.
 -->
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 

<sql:setDataSource var="ds" driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:satya" user="scott" password="tiger" />
<sql:query var="res_set" dataSource="${ds}"  sql="select * FROM emp" />

<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
<c:forEach var="row" items="${res_set.rows}"> 
<c:out value="${row.ename}" />
<c:out value="${row.empno}" />

<br><br>
</c:forEach> 

</BODY>
</HTML>
