package com.api.utils;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.pojo.UserCredentails;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

	public static String getToken(Role role) {

		UserCredentails usrCredentails = null;

		if (role == FD) {
			usrCredentails = new UserCredentails("iamfd", "password");
		}

		else if (role == SUP) {
			usrCredentails = new UserCredentails("iamsup", "password");
		}

		else if (role == ENG) {
			usrCredentails = new UserCredentails("iameng", "password");
		}

		else if (role == QC) {
			usrCredentails = new UserCredentails("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(usrCredentails).when().post("login").then().log().ifValidationFails().statusCode(200).extract()
				.body().path("data.token");

		return token;

	}

}
