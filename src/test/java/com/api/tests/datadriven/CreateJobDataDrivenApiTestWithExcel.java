package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtils;

public class CreateJobDataDrivenApiTestWithExcel {

	
	@Test(description = "Verify Create Job API is able to create In-warranty job",groups = {"api", "regression", "datadriven","csv"},
			
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIExcelDataProvider"
			
			
			
			)
	public void createJobAPITest (CreateJobPayload createJobPayload) {
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK())
		      .body("message", equalTo("Job created successfully. "))
		      .body("data.mst_service_location_id", equalTo(1))
		      .body("data.job_number", startsWith("JOB_"))
		      .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json"));
		

	}

}
