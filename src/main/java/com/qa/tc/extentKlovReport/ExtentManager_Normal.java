package com.qa.tc.extentKlovReport;

import static com.qa.tc.constant.FilePaths.EXTENT_REPORT_PATH;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager_Normal {
	private static final ExtentReports extentReports = new ExtentReports();
	private static ExtentTest extentTest;

	public static void initExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(EXTENT_REPORT_PATH);
		reporter.config().setReportName("Reporter : " + System.getProperty("user.name"));
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle("Extent Report : Orange HRM");
		reporter.config().setEncoding("UTF-8");
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Author", System.getProperty("user.name").toUpperCase());
		extentReports.setSystemInfo("Execution Env", "PROD");
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.attachReporter(reporter);
	}

	public static void flushReport() {
		extentReports.flush();
	}

	public static void log_pass(String message) {
		extentTest.pass(message);
	}

	public static void log_fail(String message) {
		extentTest.fail(message);
	}

	public static ExtentTest getCurrentTest() {
		return extentTest;
	}

	public static void createTestCase(ITestResult result) {
		extentReports.createTest(result.getMethod().getMethodName());
		extentTest.createNode(result.getMethod().getMethodName());
	}

	public static void testSuccess(ITestResult result) {
		extentTest.log(Status.PASS, MarkupHelper.createLabel("Test Case Executed Successfully", ExtentColor.GREEN));
	}

	public static void testSkip(ITestResult result) {
		extentTest.addScreenCaptureFromBase64String("BASE64", "IMAGE TITLE");
		extentTest.log(Status.SKIP, MarkupHelper.createLabel("Test Case Skipped", ExtentColor.YELLOW));
		extentTest.log(Status.SKIP, result.getThrowable());
	}

	public static void testFail(ITestResult result) {
		extentTest.addScreenCaptureFromBase64String("BASE64", "IMAGE TITLE");
		extentTest.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
		extentTest.log(Status.FAIL, result.getThrowable());
	}

}