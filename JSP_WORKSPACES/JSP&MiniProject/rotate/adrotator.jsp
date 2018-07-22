
<html
<jsp:useBean id="rotator" scope="session" class="myapp.Rotator" />


<% 
response.setIntHeader("Refresh",2);
//response.setHeader("page-enter","revealTrans(duration=1)");
rotator.nextAdvertisement();
%>
<body bgcolor="rgb(214,214,173)" scroll=no leftmargin=0 topmargin=0>
<a href="<jsp:getProperty name="rotator" property="link" />">
<img border=0 width=300 height=100 src="<jsp:getProperty name="rotator" property="image" />" alt="advertisement" /></a>
<br>
<b> it is rest of the page </b>
</body>
</html>