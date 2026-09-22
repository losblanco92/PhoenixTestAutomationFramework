package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Listeners;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

@Listeners(com.listeners.APITestListener.class)

public class MasterService {
	
	private final static String MASTER_API_ENDPOINT = "/master";
	
	@Step("Making Master API request")

	public Response master (Role role) {
		
		
	return	 given().spec(requestSpecWithAuth(role))
		 .when().post(MASTER_API_ENDPOINT);
	}
	
	public Response masterWithoutToken () {
		
		
		return	 given().spec(requestSpec())
			 .when().post(MASTER_API_ENDPOINT);
		}

}
