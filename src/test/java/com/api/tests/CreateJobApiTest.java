package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	
	@Test
	public void createJobAPITest () {
		
		
		Customer customer = new Customer("Neer", "Joshi", "9265432120", "", "abc@xyz.com", "");
		CustomerAddress customerAddress = new CustomerAddress("123", "Galaxy", "Khao Gali", "Opposite RBI", "Navi Mumbai", "122022", "India", "Haryana");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "12068852232432", "18162256632112", "18162256632112", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battray Issue");
		Problems [] problemsArray = {problems};
		CreateJobPayload customerjobpayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, customerjobpayload)).when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK());
		
	}

}
