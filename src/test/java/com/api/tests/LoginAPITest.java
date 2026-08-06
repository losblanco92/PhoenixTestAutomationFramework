package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import org.testng.annotations.Test;
import com.api.pojo.UserCredentails;
import io.restassured.http.ContentType;

public class LoginAPITest {

	@Test
	public void loginApiTest () {
		
      UserCredentails userCredentails = new UserCredentails("iamfd", "password");
		
		given().baseUri(getProperty("BASE_URI"))
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
