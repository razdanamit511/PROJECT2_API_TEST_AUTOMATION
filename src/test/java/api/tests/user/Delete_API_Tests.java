package api.tests.user;

import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class Delete_API_Tests {

	User payload;
	UserEndPoints endpoints;
	Faker faker;

	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Functional"})
	public void DeleteTest1() throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
	//	System.out.println(OriginalUser.asPrettyString());
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		assertThat("Assertion 1", matcher1.matches(String.valueOf(DeletedUser.getStatusCode())));

	}

	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Regression"})
	public void DeleteTest2() throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		System.out.println(DeletedUser.asPrettyString());
		assertThat(DeletedUser.getStatusCode(), is(200));

	}
	
	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"})
	public void DeleteTest3() throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		System.out.println(DeletedUser.asPrettyString());
		
		assertThat(DeletedUser.jsonPath().get("code"), is(200));
		assertThat(DeletedUser.jsonPath().get("type"), equalToIgnoringWhiteSpace("unknown"));
		assertThat(DeletedUser.jsonPath().get("message"), notNullValue());


	}
	
}
