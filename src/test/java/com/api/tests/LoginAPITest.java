package com.api.tests;

import static com.api.utils.SpecUtils.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;


public class LoginAPITest {

 private	 UserCredentials userCredentails;
 
     @BeforeMethod(description = "Create the payload for login API")
	public void setUp() {
		
	 userCredentails = new UserCredentials("iamfd", "password");
		
	}
	
	@Test(description = "Verify if login API is working for FD user", groups = {"api", "regression", "smoke"})
	public void loginApiTest () {
		
		given().spec(requestSpec(userCredentails))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
