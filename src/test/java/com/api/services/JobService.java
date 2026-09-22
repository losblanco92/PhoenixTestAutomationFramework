package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {

	private final static String CREATE_JOB_ENDPOINT = "/job/create";
	private final static String SEARCH_JOB_ENDPOINT = "/job/search";
	
	@Step("Creating In Warranty Job with Create Job API")

	public Response create(Role role, Object payload) {

		return given().spec(SpecUtils.requestSpecWithAuth(role, payload)).when().post(CREATE_JOB_ENDPOINT);

	}
	@Step("Making Search API request")

	public Response search(Role role, Object payload) {

		return given().spec(SpecUtils.requestSpecWithAuth(role, payload))
				.when().post(SEARCH_JOB_ENDPOINT);

	}
}
