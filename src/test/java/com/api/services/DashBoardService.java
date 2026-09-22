package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashBoardService {

	private static final Logger LOGGER= LogManager.getLogger(DashBoardService.class);
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	private static final String DETAILS_ENDPOINT = "/dashboard/details"; 
	
	@Step("Making Count API requst for the role")
	public Response count (Role role) {
		LOGGER.info("Making request to the {} for the role {}", COUNT_ENDPOINT,role );
	return	given().spec(requestSpecWithAuth(role))
		.when().get(COUNT_ENDPOINT);
	}
	@Step("Making Count API request without Auth Token")
	public Response countAPIWithNoAuth () {
		LOGGER.info("Making request to the {} with no auth", COUNT_ENDPOINT );	
		return	given().spec(requestSpec())
			.when().get(COUNT_ENDPOINT);
		}
	
	@Step("Making Details API request for the role")
	public Response details (Role role, Object payload) {
		LOGGER.info("Making request to the {} for the role {} and the payload {}", DETAILS_ENDPOINT, role, payload );	
		return	given().spec(requestSpecWithAuth(role, payload))
			.when().post(DETAILS_ENDPOINT);
		}
	
}
