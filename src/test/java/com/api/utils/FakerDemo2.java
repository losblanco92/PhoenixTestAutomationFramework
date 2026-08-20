package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {

	final static String COUNTRY = "India";

	public static void main(String[] args) {

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

		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemList);
		System.out.println(createJobPayload);

	}

}
