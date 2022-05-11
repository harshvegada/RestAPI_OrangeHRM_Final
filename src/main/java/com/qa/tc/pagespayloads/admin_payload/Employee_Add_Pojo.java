package com.qa.tc.pagespayloads.admin_payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Employee_Add_Pojo {
    private String name;

	@Override
	public String toString() {
		return "Employee_Add_Pojo [name=" + name + "]";
	}

}