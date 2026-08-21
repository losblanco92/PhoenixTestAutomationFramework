package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.constants.Models;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Products;
import com.api.constants.Service_Location;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static final String COUNTRY = "India";

	private static final Faker FAKER = new Faker(new Locale("en-IND"));
	
	private static final Random RANDOM = new Random();
	
	private static int [] validProblemId = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		  List<Problems> problemsList = generateFakeListOfProblems();
		  CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_CENTRE_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	
	     return createJobPayload;
	}
	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
       
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for(int i=1; i<=count; i++) {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		  List<Problems> problemsList = generateFakeListOfProblems();
		  CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_CENTRE_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	       
		  payloadList.add(createJobPayload);
	     
		}
		return payloadList.iterator();
	}

	private static List<Problems> generateFakeListOfProblems() {
		String FAKERemark = FAKER.pokemon().name();

		
		int randomIndex = RANDOM.nextInt(validProblemId.length);

		Problems problems = new Problems(validProblemId[randomIndex], FAKERemark);

		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {
		String dop = DateTimeUtils.timeWithDaysAgo(10);
		String imeiSerialNumber = FAKER.numerify("###############");
		String popUrl = FAKER.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, Products.NEXUS_2.getCode(), Models.NEXUS_2_BLUE.getCode());
		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddress() {
		String flatNumber = FAKER.numerify("###");
		String apartmentName = FAKER.address().streetName();
		String streeetName = FAKER.address().streetName();
		String landmark = FAKER.address().streetName();
		String area = FAKER.address().streetName();
		String pinCode = FAKER.numerify("######");

		String state = FAKER.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streeetName, landmark, area,
				pinCode, COUNTRY, state);
		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		String fname = FAKER.name().firstName();
		String lname = FAKER.name().lastName();
		String mobileNumber = FAKER.numerify("910#######");
		String alternateMobileNumber = FAKER.numerify("981#######");
		String customerEmailAddres = FAKER.internet().emailAddress();
		String altCustomerEmailAddres = FAKER.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddres,
				altCustomerEmailAddres);
		return customer;
	}

}
