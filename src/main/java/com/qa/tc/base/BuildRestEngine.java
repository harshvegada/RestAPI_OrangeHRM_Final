package com.qa.tc.base;

import com.qa.tc.enums.HTTPMethodType;
import com.qa.tc.enums.Type_Of_Body;
import com.qa.tc.extentKlovReport.ExtentLogger;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static com.qa.tc.constant.API_EndPoints.PROD_BASE_URI;
import static com.qa.tc.constant.API_EndPoints.QA_BASE_URI;


public class BuildRestEngine {

    protected static ThreadLocal<RequestSpecBuilder> requestSpecBuilderThread = new ThreadLocal<>();
    public static ThreadLocal<ValidatableResponse> validateResponseThread = new ThreadLocal<>();
    public static ThreadLocal<Response> responseThread = new ThreadLocal<>();

    @Step("Setting up token {0} in request")
    protected void setAuthTokenInRequest(String token) {
        ExtentLogger.log_Message(token + " token set-up in headers");
        requestSpecBuilderThread.get().addHeader("Authorization", "Bearer " + token);
    }

    @Step("Setting up Cutom Base URI {0} and setup Content type")
    protected void buildCutomRequest(String environment) {
        ExtentLogger.log_Message(environment + " Environment set-up for execution");
        requestSpecBuilderThread.set(new RequestSpecBuilder());
        switch (environment.toLowerCase()) {
            case "qa":
                requestSpecBuilderThread.get().setBaseUri(QA_BASE_URI);
                break;
            case "prod":
                requestSpecBuilderThread.get().setBaseUri(PROD_BASE_URI);
        }
        requestSpecBuilderThread.get().addHeader("Accept", "application/json");
        requestSpecBuilderThread.get().addHeader("Content-Type", "application/json");
    }

    @Step("Setting up Base URI and setup Content type")
    protected void buildRequest(String environment) {
        ExtentLogger.log_Message(environment + " Environment set-up for execution");
        requestSpecBuilderThread.set(new RequestSpecBuilder());
        switch (environment.toLowerCase()) {
            case "qa":
                requestSpecBuilderThread.get().setBaseUri(QA_BASE_URI);
                break;
            case "prod":
                requestSpecBuilderThread.get().setBaseUri(PROD_BASE_URI);
        }
        requestSpecBuilderThread.get().addHeader("Accept", "application/json");
        requestSpecBuilderThread.get().addHeader("Content-Type", "application/json");
        ExtentLogger.log_Message("Headers set-up in API");
    }

    @Step("Setting up headers {0}")
    protected void setRequestHeaders(Map<String, String> headers) {
        requestSpecBuilderThread.get().addHeaders(headers);
        ExtentLogger.log_Message(headers + " Headers set-up in API");
    }

    @Step("Setting up form parameters{0}")
    protected void setFormParams(Map<String, String> formParams) {
        requestSpecBuilderThread.get().addFormParams(formParams);
        ExtentLogger.log_Message(formParams + " Form parameters set-up in API");
    }

    @Step("Setting up query String {0}")
    protected void setQueryString(Map<String, String> queryString) {
        requestSpecBuilderThread.get().addQueryParams(queryString);
        ExtentLogger.log_Message(queryString + " Query set-up in API");
    }

    @Step("Setting up path {0}")
    protected void setPathParams(Map<String, String> pathParams) {
        requestSpecBuilderThread.get().addPathParams(pathParams);
        ExtentLogger.log_Message(pathParams + " path parameter set-up in API");
    }

    @Step("Setting up query {0}")
    protected void setQueryParams(Map<String, String> queryParams) {
        requestSpecBuilderThread.get().addQueryParams(queryParams);
        ExtentLogger.log_Message(queryParams + " Query set-up in API");
    }

    @Step("Getting value for {0} from JSON Path")
    protected String getJsonPathData(Response response, String path) {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(path);
    }

    @Step("Setting up body {0}")
    protected void setUpBody(Object requestBody) {
        requestSpecBuilderThread.get().setBody(requestBody);
        ExtentLogger.log_JSONBody(Type_Of_Body.Request, String.valueOf(requestBody));
    }

    @Step("Executing Method {0} with endpoint {1}")
    protected Response executeAPI(HTTPMethodType methodType, String apiEndPoints) {
        ExtentLogger.log_Message("API About to execute.");
        ExtentLogger.log_Message("API Type : " + methodType);
        ExtentLogger.log_Message("API End point : " + apiEndPoints);
        RequestSpecification request = RestAssured.given();
        request.spec(requestSpecBuilderThread.get().build());
        switch (methodType) {
            case GET:
                validateResponseThread.set(request.when().get(apiEndPoints).then());
                responseThread.set(validateResponseThread.get().extract().response());
                ExtentLogger.log_Message("Response Code : " + responseThread.get().statusCode());
                ExtentLogger.log_JSONBody(Type_Of_Body.Response, responseThread.get().asPrettyString());
                return validateResponseThread.get().extract().response();
            case POST:
                validateResponseThread.set(request.when().post(apiEndPoints).then());
                responseThread.set(validateResponseThread.get().extract().response());
                ExtentLogger.log_Message("Response Code : " + responseThread.get().statusCode());
                ExtentLogger.log_JSONBody(Type_Of_Body.Response, responseThread.get().asPrettyString());
                validateResponseThread.get().extract().response();
                return validateResponseThread.get().extract().response();
            case PATCH:
                validateResponseThread.set(request.when().patch(apiEndPoints).then());
                responseThread.set(validateResponseThread.get().extract().response());
                ExtentLogger.log_Message("Response Code : " + responseThread.get().statusCode());
                ExtentLogger.log_JSONBody(Type_Of_Body.Response, responseThread.get().asPrettyString());
                return validateResponseThread.get().extract().response();
            case DELETE:
                validateResponseThread.set(request.when().delete(apiEndPoints).then());
                responseThread.set(validateResponseThread.get().extract().response());
                ExtentLogger.log_Message("Response Code : " + responseThread.get().statusCode());
                ExtentLogger.log_JSONBody(Type_Of_Body.Response, responseThread.get().asPrettyString());
                return validateResponseThread.get().extract().response();
            default:
                throw new IllegalStateException("Method type not added in the HTTPMethodType enum..." + methodType);
        }
    }

    @Step("Validating {0} schema with response")
    protected void validateReponseSchema(File fileName) {
        validateResponseThread.get().body(JsonSchemaValidator.matchesJsonSchema(fileName));
        ExtentLogger.log_Message("Schema Validation completed.");
    }
}
