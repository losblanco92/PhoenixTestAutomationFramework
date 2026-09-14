package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtils;

import io.restassured.response.Response;

public class JobService {

	private final static String CREATE_JOB_ENDPOINT = "/job/create";
	private final static String SEARCH_JOB_ENDPOINT = "/job/search";

	public Response create(Role role, Object payload) {

		return given().spec(SpecUtils.requestSpecWithAuth(role, payload)).when().post(CREATE_JOB_ENDPOINT);

	}

	public Response search(Role role, Object payload) {

		return given().spec(SpecUtils.requestSpecWithAuth(role, payload))
				.when().post(SEARCH_JOB_ENDPOINT);

	}
}
