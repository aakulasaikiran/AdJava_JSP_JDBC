<html>
<script language = 'javascript'>
function isValid()
{
	if(f.stu_id.value == "")
	{
		alert(f.stu_id.name + "  " + "Field Missed");
		f.stu_id.focus();
		return false;
	}
	if(isNaN(f.stu_id.value))
	{
		alert("Stu_ID should be numeric value");
		f.stu_id.focus();
		return false;
	}
	return true;
}
</script>
<body>
<form name = f action = './delete' method = 'post' onSubmit ='return isValid()'>
<table border = 1 cellpadding = 7 cellspacing = 7 align = center>
<caption><i><font size = 4><u>Delete Records</caption>
<tr>
<th>Student ID<th><input type = "text" name = "stu_id"></tr>
<tr>
<th>click<th><input type = "submit" value = "   DELETE   "></tr>
</table>
</form>
</body>
</html>