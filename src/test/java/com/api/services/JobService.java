package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtils;

import io.restassured.response.Response;

public class JobService {
	
	private final static String CREATE_JOB_ENDPOINT ="/job/create";
	
	public Response create (Role role, Object payload) {
		
	return	given().spec(SpecUtils.requestSpecWithAuth(role, payload)).when()
        .post(CREATE_JOB_ENDPOINT);
		
		
	}

}
