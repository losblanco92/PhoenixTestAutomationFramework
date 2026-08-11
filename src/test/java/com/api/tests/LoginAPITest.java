package com.api.tests;

import static com.api.utils.SpecUtils.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;


public class LoginAPITest {

	@Test
	public void loginApiTest () {
		
		UserCredentials userCredentails = new UserCredentials("iamfd", "password");
		
		given().spec(requestSpec(userCredentails))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
