package api.tests.user;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matcher;
import static org.hamcrest.MatcherAssert.*;

public class Get_API_Tests {

	Faker faker;
	UserEndPoints endpoints;
	User payload;
	
	
	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Functional"})
	public void GetTest1() throws Exception {
		
		payload=new User();
		endpoints=new UserEndPoints();
		faker=new Faker();

		//Set values in Payload
		payload.setId(faker.number().randomDigit());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setUsername(faker.name().username());
		payload.setUserStatus(1);

		endpoints.CreateUser(payload); //Pre-requisite: Is to create the user before getting the same.
		
		Response response=endpoints.GetUser(payload.getUsername());
		
		Matcher<String> matcher= matchesPattern("^20[0-9]$");
		
		assertThat("Assertion 1", matcher.matches(String.valueOf(response.getStatusCode())));
		
	}
	
	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Regression"})
	public void GetTest2() throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		payload.setId(faker.number().randomDigit());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		payload.setUserStatus(0);

		endpoints.CreateUser(payload);
		
		Response response=endpoints.GetUser(payload.getUsername());
		
		assertThat(response.getStatusCode(), is(200));

	}
	
	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"})
	public void GetTest3() throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		payload.setId(faker.number().randomDigit());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		payload.setUserStatus(0);


		endpoints.CreateUser(payload);
		
		Response response=endpoints.GetUser(payload.getUsername());
		
		System.out.println(response.asPrettyString());
		
		
		  assertThat(response.jsonPath().get("id"), is(this.payload.getId()) );
		  assertThat(response.jsonPath().get("username"), is(this.payload.getUsername()));
		  assertThat(response.jsonPath().get("firstName"), is(this.payload.getFirstName()));
		  assertThat(response.jsonPath().get("lastName"), is(this.payload.getLastName()));
		  assertThat(response.jsonPath().get("password"), is(this.payload.getPassword()));
		  assertThat(response.jsonPath().get("phone"), is(this.payload.getPhone()));
		  assertThat(response.jsonPath().get("userStatus"), is(this.payload.getUserStatus()));


	}
	
	
}
