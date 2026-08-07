package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest () {
		
		
		given().baseUri(getProperty("BASE_URI"))
		.header("Authorization", getToken(FD))
		.contentType("")
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		 .when().post("master")
		 .then().log().all()
		 .statusCode(200)
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .time(lessThan(2000L))
		 .body("$", hasKey("message"))
		 .body("$", hasKey("data"))
		 .body("data", hasKey("mst_oem"))
		 .body("data", hasKey("mst_model"))
		 .body("data.mst_oem.size()", greaterThan(0))
		 .body("data.mst_model.size()", greaterThan(0))
		 .body("data.mst_oem.id", everyItem(greaterThan(0)))
		 .body("data.mst_oem.name", everyItem(notNullValue()))
		  
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		}
	
	
	@Test
	public void invalidTokenMasterAPI () {
		
		given().baseUri(getProperty("BASE_URI"))
		.header("Authorization", "")
		.contentType("")
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		 .when().post("master")
		 .then().log().all()
		 .statusCode(401);
		
	}

}
