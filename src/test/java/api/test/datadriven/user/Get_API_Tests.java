package api.test.datadriven.user;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utils.DataProviders;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matcher;
import static org.hamcrest.MatcherAssert.*;

public class Get_API_Tests {

	Faker faker;
	UserEndPoints endpoints;
	User payload;
	
	
	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void GetTest1(String Id, String FirstName, String LastName, String Username, String Email, String Password, String PhoneNumber, String UserStatus) throws Exception {
		
		payload=new User();
		endpoints=new UserEndPoints();
		faker=new Faker();

		//Set values in Payload
		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setPhone(PhoneNumber);
		payload.setEmail(Email);
		payload.setUsername(Username);
		payload.setPassword(Password);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		endpoints.CreateUser(payload); //Pre-requisite: Is to create the user before getting the same.
		
		Response response=endpoints.GetUser(payload.getUsername());
		
		Matcher<String> matcher= matchesPattern("^20[0-9]$");
		
		assertThat("Assertion 1", matcher.matches(String.valueOf(response.getStatusCode())));
		
	}
	
	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Regression"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void GetTest2(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload=new User();
		endpoints = new UserEndPoints();
		faker=new Faker();
		
		//Set values in Payload
		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setPhone(PhoneNumber);
		payload.setEmail(Email);
		payload.setUsername(Username);
		payload.setPassword(Password);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		endpoints.CreateUser(payload);
		
		Response response=endpoints.GetUser(payload.getUsername());
		
		assertThat(response.getStatusCode(), is(200));

	}
	
	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void GetTest3(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

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
