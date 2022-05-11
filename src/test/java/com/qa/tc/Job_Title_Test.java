package com.qa.tc;


import com.qa.tc.customAnnotations.TestCaseCategory;
import com.qa.tc.enums.TestCaseCategoryType;
import com.qa.tc.testngListeners.ITestResultImpl;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

import static com.qa.tc.base.BuildRestEngine.responseThread;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.SUCCESSFULL_CREATED;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.SUCCESSFULL_OK;
import static com.qa.tc.constant.FilePaths.FILE_PATH_OF_SCHEMAS;
import static com.qa.tc.constant.FilePaths.GET_ALL_EMPLOYEE_DETAILS_SCHEMAS;

@Listeners(ITestResultImpl.class)
@Epic("Epic : Job Title GET,POST, DELETE & UPDATE Calls")
@Feature("Feature : Job Title")
public class Job_Title_Test extends TestBase {


    @Test(description = "Description : Verify that we are able to create new Job Title")
    @Severity(SeverityLevel.CRITICAL)
    @Story("APIAUTO-890 : Verify that we are able to create new Job Title")
    @TestCaseCategory(author = "Rajesh", category = TestCaseCategoryType.SANITY)
    public void add_New_Job_Title() {
        job_Title_Page.add_Random_Job_Title();
        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_CREATED);
    }
//
//
//    //@Test(description = "Description : Verify we are able to delete all Job Title")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("APIAUTO-367 : Verify we are able to delete all Job Title")
//    @TestCaseCategory(author = "Maulik", category = TestCaseCategoryType.SANITY)
//    public void delete_Job_Title() {
//        job_Title_Page.delete_Job_Title();
//        Assert.assertEquals(responseThread.get().statusCode(), NO_CONTENT_FOUND);
//    }
//
//    @Test(description = "Description : Verify that we are able to get all job Title")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("APIAUTO-398 : Verify that we are able to get all job Title")
//    @TestCaseCategory(author = "Harsh", category = TestCaseCategoryType.SANITY)
//    public void get_All_Job_Title() {
//        job_Title_Page.get_All_Job_Title();
//        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
//    }
//
//
//    @Test(description = "Description : Verify that we are getting data based on query params")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("APIAUTO-983 : Verify that we are getting data based on query params")
//    @TestCaseCategory(author = "Maulik", category = TestCaseCategoryType.SANITY)
//    public void get_sorted_Job_Title() {
//        String limit = "10";
//        Map<String, String> query = new HashMap<>();
//        query.put("sortingOrder", "ASC");//DESC
//        query.put("limit", limit);
//        job_Title_Page.get_Job_Title_Based_On_Query(query);
//        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
//    }
//
//    @Test(description = "Description : Verify that we are able to Update First Job Title")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("APIAUTO-123 : Verify that we are able to Update First Job Title")
//    @TestCaseCategory(author = "Harsh", category = TestCaseCategoryType.SANITY)
//    public void update_first_Job_Title() {
//        job_Title_Page.update_First_JobTitle();
//        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
//    }

    @Test(description = "Description : Verify that schema for employee details")
    @Severity(SeverityLevel.CRITICAL)
    @Story("APIAUTO-789 : Verify that schema for employee details")
    @TestCaseCategory(author = "Harsh", category = TestCaseCategoryType.SMOKE)
    public void validateSchemaForGetEmployee() {
        job_Title_Page.get_All_Job_Title();
        Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
        job_Title_Page.validateSchema(new File(FILE_PATH_OF_SCHEMAS + GET_ALL_EMPLOYEE_DETAILS_SCHEMAS));
    }

}
