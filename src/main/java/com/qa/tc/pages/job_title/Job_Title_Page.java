package com.qa.tc.pages.job_title;

import static com.qa.tc.constant.APIConstant.API_EndPoints.DELETE_JOB_TITLE;
import static com.qa.tc.constant.APIConstant.API_EndPoints.GET_JOB_TITLE;
import static com.qa.tc.constant.APIConstant.API_EndPoints.PATCH_JOB_TITLE;
import static com.qa.tc.constant.APIConstant.API_EndPoints.POST_JOB_TITLE;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.SUCCESSFULL_OK;
import static com.qa.tc.enums.HTTPMethodType.DELETE;
import static com.qa.tc.enums.HTTPMethodType.GET;
import static com.qa.tc.enums.HTTPMethodType.PATCH;
import static com.qa.tc.enums.HTTPMethodType.POST;

import java.io.File;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.tc.base.BuildRestEngine;
import com.qa.tc.extentKlovReport.ExtentManager;
import com.qa.tc.pagespayloads.job_title_payload.Job_Title_Add_Pojo;
import com.qa.tc.pagespayloads.job_title_payload.Job_Title_Delete_Pojo;
import com.qa.tc.pagespayloads.job_title_payload.Job_Title_Update_Pojo;
import com.qa.tc.utill.GenerateData;

import io.restassured.response.Response;

public class Job_Title_Page extends BuildRestEngine {

    private static Job_Title_Page job_Title_Page;
    private GenerateData data;

    private Job_Title_Page() {
        data = new GenerateData();
    }

    public static Job_Title_Page getInstance() {
        if (job_Title_Page == null)
            job_Title_Page = new Job_Title_Page();
        return job_Title_Page;
    }

    public Job_Title_Page add_Random_Job_Title() {
        Job_Title_Add_Pojo request = Job_Title_Add_Pojo.builder()
                .jobTitleName(data.getJobTitleName())
                .jobDescription(data.getJobDescritpion())
                .note(data.getRandomNotes())
                .currentJobSpecification(data.getJobSpecification())
                .jobSpecification(Job_Title_Add_Pojo.JobSpecification.builder().base64("ClVQREFURSBgb2hybV9tZW51X2l0ZW1gIFNFVCBg")
                        .filename("AccompanistAttachment.pdf")
                        .filesize("1756")
                        .filetype("application/pdf").build())
                .build();
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createJsonCodeBlock(request));
        setUpBody(request);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createLabel("Request Settle in API Body", ExtentColor.GREEN));
        executeAPI(POST, POST_JOB_TITLE);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Code", String.valueOf(responseThread.get().statusCode())));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Of Added New job Title", responseThread.get().asPrettyString()));
        return this;
    }

    public Job_Title_Page add_Employee_Title(Job_Title_Add_Pojo requestBody){
        setUpBody(requestBody);
        executeAPI(POST,POST_JOB_TITLE);
        return this;
    }

    public Job_Title_Page delete_Job_Title() {
        JSONObject request = Job_Title_Delete_Pojo.getDeletePayload(executeAPI(GET, GET_JOB_TITLE).jsonPath().getList("data.id"));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createJsonCodeBlock(request));
        setUpBody(request);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createLabel("Request Settle in API Body", ExtentColor.GREEN));
        executeAPI(DELETE, DELETE_JOB_TITLE);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Code", String.valueOf(responseThread.get().statusCode())));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Of Deleted Job Title", responseThread.get().asPrettyString()));
        return this;
    }

    public Job_Title_Page get_All_Job_Title() {
        executeAPI(GET, GET_JOB_TITLE);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Code", String.valueOf(responseThread.get().statusCode())));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Of All Available Job Title", responseThread.get().asPrettyString()));
        return this;
    }

    public Job_Title_Page get_Job_Title_Based_On_Query(Map<String, String> query) {
        setQueryParams(query);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createLabel("Query Param Settle in API", ExtentColor.GREEN));
        executeAPI(GET, GET_JOB_TITLE);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Code", String.valueOf(responseThread.get().statusCode())));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Of Job Title", responseThread.get().asPrettyString()));
        return this;
    }

    public Job_Title_Page update_First_JobTitle() {
        Job_Title_Add_Pojo request = Job_Title_Add_Pojo.builder()
                .jobTitleName(data.getJobTitleName())
                .jobDescription(data.getJobDescritpion())
                .note(data.getRandomNotes())
                .currentJobSpecification(data.getJobSpecification())
                .jobSpecification(Job_Title_Add_Pojo.JobSpecification.builder().base64("ClVQREFURSBgb2hybV9tZW51X2l0ZW1gIFNFVCBg")
                        .filename("AccompanistAttachment.pdf")
                        .filesize("1756")
                        .filetype("application/pdf").build())
                .build();
        setUpBody(request);
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createJsonCodeBlock(request));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createLabel("Request Settle in API Body", ExtentColor.GREEN));
        Response response = executeAPI(PATCH, String.format(PATCH_JOB_TITLE, executeAPI(GET, GET_JOB_TITLE).jsonPath().getList("data.id").get(0)));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Code", String.valueOf(response.statusCode())));
        ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response of updated job Title", response.asPrettyString()));
        return this;
    }

    public Job_Title_Page update_Job_Title_Details(Job_Title_Update_Pojo request, int employeeID) {
        setUpBody(request);
        executeAPI(PATCH, String.format(PATCH_JOB_TITLE, employeeID));
        Assert.assertEquals(responseThread.get().statusCode(),SUCCESSFULL_OK);
        return this;
    }

    public void validateSchema(File file){
        super.validateReponseSchema(file);
    }
}
