package com.qa.tc.constant;

import java.io.File;

public class FilePaths {


    public static final String BASE_DIRECTORY_FOR_CONFIG = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    //************//
    //Excel Files //
    //************//
    public static final String API_TEST_DATA_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String API_DETAILS_SHEET = "APIDetailsSheet.xlsx";

    //************//
    //Report Path //
    //************//
    public static final String ALLURE_ENV_PATH = System.getProperty("user.dir") + File.separator + "allure-results" + File.separator + "environment.properties";
    public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + File.separator + "extent-reports" + File.separator + "extent-report.html";
    public static final String ALLURE_JSON_FILES = System.getProperty("user.dir") + File.separator + "allure-results" + File.separator;
    //************//
    //Schema Files//
    //************//
    public static final String FILE_PATH_OF_SCHEMAS = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "jsonSchemas" + File.separator;
    public static final String GET_ALL_EMPLOYEE_DETAILS_SCHEMAS = "getAllEmployeeDetailSchema.json";


    //************//
    // Mock Files //
    //************//
    public static final String MOCK_RESPONSE_FILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "mockResponse.json";

}
