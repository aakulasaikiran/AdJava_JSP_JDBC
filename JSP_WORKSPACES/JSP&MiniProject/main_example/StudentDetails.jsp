<% 
	String res =(String)request.getAttribute("msg");
	String id = (String)request.getAttribute("no");
	String name = (String)request.getAttribute("name");
	String addr = (String)request.getAttribute("address");
%>

<html>
	<script language = 'javascript'>

	function isInsert()
	{
		f.setVal.value = "insert";
		check();
	}
	function isDelete()
	{
		f.setVal.value = "delete";
		check1();
	}
	function isUpdate()
	{
		f.setVal.value = "update";
		check();
	}
	function isDisplay()
	{
		f.setVal.value = "display";
		check1();
	}

	function check()
	{	
		if(f.stu_id.value == "")
		{
			alert("Please provide Student ID");
			f.stu_id.focus();
		}
		else if(isNaN(f.stu_id.value))
		{
			alert("Student ID should be numeric");
			f.stu_id.value = "";
			f.stu_id.focus();
		}
		else if(f.stu_name.value == "")
		{
			alert("Please provide Student Name");
			f.stu_name.focus();
		}
		else if(f.stu_add.value == "")
		{
			alert("Please provide Student Address");
			f.stu_add.focus();
		}
		else
			f.submit();
	} 

	function check1()
	{
		if(f.stu_id.value == "")
		{
			alert("Please provide Student ID");
			f.stu_id.focus();
		}
		else if(isNaN(f.stu_id.value))
		{
			alert("Student ID should be numeric");
			f.stu_id.value = "";
			f.stu_id.focus();
		}
		else
			f.submit();
	}

	function disp1_output()
	{
		var i = <%=id%>;
		var n ='<%=name%>';
		var a ='<%=addr%>';
		f.stu_id.value = i;	
		f.stu_name.value=n;
		f.stu_add.value = a;
	}

	function isReloading()
	{	
		<% 
	    if(res != null)
		{
			if(res.equals("1"))
				out.println("alert('Information stored successfully')");
			else if(res.equals("2"))
				out.println("alert('Given Student ID already exists')");
			else if(res.equals("3"))
				out.println("alert('Information deleted successfully')");
			else if(res.equals("4"))
				out.println("alert('Given Student ID is Invalid')");
			else if(res.equals("5"))
				out.println("alert('Information updated successfully')");
			else if(res.equals("6"))
			{
				out.println("disp1_output()");
			}
			else if(res.equals("7"))
				out.println("alert('Unknown problem occured.')");
		  }
		%>
	}
	
	</script>

<body onLoad='isReloading()'>
	<form name=f action='./studentdemo' method='post'>
	
	<center>
	<span style= "width=500;height=60;filter:shadow(color=blue,direction=135)">
	
	<font color=lavender size=5>SATHYA TECHNOLOGIES</font>
	<hr color=pink width=100%>
	</span></center>

	<table border=1 cellpadding=7 cellspacing=7 align=center>

	<caption>
		<font size=6><i><b><u>Student Table</i></b></u></font>
	</caption>

	<tr>
		<th>Student ID</th>
		<th><input type="text" name="stu_id"></th>
	</tr>
	<tr>
		<th>Student Name</th>
		<th><input type="text" name="stu_name"></th>
	</tr>
	<tr>
		<th>Student Address</th>
		<th><input type="text" name="stu_add"></th>
	</tr>
	<tr>
		<th colspan=4>
		<input type='button' value='Insert' onClick='isInsert()'>
		<input type='button' value='Delete' onClick='isDelete()'>
		<input type='button' value='Update' onClick='isUpdate()'>
		<input type='button' value='Display' onClick='isDisplay()'>
	</tr>
	</table>

	<input type='text' name='setVal' readonly 				
					style='visibility:hidden'>
	</form>
	</body>
</html>