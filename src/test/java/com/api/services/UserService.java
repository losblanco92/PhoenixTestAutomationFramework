package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserService {
	
	private static final String USERDETAILS_ENDPOINT = "/userdetails";
		
	@Step("Making User Details API request")

	public Response userDetails (Role role) {
		
		 return  given().spec(requestSpecWithAuth(role))
		.when().get(USERDETAILS_ENDPOINT);
		
	}

}
