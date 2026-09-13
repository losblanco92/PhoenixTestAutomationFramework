package com.api.tests.datadriven;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;


public class LoginAPIDataDrivenTest {
	
	private AuthService authService;
	
	@BeforeMethod(description = "Initializing Auth Service")
	public void setUp() {
	 authService = new AuthService();
	}
	@Test(description = "Verify if login API is working for FD user", groups = {"api", "regression", "datadriven"},
			
			dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIDataProvider")
	public void loginApiTest (UserBean userBean) {
		
		authService.login(userBean)
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
