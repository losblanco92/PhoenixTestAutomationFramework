package com.api.tests;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentails;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {

	@Test
	public void loginApiTest () {
		
		UserCredentails userCredentails = new UserCredentails("iamfd", "password");
		
		given().baseUri("http://64.227.160.186:9000/v1")
		.and().contentType(ContentType.JSON)
		.and().body(userCredentails)
		.and().accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when().post("login")
		.then().log().all().
		 and().statusCode(200)
		.time(lessThan(2000L))
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
