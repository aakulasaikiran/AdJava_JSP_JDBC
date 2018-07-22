<html>
<title> Plugin example </title>
<body bgcolor="white">

<jsp:plugin type="applet" code="Animation.class" codebase="/jsp-plug1" jreversion="1.2" width="500" height="300" >
    <jsp:fallback>
        Plugin tag OBJECT or EMBED not supported by browser.
    </jsp:fallback>
</jsp:plugin>
<p>
<h4>
<font color=red> 
The above applet is loaded using the Java Plugin from a jsp page using the
plugin tag.
</font>
</h4>
</body>
</html>
