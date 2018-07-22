package beans;

public class Employee implements java.io.Serializable
	{
			int id;
			String name;
			int deptno;
			double salary;
			String desg;
			String dname;

			public Employee()
			{}
			public Employee(int id,
									String name,
									double salary,
									String desg,
									String dname)
			{
				this.id=id;
				this.deptno=deptno;
				this.salary=salary;
				this.desg=desg;
				this.dname=dname;
			}
			public int getId()
			{
				return id;
			}
			public void setId(int id)
			{
				this.id=id;
			}

			public String getName()
			{
				return name;
			}
			public void setName(String name)
			{
				this.name=name;
			}

			public int getDeptno()
			{
				return deptno;
			}
			public void setDeptno(int deptno)
			{
				this.deptno=deptno;
			}

			public double  getSalary()
			{
				return salary;
			}
			public void setSalary(double salary)
			{
				this.salary=salary;
			}

			public String getDesg()
			{
				return desg;
			}
			public void setDesg(String desg)
			{
				this.desg=desg;
			}
			public String getDname()
			{
				return dname;
			}
			public void setDname(String dname)
			{
				this.dname=dname;
			}
}





