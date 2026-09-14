package com.api.services;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import static com.api.utils.SpecUtils.*;

import io.restassured.response.Response;

public class MasterService {
	
	private final static String MASTER_API_ENDPOINT = "/master";
	
	
	public Response master (Role role) {
		
		
	return	 given().spec(requestSpecWithAuth(role))
		 .when().post(MASTER_API_ENDPOINT);
	}
	
	public Response masterWithoutToken () {
		
		
		return	 given().spec(requestSpec())
			 .when().post(MASTER_API_ENDPOINT);
		}

}
