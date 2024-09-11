package api.test.datadriven.user;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utils.DataProviders;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class Post_API_Tests {

	ExtentTest test;
	User payload;
	UserEndPoints endpoints;
	Faker faker;

	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PostTest1(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));
	
		Response response = endpoints.CreateUser(payload);
		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		System.out.println(response.asPrettyString());
		
		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));

	}

	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Regression"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PostTest2(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));
		
		Response response = endpoints.CreateUser(payload);
		
		System.out.println(response.asPrettyString());
		
		assertThat(response.getStatusCode(), is(200));

	}
	
	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PostTest3(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		Response response = endpoints.CreateUser(payload);
		
		System.out.println(response.asPrettyString());
		
		assertThat(response.jsonPath().get("code"), is(200));
		assertThat(response.jsonPath().get("type"), equalToIgnoringWhiteSpace("unknown"));
		assertThat(response.jsonPath().get("message"), notNullValue());


	}
	
}