package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse () {
		
		Header authorizationHeader = new Header("Authorization", getToken(FD));
		
		
		given().baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header(authorizationHeader)
		.log().uri()
		.log().headers()
		.log().body()
		 .log().method()
		.when().get("dashboard/count")
		.then().log().all().statusCode(200)
		.time(lessThan(2000L))
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .body("data.size()", equalTo(3))
		 .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		 .body("data.label", everyItem(not(blankOrNullString())))
		 .body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"));
		}
	
	
	@Test
	public void countAPIMissingAuthToken() {
		given().baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.log().uri()
		.log().headers()
		.log().body()
		 .log().method()
		.when().get("dashboard/count")
		.then().log().all().statusCode(401);
	}

}
