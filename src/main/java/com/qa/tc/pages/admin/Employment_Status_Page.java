package com.qa.tc.pages.admin;

import static com.qa.tc.constant.APIConstant.API_EndPoints.GET_EMPLOYEEMENT_DETAILS;
import static com.qa.tc.constant.APIConstant.API_EndPoints.PATCH_EMPLOYEEMENT_DETAILS;
import static com.qa.tc.constant.APIConstant.API_EndPoints.POST_EMPLOYEEMENT_DETAILS;
import static com.qa.tc.enums.HTTPMethodType.GET;
import static com.qa.tc.enums.HTTPMethodType.PATCH;
import static com.qa.tc.enums.HTTPMethodType.POST;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.qa.tc.base.BuildRestEngine;
import com.qa.tc.constant.APIConstant;
import com.qa.tc.enums.HTTPMethodType;
import com.qa.tc.pagespayloads.admin_payload.Employee_Add_Pojo;
import com.qa.tc.pagespayloads.admin_payload.Employee_Delete_Pojo;
import com.qa.tc.pagespayloads.admin_payload.Employee_Update_Pojo;
import com.qa.tc.utill.GenerateData;

import io.qameta.allure.Step;


public class Employment_Status_Page extends BuildRestEngine {

    private static Employment_Status_Page employment_Status_Page;
    private static GenerateData data;

    private Employment_Status_Page() {
        data = new GenerateData();
    }

    public static Employment_Status_Page getInstance() {
        if (employment_Status_Page == null)
            employment_Status_Page = new Employment_Status_Page();
        return employment_Status_Page;
    }

    @Step("Getting All Employee Details")
    public Employment_Status_Page get_All_Employee() {
        executeAPI(GET, GET_EMPLOYEEMENT_DETAILS);
        return this;
    }

    @Step("Deleting All Employee Details")
    public Employment_Status_Page deleteAllEmployee() {
        List<String> allEmp = executeAPI(GET, GET_EMPLOYEEMENT_DETAILS).jsonPath().getList("data.id");
        JSONObject request = Employee_Delete_Pojo.getDeletePayload(allEmp);
        setUpBody(request);
        executeAPI(HTTPMethodType.DELETE, APIConstant.API_EndPoints.DELETE_EMPLOYEEMENT_DETAILS);
        return this;
    }

    @Step("Getting Employee By Sorting {0}")
    public Employment_Status_Page get_Employee_By_sorting(Map<String, String> queryString) {
        setQueryParams(queryString);
        executeAPI(GET, GET_EMPLOYEEMENT_DETAILS);
        return this;
    }

    @Step("Adding New Employee To System")
    public Employment_Status_Page add_Employee_Details() {
        Employee_Add_Pojo requestBody = Employee_Add_Pojo.builder().name(data.getFirstName()).build();
        setUpBody(requestBody);
        executeAPI(POST, POST_EMPLOYEEMENT_DETAILS);
        return this;
    }

    @Step("Adding New Employee To System")
    public Employment_Status_Page add_Employee_Custom_Details(Employee_Add_Pojo requestBody) {
        setUpBody(requestBody);
        executeAPI(POST, POST_EMPLOYEEMENT_DETAILS);
        return this;
    }

    @Step("Updating existing employee details")
    public Employment_Status_Page update_First_EmployeeDetail() {
        executeAPI(GET, GET_EMPLOYEEMENT_DETAILS);
        String updateEmployeeEndPoint = String.format(PATCH_EMPLOYEEMENT_DETAILS, responseThread.get().jsonPath().getList("data.id").get(0));
        Employee_Update_Pojo request = Employee_Update_Pojo.builder().name(data.getFirstName()).build();
        setUpBody(request);
        executeAPI(PATCH, updateEmployeeEndPoint);
        return this;
    }

    @Step("Updating Employee Details Based Employee ID {0} and Requested Body {1}")
    public Employment_Status_Page update_Employee_Details(int empID, Employee_Update_Pojo requestBody) {
        setUpBody(requestBody);
        executeAPI(PATCH, String.format(PATCH_EMPLOYEEMENT_DETAILS, empID));
        return this;
    }
}
