package com.qa.tc;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

public class JSONPathUnderstanding {

	
	public static void main(String[] args) {
		Response r = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
		.baseUri("https://mocki.io/v1/d369947b-f5dc-4db5-8de8-bb1692e25fe3")
		.when()
		.get()
		.then()
		.extract()
		.response();

		JsonPath path = new JsonPath(r.asString());
		List<String> names = path.getList("data.name");
		System.out.println(names);
		Collections.sort(names);
		System.out.println(names);
	}
}
