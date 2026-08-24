package com.api.tests.datadriven;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.dataproviders.api.bean.UserBean;


public class LoginAPIDataDrivenUsingExcel {
	
	@Test(description = "Verify if login API is working for FD user", groups = {"api", "regression", "datadriven"},
			
			dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIDataProviderUsingExcel")
	public void loginApiTest (UserBean userBean) {
		
		given().spec(requestSpec(userBean))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
		
		
	}
	
}
