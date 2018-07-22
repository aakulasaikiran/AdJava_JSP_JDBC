<%@page import="java.util.*" errorPage="Error.jsp" session="true"%>

<html>
 <body>
  <table>
   <tr>
    <th>Name</th>
   <th>Value</th>
 </tr>

<%
Hashtable ht=(Hashtable)session.getAttribute("Ht");

Enumeration names=ht.keys();

while (names.hasMoreElements())
{
String name= (String) names.nextElement();
 
String value= (String) ht.get(name);
%>
<tr>
  <td><%=name%></td>
 <td><%=value%></td>
</tr>
<%
}//while
%>
   </table>
 </body>
</html>