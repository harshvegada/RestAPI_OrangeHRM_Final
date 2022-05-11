package com.qa.tc.pagespayloads.admin_payload;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Employee_Get_Pojo {
    private int limit;
    private String sortingOrder;
    
}