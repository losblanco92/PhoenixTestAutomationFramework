package com.api.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

public class AuthTokenProvider {

	public static String getToken(Role role) {

	    UserCredentials userCredentials = switch (role) {
	        case FD  -> new UserCredentials("iamfd", "password");
	        case SUP -> new UserCredentials("iamsup", "password");
	        case ENG -> new UserCredentials("iameng", "password");
	        case QC  -> new UserCredentials("iamqc", "password");
	    };

		String token = given()
				.baseUri(ConfigManager.getProperty("BASE_URI"))
				.contentType(JSON)
				.body(userCredentials)
				.when().post("login")
				.then().log().ifValidationFails()
				.statusCode(200)
				.extract()
				.body().jsonPath().getString("data.token");

		return token;

	}

}
