package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.SearchJobPayload;
import com.api.services.JobService;


@Listeners(com.listeners.APITestListener.class)

public class SearchJobAPITest {
	
private	JobService jobService;
private final static String JOB_NUMBER ="JOB_422343";
private	SearchJobPayload searchJobPayload;
	
	
@BeforeMethod(description = "Instantaiting Job service and creating SearchJob Payload")
public void setUp () {
	
	searchJobPayload = new SearchJobPayload(JOB_NUMBER);
	jobService = new JobService();
	
}
	
@Test(description = "Verify Serach Job API is showing correct response",groups = {"api", "regression", "smoke"})
public void validateDetailsAPIResponse() {
	
	jobService.search(FD, searchJobPayload).then().spec(responseSpec_OK())
	.body("message", equalTo("Success"));
	
	
	
}

}
