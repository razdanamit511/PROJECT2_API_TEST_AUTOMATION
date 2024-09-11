package api.test.datadriven.user;

import org.testng.annotations.Test;
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
import api.utils.DataProviders;
import io.restassured.response.Response;

public class Delete_API_Tests {

	User payload;
	UserEndPoints endpoints;
	Faker faker;

	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void DeleteTest1(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
	//	System.out.println(OriginalUser.asPrettyString());
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		assertThat("Assertion 1", matcher1.matches(String.valueOf(DeletedUser.getStatusCode())));

	}

	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Regression"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void DeleteTest2(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		System.out.println(DeletedUser.asPrettyString());
		assertThat(DeletedUser.getStatusCode(), is(200));

	}
	
	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void DeleteTest3(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

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

		Response OriginalUser = endpoints.CreateUser(payload);

		Matcher<String> matcher1=matchesPattern("^20[0-9]$");
		
		Response DeletedUser=endpoints.DeleteUser(this.payload.getUsername());
		
		System.out.println(DeletedUser.asPrettyString());
		
		assertThat(DeletedUser.jsonPath().get("code"), is(200));
		assertThat(DeletedUser.jsonPath().get("type"), equalToIgnoringWhiteSpace("unknown"));
		assertThat(DeletedUser.jsonPath().get("message"), notNullValue());


	}
	
}
