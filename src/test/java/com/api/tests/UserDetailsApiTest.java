package com.api.tests;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static com.api.constants.Role.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsApiTest {
	
	@Test
	public void userDetailsApi () throws IOException {
		
		given().spec(requestSpecWithAuth(FD))
		.when().get("userdetails")
		.then().spec(responseSpec_OK()).and()
		.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	   
		
		
		
	}

}
