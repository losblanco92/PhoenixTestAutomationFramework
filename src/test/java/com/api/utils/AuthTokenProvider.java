package com.api.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

public class AuthTokenProvider {
	private static final Logger LOGGER= LogManager.getLogger(AuthTokenProvider.class);
	public static Map<Role, String> tokenCaching = new ConcurrentHashMap<Role, String>();

	public static String getToken(Role role) {

		LOGGER.info("Checking if token for {} is present in the cache", role);
		if (tokenCaching.containsKey(role)) {
			
			LOGGER.info("Token found for {}", role);

			return tokenCaching.get(role);

		}

		else {

			LOGGER.info("Token not found for {} making the request for token", role);
			UserCredentials userCredentials = switch (role) {
			case FD -> new UserCredentials("iamfd", "password");
			case SUP -> new UserCredentials("iamsup", "password");
			case ENG -> new UserCredentials("iameng", "password");
			case QC -> new UserCredentials("iamqc", "password");
			};

			String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(JSON)
					.body(userCredentials).when().post("login").then().log().ifValidationFails().statusCode(200)
					.extract().body().jsonPath().getString("data.token");

			LOGGER.info("Token cached for future request for {}", role);
			tokenCaching.put(role, token);

			return token;

		}

	}

}
