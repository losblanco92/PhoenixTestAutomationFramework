package com.api.tests;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("User Management System")
@Feature("Authentication")
@Listeners(com.listeners.APITestListener.class)
public class LoginAPITest {


 private	 UserBean userCredentails;
 private AuthService authService;
     @BeforeMethod(description = "Create the payload for login API")
	public void setUp() {
	
    	 authService = new AuthService();
    	 userCredentails = new UserBean("iamfd", "password");
		
	}
	
    @Story("Valid user should be able to login to the system") 
    @Description("Verify if login API is working for FD user")
    @Severity(SeverityLevel.BLOCKER)
    
	@Test(description = "Verify if login API is working for FD user", groups = {"api", "regression", "smoke"}
	, retryAnalyzer = com.api.retry.RetryAnalyzer.class)
	public void loginApiTest () {
		
	  authService.login(userCredentails)
	.then()
	.spec(responseSpec_OK())
	.body("message", equalTo("Success"))
	.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
