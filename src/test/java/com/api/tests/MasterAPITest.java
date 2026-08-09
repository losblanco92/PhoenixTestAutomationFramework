package com.api.tests;

import static com.api.constants.Role.*;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest () {
		
		
		given().spec(requestSpecWithAuth(FD))
		 .when().post("master")
		 .then().spec(responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .body("$", hasKey("message"))
		 .body("$", hasKey("data"))
		 .body("data", hasKey("mst_oem"))
		 .body("data", hasKey("mst_model"))
		 .body("data.mst_oem.size()", greaterThan(0))
		 .body("data.mst_model.size()", greaterThan(0))
		 .body("data.mst_oem.id", everyItem(greaterThan(0)))
		 .body("data.mst_oem.name", everyItem(notNullValue()))
		  
		 .body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		}
	
	
	@Test
	public void invalidTokenMasterAPI () {
		
		given().spec(requestSpec())
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		 .when().post("master")
		 .then().spec(responseSpec_TXT(401));
		
	}

}
