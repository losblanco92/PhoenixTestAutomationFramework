package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("User Management System")
@Feature("User Details")
@Listeners(com.listeners.APITestListener.class)

public class UserDetailsApiTest {
	
	private UserService userService;
	
	@BeforeMethod(description = "Initializing UserService instance")
	public void setUp() {
		
		 userService = new UserService();
	}
	
	@Story("User details should be correctly shown") 
    @Description("Verify User Details API is shwoing correct response")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify User Details API is shwoing correct response", groups = {"api", "regression", "smoke"})
	public void userDetailsApi () throws IOException {
		
		userService.userDetails(FD)
		.then().spec(responseSpec_OK()).and()
		.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	   
		
		
		
	}

}
