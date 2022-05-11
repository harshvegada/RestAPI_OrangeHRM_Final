package com.qa.tc.constant;

public class API_EndPoints {



    public static final String QA_BASE_URI = "https://api-sandbox.orangehrm.com";
    public static final String PROD_BASE_URI = "https://api-sandbox.orangehrm.com";
    public static final String BASE_PATH = "/api";
    public static final String QA_AUTH_END_POINT = "/oauth/issueToken";
    public static final String PROD_AUTH_END_POINT = "/oauth/issueToken";


    // **************//
    // Job JobTitle //
    // **************//
    public static final String GET_JOB_TITLE = "/jobTitles";
    public static final String POST_JOB_TITLE = "/jobTitles";
    public static final String PATCH_JOB_TITLE = "/jobTitles/%s";
    public static final String DELETE_JOB_TITLE = "/jobTitles";

    public static final String PUT_JOB_TITLE = "/employees/%s/CustomFieldValues";

    public static void main(String[] args) {
		System.out.println("3734e162d71f109c39f29691f67066fa".length());
		System.out.println("103827018a8d2b2e472bdc4de531f668e32da980".length());
	}
}