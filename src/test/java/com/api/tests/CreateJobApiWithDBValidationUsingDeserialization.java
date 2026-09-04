package com.api.tests;

import static com.api.utils.DateTimeUtils.timeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
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
import com.api.response.model.CreateJobAPIResponse;
import com.api.utils.SpecUtils;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerAddressDao;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerDao;
import com.database.model.CustomerProductDBModel;
import com.database.model.CustomerProductDao;

import io.restassured.response.Response;

public class CreateJobApiWithDBValidationUsingDeserialization {
	private CreateJobPayload customerjobpayload;
	private Customer customer;
	private CustomerAddress customerAddress;
	private CustomerProduct customerProduct;

	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp()

	{
		customer = new Customer("Neer", "Joshi", "9265432120", "", "abc@xyz.com", "");

		customerAddress = new CustomerAddress("123", "Galaxy", "Khao Gali", "Opposite RBI", "Navi Mumbai", "122022",
				"India", "Haryana");
		customerProduct = new CustomerProduct(timeWithDaysAgo(10), "16188152232412", "16188152232412", "16188152232412",
				timeWithDaysAgo(10), Products.NEXUS_2.getCode(), Models.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);

		customerjobpayload = new CreateJobPayload(Service_Location.SERVICE_CENTRE_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemsList);

	}

	@Test(description = "Verify Create Job API is able to create In-warranty job", groups = { "api", "regression",
			"smoke" })
	public void createJobAPITest() {

		CreateJobAPIResponse response = given().spec(SpecUtils.requestSpecWithAuth(Role.FD, customerjobpayload)).when()
				.post("job/create").then().spec(SpecUtils.responseSpec_OK())
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json")).extract().as(CreateJobAPIResponse.class);

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

		

		CustomerProductDBModel customerProductDBmodel = CustomerProductDao.getCustomerProductInfo(response.getData().getTr_customer_product_id());

		Assert.assertEquals(customerProductDBmodel.getDop(),customerProduct.dop());
		Assert.assertEquals(customerProductDBmodel.getSerial_number(),customerProduct.serial_number());
		Assert.assertEquals(customerProductDBmodel.getImei1(),customerProduct.imei1());
		Assert.assertEquals(customerProductDBmodel.getImei2(),customerProduct.imei2());
		Assert.assertEquals(customerProductDBmodel.getMst_model_id(),customerProduct.mst_model_id());
		Assert.assertEquals(customerProductDBmodel.getPopurl(),customerProduct.popurl());
		
		
		
		
	}

}
