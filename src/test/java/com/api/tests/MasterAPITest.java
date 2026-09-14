package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static com.api.utils.SpecUtils.responseSpec_TXT;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.MasterService;

public class MasterAPITest {
	
	private MasterService  masterService;
	
	@BeforeMethod(description = "Instantiating Master API Service")
	public void setUp() {
	 masterService = new MasterService ();
	}
	
	
	@Test(description = "Verify Master API is showing correct response",groups = {"api", "regression", "smoke"})
	public void masterAPITest () {
		
		
		masterService.master(FD)
		 .then().spec(responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .body("$", hasKey("message"))
		 .body("$", hasKey("data"))
		 .body("data", hasKey("mst_oem"))
		 .body("data", hasKey("mst_model"))
		 .body("data.mst_oem.size()", greaterThan(0))
		 .body("data.mst_model.size()", greaterThan(0))
		 .body("data.mst_oem.id", everyItem(greaterThan(0)))
		 .body("data.mst_oem.name", everyItem(notNullValue()))
		  
		 .body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		}
	
	
	@Test(description = "Verify Master API is giving correct status code for invalid token",groups = {"api","negative","regression", "smoke"})
	public void invalidTokenMasterAPI () {
		
		masterService.masterWithoutToken()
		 .then().spec(responseSpec_TXT(401));
		
	}

}
