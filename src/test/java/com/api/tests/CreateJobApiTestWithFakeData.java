package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtils;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerAddressDao;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerDao;

public class CreateJobApiTestWithFakeData {
	private CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp() {

		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify Create Job API is able to create In-warranty job", groups = { "api", "regression",
			"smoke" })
	public void createJobAPITest() {

		int customerId = given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when()
				.post("job/create").then().spec(SpecUtils.responseSpec_OK())
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json")).extract().body()
				.jsonPath().getInt("data.tr_customer_id");

		Customer customer = createJobPayload.customer();
		CustomerAddress customerAddress = createJobPayload.customer_address();

		CustomerDBModel customerDBModel = CustomerDao.getCustomerInfo(customerId);

		CustomerAddressDBModel customerAddressDBModel = CustomerAddressDao
				.getCustomerAddressInfo(customerDBModel.getTr_customer_address_id());

		Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());
		Assert.assertEquals(customerDBModel.getLast_name(), customer.last_name());
		Assert.assertEquals(customerDBModel.getMobile_number(), customer.mobile_number());
		Assert.assertEquals(customerDBModel.getMobile_number_alt(), customer.mobile_number_alt());
		Assert.assertEquals(customerDBModel.getEmail_id(), customer.email_id());
		Assert.assertEquals(customerDBModel.getEmail_id_alt(), customer.email_id_alt());
		
		
		Assert.assertEquals(customerAddressDBModel.getFlat_number(), customerAddress.flat_number());
		Assert.assertEquals(customerAddressDBModel.getStreet_name(), customerAddress.street_name());
		Assert.assertEquals(customerAddressDBModel.getLandmark(), customerAddress.landmark());
		Assert.assertEquals(customerAddressDBModel.getArea(), customerAddress.area());
		Assert.assertEquals(customerAddressDBModel.getPincode(), customerAddress.pincode());
		Assert.assertEquals(customerAddressDBModel.getCountry(), customerAddress.country());
		Assert.assertEquals(customerAddressDBModel.getState(), customerAddress.state());
	}

}
