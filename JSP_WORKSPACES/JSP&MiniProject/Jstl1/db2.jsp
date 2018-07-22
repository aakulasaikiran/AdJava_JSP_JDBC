<html>
<!--
we can use jndi resource name or different params like driver,jdbcurl,uname,pwd
to set up data source.
 -->
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 

<sql:setDataSource var="ds" driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:satya" user="scott" password="tiger" />

<HTML>
<HEAD>
<TITLE>Example on JSTL Coretags</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
<sql:transaction dataSource="${ds}">
 <sql:update> 
	update emp SET comm =comm + ? WHERE empno = ? 
	<sql:param value="1000"/>
	<sql:param value="7499"/>
 </sql:update> 
</sql:transaction> 
Record updated.

</BODY>
</HTML>
