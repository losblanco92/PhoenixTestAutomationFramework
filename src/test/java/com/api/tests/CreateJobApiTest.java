package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	
	@Test
	public void createJobAPITest () {
		
		
		Customer customer = new Customer("Neer", "Joshi", "9265432120", "", "abc@xyz.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("123", "Galaxy", "Khao Gali", "Opposite RBI", "Navi Mumbai", "122022", "India", "Haryana");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "10468852232432", "10468852232432", "10468852232432", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battray Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload customerjobpayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, customerjobpayload)).when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK())
		      .body("message", equalTo("Job created successfully. "))
		      .body("data.mst_service_location_id", equalTo(1))
		      .body("data.job_number", startsWith("JOB_"))
		      .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json"));
		
	}

}
