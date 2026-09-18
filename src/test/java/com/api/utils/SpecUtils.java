package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;	
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.lessThan;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
	
	public static RequestSpecification requestSpec() {
		
	RequestSpecification requestSpecification	= new RequestSpecBuilder()
			                    .setBaseUri(getProperty("BASE_URI"))
		                        .setContentType(JSON)
		                         .setAccept(JSON)
		                         .addFilter(new SensitiveDataFilter())
		                         .build();
	
	    return requestSpecification;
		
	}
	
	
	public static RequestSpecification requestSpec(Object payload) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				                     .setBaseUri(getProperty("BASE_URI"))
			                         .setContentType(JSON)
			                         .setAccept(JSON).setBody(payload)
			                          .addFilter(new SensitiveDataFilter())
			                         .build();
		
		    return requestSpecification;
			
		}
	
	
public static RequestSpecification requestSpecWithAuth(Role role) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				                     .setBaseUri(getProperty("BASE_URI"))
			                         .setContentType(JSON)
			                         .setAccept(JSON)
			                         .addHeader("Authorization", AuthTokenProvider.getToken(role))
			                         .addFilter(new SensitiveDataFilter())
			                         .build();
		
		    return requestSpecification;
			
		}

public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
	
	RequestSpecification requestSpecification = new RequestSpecBuilder()
			                     .setBaseUri(getProperty("BASE_URI"))
		                         .setContentType(JSON)
		                         .setAccept(JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
		                         .addFilter(new SensitiveDataFilter())
		                         .setBody(payload)
		                         .build();
	
	    return requestSpecification;
		
	}

	
	
	public static ResponseSpecification responseSpec_OK () {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				                      .expectContentType(JSON)
				                     .expectStatusCode(200)
		                            .expectResponseTime(lessThan(2000L))
		                        
		                            .build();
		  
		  return responseSpecification;
		                         
		
		  
		
	}
	
	public static ResponseSpecification responseSpec_JSON (int statusCode) {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				                      .expectContentType(JSON)
				                     .expectStatusCode(statusCode)
		                            .expectResponseTime(lessThan(2000L))
		                            
		                            .build();
		  
		  return responseSpecification;
	
}
	
	public static ResponseSpecification responseSpec_TXT (int statusCode) {
		
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				             
				                      .expectStatusCode(statusCode)
		                            .expectResponseTime(lessThan(2000L))
		                           
		                            .build();
		  
		  return responseSpecification;

}
	
}