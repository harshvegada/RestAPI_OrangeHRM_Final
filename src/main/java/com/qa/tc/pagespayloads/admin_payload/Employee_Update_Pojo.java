package com.qa.tc.pagespayloads.admin_payload;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee_Update_Pojo {
    private String name;

	@Override
	public String toString() {
		return "Employee_Update_Pojo [name=" + name + "]";
	}

}
