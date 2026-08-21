package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtils;

public class CreateJobApiTestWithFakeData {
	private CreateJobPayload createJobPayload;
	
	
	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp  ()
	{
		
		createJobPayload= FakerDataGenerator.generateFakeCreateJobData();
	}
	
       
	
	@Test(description = "Verify Create Job API is able to create In-warranty job",groups = {"api", "regression", "smoke"})
	public void createJobAPITest () {
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
		           .when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK())
		      .body("message", equalTo("Job created successfully. "))
		      .body("data.mst_service_location_id", equalTo(1))
		      .body("data.job_number", startsWith("JOB_"))
		      .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json"));
		
	}

}
