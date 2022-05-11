package com.qa.tc.extentKlovReport;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.tc.enums.Type_Of_Body;

import static com.qa.tc.enums.Type_Of_Body.Request;

public class ExtentLogger {

    public static void log_Message(String message) {
        ExtentManager.getCurrentTest().log(Status.PASS, message);
    }

    public static void log_JSONBody(Type_Of_Body typeOfBody, String body) {
        if (typeOfBody.equals(Request))
            ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Request Body", body));
        else
            ExtentManager.getCurrentTest().log(Status.PASS, MarkupHelper.createCodeBlock("Response Body", body));
    }

}
