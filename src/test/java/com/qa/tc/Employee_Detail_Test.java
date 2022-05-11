package com.qa.tc;

import static com.qa.tc.base.BuildRestEngine.responseThread;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.NO_CONTENT_FOUND;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.SUCCESSFULL_CREATED;
import static com.qa.tc.constant.APIConstant.HTTP_StatusCodes.SUCCESSFULL_OK;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.tc.customAnnotations.TestCaseCategory;
import com.qa.tc.enums.TestCaseCategoryType;
import com.qa.tc.testngListeners.ITestResultImpl;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(ITestResultImpl.class)
@Epic("Epic : Employee Detail GET,POST, DELETE & UPDATE Calls")
@Feature("Feature : Employee Details Page")
public class Employee_Detail_Test extends TestBase {

	@Test(description = "Description : All Employees should be in system")
	@Severity(SeverityLevel.NORMAL)
	@Story("APIAUTO-13490 : Verify All employees should be in System")
	@TestCaseCategory(author = "Maulik", category = TestCaseCategoryType.SECURITY)
	public void get_details_of_Employee() {
		employment_Status_Page.get_All_Employee();
		Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
		Assert.assertNotEquals(responseThread.get().jsonPath().getList("data.name").size(), 0,
				"All Employee Get Deleted from the System.");
	}

	@Test(description = "Description : Verify that by we are getting result based on query param")
	@Severity(SeverityLevel.CRITICAL)
	@Story("APIAUTO-17890 : Verify User able to sort employee details")
	@TestCaseCategory(author = "Harsh", category = TestCaseCategoryType.SECURITY)
	public void get_Sorted_Employee() {
		String limit = "10";
		Map<String, String> query = new HashMap<>();
		query.put("sortingOrder", "DESC");// ASC
		// query.put("limit", limit);
		employment_Status_Page.get_All_Employee();
		employment_Status_Page.get_Employee_By_sorting(query);
		Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
		Assert.assertEquals(responseThread.get().jsonPath().getList("data.name").size(), Integer.parseInt(limit),
				"Sorting not working on employee");
	}

	@Test(description = "Description : New Employee data inserted in System")
	@Severity(SeverityLevel.BLOCKER)
	@Story("APIAUTO-17000 : New Employee data inserted in System")
	@TestCaseCategory(author = "Rajesh", category = TestCaseCategoryType.PERFORMANCE)
	public void add_random_employee() {
		employment_Status_Page.add_Employee_Details();
		Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_CREATED);
		Assert.assertNotNull(responseThread.get().jsonPath().get("data.id").toString(), "Employee Not created!");
	}

	// @Test(description = "Description : Verify we able to delete all employee data
	// from the system")
	@Severity(SeverityLevel.BLOCKER)
	@Story("APIAUTO-15630 : Verify we able to delete all employee data from the system")
	@TestCaseCategory(author = "Kuldeep", category = TestCaseCategoryType.SANITY)
	public void deleteAllEmployee() {
		Assert.assertEquals(employment_Status_Page.deleteAllEmployee(), NO_CONTENT_FOUND);
	}

	@Test(description = "Verify we are able to update existing employee detail by Employee ID")
	@Severity(SeverityLevel.MINOR)
	@Story("APIAUTO-16000 : Verify we are able to update existing employee detail by Employee ID")
	@TestCaseCategory(author = "Kuldeep", category = TestCaseCategoryType.PERFORMANCE)
	public void update_Employee_Details() {
		employment_Status_Page.update_First_EmployeeDetail();
		Assert.assertEquals(responseThread.get().statusCode(), SUCCESSFULL_OK);
	}

}
