package com.qa.tc.extentKlovReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.tc.customAnnotations.TestCaseCategory;
import com.qa.tc.enums.TestCaseCategoryType;
import org.testng.ITestResult;

import static com.qa.tc.constant.FilePaths.EXTENT_REPORT_PATH;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

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

    public static ExtentTest getCurrentTest() {
        return extentTest.get();
    }

    public static void createTestCase(ITestResult result) {
        extentTest.set(extentReports.createTest(result.getMethod().getMethodName()));
        extentTest.set(extentTest.get().createNode(result.getMethod().getMethodName()));
        extentTest.set(extentTest.get().assignAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseCategory.class).author()));
        TestCaseCategoryType[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseCategory.class).category();
        addCategory(result, categories);
    }

    private static void addCategory(ITestResult result, TestCaseCategoryType[] categories) {
        for (TestCaseCategoryType category : categories) {
            extentTest.get().assignCategory(category.toString());
        }
    }

    public static void testSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, MarkupHelper.createLabel("Test Case Executed Successfully", ExtentColor.GREEN));
    }

    public static void testSkip(ITestResult result) {
		extentTest.get().addScreenCaptureFromBase64String("BASE64", "IMAGE TITLE");
		extentTest.get().log(Status.SKIP, MarkupHelper.createLabel("Test Case Skipped", ExtentColor.YELLOW));
		extentTest.get().log(Status.SKIP, result.getThrowable());
    }

    public static void testFail(ITestResult result) {
//        extentTest.get().addScreenCaptureFromBase64String("BASE64", "IMAGE TITLE");
        extentTest.get().log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
        extentTest.get().log(Status.FAIL, result.getThrowable());
    }


}