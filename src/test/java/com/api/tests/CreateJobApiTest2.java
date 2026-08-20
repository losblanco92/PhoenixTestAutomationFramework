package com.api.tests;

import static com.api.utils.DateTimeUtils.timeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import com.api.utils.DateTimeUtils;
import com.api.utils.SpecUtils;
import com.github.javafaker.Faker;

public class CreateJobApiTest2 {
	private CreateJobPayload customerJobPayload;
	private final static String COUNTRY = "India";
	
	@BeforeMethod(description = "Creates payload for Create Job API")
	public void setUp  ()
	
	{
		Faker faker = new Faker(new Locale("en-IND"));

			String fname = faker.name().firstName();
			String lname = faker.name().lastName();
			String mobileNumber = faker.numerify("910#######");
			String alternateMobileNumber = faker.numerify("981#######");
			String customerEmailAddres = faker.internet().emailAddress();
			String altCustomerEmailAddres = faker.internet().emailAddress();
			Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddres,
					altCustomerEmailAddres);

			String flatNumber = faker.numerify("###");
			String apartmentName = faker.address().streetName();
			String streeetName = faker.address().streetName();
			String landmark = faker.address().streetName();
			String area = faker.address().streetName();
			String pinCode = faker.numerify("######");

			String state = faker.address().state();

			CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streeetName, landmark, area,
					pinCode, COUNTRY, state);

			String dop = DateTimeUtils.timeWithDaysAgo(10);
			String imeiSerialNumber = faker.numerify("###############");
			String popUrl = faker.internet().url();

			CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
					popUrl, 1, 1);

			String fakeRemark = faker.lorem().sentence(4);

			Random random = new Random();
			int problemId = random.nextInt(27) + 1;

			Problems problems = new Problems(problemId, fakeRemark);

			List<Problems> problemList = new ArrayList<Problems>();
			problemList.add(problems);

			customerJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
					problemList);
		
	}
	
	@Test(description = "Verify Create Job API is able to create In-warranty job",groups = {"api", "regression", "smoke"})
	public void createJobAPITest () {
		
		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, customerJobPayload)).when()
		           .post("job/create")
		      .then().spec(SpecUtils.responseSpec_OK())
		      .body("message", equalTo("Job created successfully. "))
		      .body("data.mst_service_location_id", equalTo(1))
		      .body("data.job_number", startsWith("JOB_"))
		      .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPISchema.json"));
		
	}

}
