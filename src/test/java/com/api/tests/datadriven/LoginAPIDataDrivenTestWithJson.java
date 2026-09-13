package com.api.tests.datadriven;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;




public class LoginAPIDataDrivenTestWithJson {
	private AuthService authService;
	@BeforeMethod(description = "Initializing AuthService")
	
	public void setUp() {
		authService = new AuthService();
	}
	
	@Test(description = "Verify if login API is working for FD user", groups = {"api", "regression", "datadriven"},
			
			dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginApiTest (UserCredentials userCredentials) {
		
		authService.login(userCredentials)
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
