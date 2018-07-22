<script language="JavaScript">

	function startup()
	{
				<% 
						String res=(String)request.getAttribute("msg");

						if(res != null)
						{
							out.print("alert(' " + res + " ')");
							request.removeAttribute("msg");
						}

					%>
	}

	function  validate()
	{
		if(loginForm.uname.value == "")
		{
			alert("Please  provide  UserName");
			loginForm.uname.focus();
			return false;
		}

		if(loginForm.pwd.value == "")
		{
			alert("Please provide  Password");
			loginForm.pwd.focus();
			return  false;
		}

		loginForm.submit();
		return  true;
	}

</script>

<html>
	<head>
			<title>Login  Screen</title>
	</head>

	<body  onload="startup()" bgcolor=#ffeeda>
		<form name="loginForm"  action="controller"  method="post" >
		
			<center><h2>LOGIN PAGE</h2>
			<br><br><br>
			USER NAME <input type="text" name="uname"  size="20">
		  <br>
			 PASSWORD <input type="password" name="pwd" size="20">
		  <br><br>
		  <input type="button" value="LOGIN" onclick="validate()">
		  </center>
		
		  <input type="hidden" name="source" value="login">

		</form>
	</body>
</html>