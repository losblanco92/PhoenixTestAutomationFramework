package com.api.tests;

import static com.api.constants.Role.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse () {
		
		
		given().spec(requestSpecWithAuth(FD))
		.when().get("dashboard/count")
		.then().spec(responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .body("data.size()", equalTo(3))
		 .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		 .body("data.label", everyItem(not(blankOrNullString())))
		 .body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
		 .body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"));
		}
	
	
	@Test
	public void countAPIMissingAuthToken() {
		given().spec(requestSpec())
		 .log().method()
		.when().get("dashboard/count")
		.then().spec(responseSpec_TXT(401));
	}

}
