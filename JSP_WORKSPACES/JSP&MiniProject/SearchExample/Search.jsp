<html>

	<script language = "javascript">
	
	function isHtml()
	{
		f.source.value = "Html";
		validate();	
	}

	function isExcel()
	{
		f.source.value = "Excel";
		validate();	
	}

	function validate()
	{
		if(f.category.selectedIndex == '0')
		{
			alert("You should select Category !!!");
			f.category.focus();
			return false;
		}
		else
		{
			f.submit();
			return true;
		}
	}

</script>

<body>
	<form name=f  action="./BookSearchServlet"  method="post">
		<center>
		<span style= "width=500;height=60;filter:shadow(color=pink,direction=135)">
		<font color=red size=5>Search for Books</font>
		<hr color=orange width=50%>
		</span></center>

		<table border=1 cellpadding=4 cellspacing=4 align=center 	bgcolor='lavender'>
		<tr>
			<th>Select Category</th>
			<th>
				<select name='category'>
					<option selected value=''>Select a value</option>
					<option value='java'>JAVA</option>
					<option value='.net'>.NET</option>
					<option value='jscript'>JavaScript</option>
				</select>
			</th>
		</tr>
		<tr>
			<th><input type='button' value='Html Output' onClick='isHtml()'></th>
			<th><input type='button' value='Excel Output' onClick='isExcel()'></th>
		</tr>
		</table>

		<input type='text' name='source' readonly style='visibility:hidden'>
	</form>
	</body>
</html>