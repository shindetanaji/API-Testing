package com.qc.api.steps;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.qc.api.utils.RegisterRequest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QCSteps {

	RequestSpecification request;
	RegisterRequest rrequest;
	Response response;
	RequestSpecBuilder builder = new RequestSpecBuilder();
	JSONParser jsonParser = new JSONParser();

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://queuecodes.com/API-Testing/";
		RestAssured.basePath = "register_user.php";
		request = RestAssured.given();
	}

	@Given("^I want to prepare data for create account$")
	public void i_want_to_prepare_data_for_create_account() throws Throwable {
		FileReader reader = new FileReader(
				"src/test/resources/create-account.json");
		JSONObject data = (JSONObject) jsonParser.parse(reader);
		request.body(data.toJSONString());
	}

	@When("^I submit the POST request for create single user$")
	public void i_submit_the_POST_request_for_create_single_user()
			throws Throwable {
		response = request.contentType("application/json").when().post();

	}

	@Then("^I should get (\\d+) success status code along with response body$")
	public void i_should_get_success_status_code_along_with_response_body(
			int expStatusCode) throws Throwable {
		System.out.println("Response body: " + response.body().asString());
		Assert.assertEquals(response.getStatusCode(), expStatusCode);
	}
	
}
