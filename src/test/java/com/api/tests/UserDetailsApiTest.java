package com.api.tests;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsApiTest {
	
	@Test
	public void userDetailsApi () throws IOException {
		
		Header authHeader = new Header("Authorization", getToken(FD));
		
		given().baseUri(getProperty("BASE_URI")).header(authHeader)
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when().get("userdetails")
		.then().statusCode(200).log().all()
		.time(Matchers.lessThan(2000L)).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	   
		
		
		
	}

}
