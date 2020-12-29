package com.qc.api.steps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.qc.api.utils.RegisterRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HSBCAPISteps {

	RequestSpecification request;
	RegisterRequest rrequest;
	Response response;
	HashMap<String, Float> exchangeRates;

	@Given("^Rates API for Latest Foreign Exchange rates \"([^\"]*)\" with \"([^\"]*)\"$")
	public void rates_API_for_Latest_Foreign_Exchange_rates_with(
			String baseURI, String basePath) throws Throwable {
		RestAssured.baseURI = baseURI;
		RestAssured.basePath = basePath;
		request = RestAssured.given();
	}

	@When("^The API is available$")
	public void the_API_is_available() throws Throwable {
		response = request.relaxedHTTPSValidation().when().get();
		exchangeRates = response.path("rates");
	}

	@Then("^To validate the response code (\\d+)$")
	public void to_validate_the_response_code(int expStatusCode)
			throws Throwable {
		Assert.assertEquals(response.getStatusCode(), expStatusCode);
	}

	@Then("^To validate date should be current date$")
	public void to_validate_date_should_be_current_date() throws Throwable {
		LocalDate expDate = LocalDate.now();
		String actDate = response.path("date");
		Assert.assertEquals(actDate, expDate);
	}

	@Then("^To validate response data should be\"([^\"]*)\"$")
	public void to_validate_response_data_should_be(String expResult)
			throws Throwable {
		Float actualResult = exchangeRates.get("INR");
		Float floatVal = Float.valueOf(expResult).floatValue();
		Assert.assertEquals(actualResult, floatVal);
	}

	@Then("^To validate response data should be\"([^\"]*)\" and \"([^\"]*)\"$")
	public void to_validate_response_data_should_be_and(String key, String value)
			throws Throwable {
		Float actualResult = null;
		if (key.equalsIgnoreCase("IND")) {
			actualResult = exchangeRates.get("INR");
		} else if (key.equalsIgnoreCase("GBP")) {
			actualResult = exchangeRates.get("GBP");
		} else if (key.equalsIgnoreCase("HKD")) {
			actualResult = exchangeRates.get("HKD");
		} else if (key.equalsIgnoreCase("IDR")) {
			actualResult = exchangeRates.get("IDR");
		}
		Float floatVal = Float.valueOf(value).floatValue();
		Assert.assertEquals(actualResult, floatVal);
	}
public void test()
{
	System.out.println("chnaged in class file");
}

}
