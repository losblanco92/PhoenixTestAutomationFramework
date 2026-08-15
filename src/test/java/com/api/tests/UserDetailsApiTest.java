package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.testng.annotations.Test;

public class UserDetailsApiTest {
	
	@Test(description = "Verify User Details API is shwoing correct response", groups = {"api", "regression", "smoke"})
	public void userDetailsApi () throws IOException {
		
		given().spec(requestSpecWithAuth(FD))
		.when().get("userdetails")
		.then().spec(responseSpec_OK()).and()
		.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	   
		
		
		
	}

}
