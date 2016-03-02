package dessert.rvo.employee;

import dessert.rvo.ResultVO;

public class EmployeeAddResultVO extends ResultVO{
     private String name;
     private String password;
     
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
     
}
