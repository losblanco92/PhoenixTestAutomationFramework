package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.response.model.CreateJobAPIResponse;
import com.api.utils.AssertionUtility;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtils;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadModel;
import com.database.model.MapJobProblemModel;

public class CreateJobApiTestWithFakeData {
	private CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp() {

		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify Create Job API is able to create In-warranty job", groups = { "api", "regression",
			"smoke" })
	public void createJobAPITest() {

		CreateJobAPIResponse response = given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when()
				.post("job/create").then().spec(SpecUtils.responseSpec_OK())
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json")).extract().as(CreateJobAPIResponse.class);

		Customer customer = createJobPayload.customer();
		CustomerAddress customerAddress = createJobPayload.customer_address();
		CustomerProduct customerProduct = createJobPayload.customer_product();
		List<Problems> problems=createJobPayload.problems();
		

		CustomerDBModel customerDBModel = CustomerDao.getCustomerInfo(response.getData().getTr_customer_id());

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
	
		
		JobHeadModel jobHeadDataFromDB = JobHeadDao.getJobHeadInfo(response.getData().getTr_customer_id());

		Assert.assertEquals(jobHeadDataFromDB.getMst_oem_id(), createJobPayload.mst_oem_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_service_location_id(), createJobPayload.mst_service_location_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_platform_id(), createJobPayload.mst_platform_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_warrenty_status_id(), createJobPayload.mst_warrenty_status_id());

		List<MapJobProblemModel> problemDataFromDB = MapJobProblemDao.getProblemInfo(response.getData().getId());

		AssertionUtility.assertProblemDetails(problemDataFromDB, problems);
		
		
		CustomerProductDBModel customerProductDBmodel = CustomerProductDao
				.getCustomerProductInfo(response.getData().getTr_customer_product_id());

		//Assert.assertEquals(customerProductDBmodel.getDop(),customerProduct.dop());
		Assert.assertEquals(customerProductDBmodel.getSerial_number(), customerProduct.serial_number());
		Assert.assertEquals(customerProductDBmodel.getImei1(), customerProduct.imei1());
		Assert.assertEquals(customerProductDBmodel.getImei2(), customerProduct.imei2());
		Assert.assertEquals(customerProductDBmodel.getMst_model_id(), customerProduct.mst_model_id());
		Assert.assertEquals(customerProductDBmodel.getPopurl(), customerProduct.popurl());

	
	
	
	
	
	
	}

}
