package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Models;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Products;
import com.api.constants.Role;
import com.api.constants.Service_Location;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtils.*;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	private CreateJobPayload customerjobpayload;
	
	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp  ()
	
	{
		
          Customer customer = new Customer("Neer", "Joshi", "9265432120", "", "abc@xyz.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("123", "Galaxy", "Khao Gali", "Opposite RBI", "Navi Mumbai", "122022", "India", "Haryana");
		CustomerProduct customerProduct = new CustomerProduct(timeWithDaysAgo(10), "10968152232432", "10968152232432", "10968152232432", timeWithDaysAgo(10), Products.NEXUS_2.getCode(), Models.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battray Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		customerjobpayload = new CreateJobPayload(Service_Location.SERVICE_CENTRE_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		
		
	}
	
	@Test(description = "Verify Create Job API is able to create In-warranty job",groups = {"api", "regression", "smoke"})
	public void createJobAPITest () {
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, customerjobpayload)).when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK())
		      .body("message", equalTo("Job created successfully. "))
		      .body("data.mst_service_location_id", equalTo(1))
		      .body("data.job_number", startsWith("JOB_"))
		      .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json"));
		
	}

}
