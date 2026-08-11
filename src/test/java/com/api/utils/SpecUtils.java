package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;

import static org.hamcrest.Matchers.*;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import static com.api.utils.ConfigManager.*;

public class SpecUtils {
	
	public static RequestSpecification requestSpec() {
		
	RequestSpecification requestSpecification	= new RequestSpecBuilder()
			                    .setBaseUri(getProperty("BASE_URI"))
		                        .setContentType(JSON)
		                         .setAccept(JSON)
		                         .log(METHOD)
		                          .log(BODY)
		                          .log(HEADERS)
		                          .log(URI).build();
	
	    return requestSpecification;
		
	}
	
	
	public static RequestSpecification requestSpec(Object payload) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				                     .setBaseUri(getProperty("BASE_URI"))
			                         .setContentType(JSON)
			                         .setAccept(JSON).setBody(payload)
			                         .log(METHOD)
			                         .log(BODY)
			                         .log(HEADERS)
			                         .log(URI).build();
		
		    return requestSpecification;
			
		}
	
	
public static RequestSpecification requestSpecWithAuth(Role role) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				                     .setBaseUri(getProperty("BASE_URI"))
			                         .setContentType(JSON)
			                         .setAccept(JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
			                         .log(METHOD)
			                         .log(BODY)
			                         .log(HEADERS)
			                         .log(URI).build();
		
		    return requestSpecification;
			
		}

public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
	
	RequestSpecification requestSpecification = new RequestSpecBuilder()
			                     .setBaseUri(getProperty("BASE_URI"))
		                         .setContentType(JSON)
		                         .setAccept(JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
		                         .setBody(payload)
		                         .log(METHOD)
		                         .log(BODY)
		                         .log(HEADERS)
		                         .log(URI).build();
	
	    return requestSpecification;
		
	}

	
	
	public static ResponseSpecification responseSpec_OK () {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				                      .expectContentType(JSON)
				                     .expectStatusCode(200)
		                            .expectResponseTime(lessThan(2000L))
		                            .log(ALL)
		                            .build();
		  
		  return responseSpecification;
		                         
		
		  
		
	}
	
	public static ResponseSpecification responseSpec_JSON (int statusCode) {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				                      .expectContentType(JSON)
				                     .expectStatusCode(statusCode)
		                            .expectResponseTime(lessThan(2000L))
		                            .log(ALL)
		                            .build();
		  
		  return responseSpecification;
	
}
	
	public static ResponseSpecification responseSpec_TXT (int statusCode) {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				             
				                      .expectStatusCode(statusCode)
		                            .expectResponseTime(lessThan(2000L))
		                            .log(ALL)
		                            .build();
		  
		  return responseSpecification;

}
	
}