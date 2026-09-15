package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;

import org.apache.logging.log4j.LogManager;



import io.restassured.response.Response;

public class AuthService {

	private static final Logger LOGGER= LogManager.getLogger(AuthService.class);
	private static final String LOGIN_ENDPOINT = "login";

	public Response login(Object userCredentials) {
    LOGGER.info("Making logging request for the payload {}",((UserCredentials)userCredentials).username());
		Response response = 
				
	given().spec(requestSpec(userCredentials))
	.when().post(LOGIN_ENDPOINT);
		
		return response;

	}

}
