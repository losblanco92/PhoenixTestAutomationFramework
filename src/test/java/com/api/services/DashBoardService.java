package com.api.services;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import static com.api.utils.SpecUtils.*;

import io.restassured.response.Response;

public class DashBoardService {

	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	public Response count (Role role) {
		
	return	given().spec(requestSpecWithAuth(role))
		.when().get(COUNT_ENDPOINT);
	}
	
	public Response countAPIWithNoAuth () {
		
		return	given().spec(requestSpec())
			.when().get(COUNT_ENDPOINT);
		}
	
}
