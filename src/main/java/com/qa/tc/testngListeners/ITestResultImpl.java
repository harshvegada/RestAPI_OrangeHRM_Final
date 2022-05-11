package com.qa.tc.testngListeners;

import com.qa.tc.base.BuildRestEngine;
import com.qa.tc.extentKlovReport.ExtentManager;
import io.restassured.http.ContentType;

import org.apache.log4j.PropertyConfigurator;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.util.Strings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.qa.tc.constant.API_EndPoints.*;
import static io.restassured.RestAssured.given;

public class ITestResultImpl extends BuildRestEngine implements ITestListener, IAnnotationTransformer {

	static String environment;
	static String defaultEnvironment = "qa";

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(ReRunTestCase.class);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentManager.createTestCase(result);
		buildRequest(environment);
		setAuthTokenInRequest(getAuthToken());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		responseThread.remove();
		ExtentManager.testSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		responseThread.remove();
		ExtentManager.testFail(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		responseThread.remove();
		ExtentManager.testSkip(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		environment = Strings.isNullOrEmpty(System.getProperty("environment")) ? defaultEnvironment
				: System.getProperty("environment");
		ExtentManager.initExtentReports();
		PropertyConfigurator.configure("log4j");
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}

	public static void main(String[] args) {
		System.out.println(getAuthToken());
	}

	public static String getAuthToken() {
		Map<String, String> formParam = new HashMap<>();
		formParam.put("client_id", "api-client");
		formParam.put("client_secret", "942d36a36d6bf422a36f5871f905b6e5");
		formParam.put("grant_type", "admin123");
		formParam.put("username", "admin");
		formParam.put("password", "admin123");
		Map<String, String> header = new HashMap<>();
		header.put("Content-Type", "application/x-www-form-urlencoded");

//		String BASE_URI = environment.equals("qa") ? QA_BASE_URI : PROD_BASE_URI;
		String BASE_URI = PROD_BASE_URI;
//		String AUTH_ENDPOINT = environment.equals("qa") ? QA_AUTH_END_POINT : PROD_AUTH_END_POINT;
		String AUTH_ENDPOINT = PROD_AUTH_END_POINT;
		return given().baseUri(BASE_URI).accept(ContentType.JSON).formParams(formParam).headers(header).when()
				.post(AUTH_ENDPOINT).then().extract().response().asString();
//				.jsonPath().getString("access_token");
	}
}
