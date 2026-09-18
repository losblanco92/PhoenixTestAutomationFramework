package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.request.model.DetailsPayload;
import com.api.services.DashBoardService;
import static com.api.utils.SpecUtils.*;


@Listeners(com.listeners.APITestListener.class)

public class DetailsAPITest {
	private DetailsPayload detailsPayload;
	private DashBoardService dashBoardService;

	@BeforeMethod(description = "Instantaiting DashBoard service and creating Details Payload")
	public void setUp() {

		detailsPayload = new DetailsPayload("created_today");
		dashBoardService = new DashBoardService();

	}

	@Test(description = "Verify Details API is showing correct response",groups = {"api", "regression", "smoke"})
	public void validateDetailsAPIResponse() {
		
		dashBoardService.details(FD, detailsPayload).then()
		.spec(responseSpec_OK()).body("message", equalTo("Success"));
		
		
	}

}
