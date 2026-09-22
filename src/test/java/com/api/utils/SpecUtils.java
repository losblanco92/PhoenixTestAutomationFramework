package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.lessThan;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {

	@Step("Setting up the Base URI, Content Type and attaching the sensitive data filter")

	public static RequestSpecification requestSpec() {

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(JSON).setAccept(JSON).addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return requestSpecification;

	}

	@Step("Setting up the Base URI, Content Type and attaching the sensitive data filter")

	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(JSON).setAccept(JSON).setBody(payload).addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return requestSpecification;

	}

	@Step("Setting up the Base URI, Content Type and attaching the sensitive data filter for the role")

	public static RequestSpecification requestSpecWithAuth(Role role) {

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(JSON).setAccept(JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return requestSpecification;

	}

	@Step("Setting up the Base URI, Content Type and attaching the sensitive data filter for the role with payload")

	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(JSON).setAccept(JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).setBody(payload).build();

		return requestSpecification;

	}

	@Step("Expecting the response to have expected Content Type, Status as 200 and expected Response time")

	public static ResponseSpecification responseSpec_OK() {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(JSON)
				.expectStatusCode(200).expectResponseTime(lessThan(2000L))

				.build();

		return responseSpecification;

	}

	@Step("Expecting the response to have expected Content Type,  expctected Status code and expected Response time")

	public static ResponseSpecification responseSpec_JSON(int statusCode) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(JSON)
				.expectStatusCode(statusCode).expectResponseTime(lessThan(2000L))

				.build();

		return responseSpecification;

	}

	@Step("Expecting the response to have expctected Status code and expected Response time")

	public static ResponseSpecification responseSpec_TXT(int statusCode) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder()

				.expectStatusCode(statusCode).expectResponseTime(lessThan(2000L))

				.build();

		return responseSpecification;

	}

}