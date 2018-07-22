function isValid(frm)
{
	var sid = frm.stu_id;
	var snm = frm.stu_name;
	var sadd = frm.stu_add;
	if(check(sid) && isNumber(sid) && check(snm) && check(sadd))
	{
		return true;
	}
	else
	{
		return false;
	}
}
function check(cmd)
{
	if(cmd.value == "")
	{
		alert(cmd.name + "  " + "Field Missed");
		cmd.focus();
		return false;
	}
	return true;
}
function isNumber(cmd)
{	
	if(isNaN(cmd.value))
	{
		alert("Stu_ID should be numeric value");
		cmd.focus();
		return false;
	}
	return true;
}