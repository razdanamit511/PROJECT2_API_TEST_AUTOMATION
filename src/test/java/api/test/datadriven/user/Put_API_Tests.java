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

public class Put_API_Tests {

	User payload;
	UserEndPoints endpoints;
	Faker faker;

	@Test(description = "Validate that response code falls in 2XX category", priority = 1, groups = {"Regression"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PutTest1(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload = new User();
		endpoints = new UserEndPoints();
		faker = new Faker();

		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		Response response1 = endpoints.CreateUser(payload);

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());

		Response response2 = endpoints.UpdateUser(payload, payload.getUsername());

		Matcher<String> matcher1 = matchesPattern("^20[0-9]$");

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		assertThat("Assertion 1", matcher1.matches(String.valueOf(response2.getStatusCode())));

	}

	@Test(description = "Validate that response code = 200 ", priority = 2, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PutTest2(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload = new User();
		endpoints = new UserEndPoints();
		faker = new Faker();

		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		Response response1 = endpoints.CreateUser(payload);

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());

		Response response2 = endpoints.UpdateUser(payload, payload.getUsername());

		// Matcher<String> matcher1=matchesPattern("^20[0-9]$");

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		assertThat(response2.getStatusCode(), is(200));
	}

	@Test(description = "Validate that response contains response body as per the req ", priority = 3, groups = {"Functional"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PutTest3(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload = new User();
		endpoints = new UserEndPoints();
		faker = new Faker();

		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));
		Response response1 = endpoints.CreateUser(payload);

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());

		Response response2 = endpoints.UpdateUser(this.payload, this.payload.getUsername());

		System.out.println(
				"Response after update user :" + endpoints.GetUser(this.payload.getUsername()).asPrettyString());

		assertThat(response2.jsonPath().get("code"), is(200));
		assertThat(response2.jsonPath().get("type"), is("unknown"));
		assertThat(response2.jsonPath().get("message"), notNullValue());

	}

	@Test(description = "Validate that value of the user gets updated post successfull updation ", priority = 4, groups = {"Regression"}, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void PutTest4(String Id, String FirstName, String LastName, String PhoneNumber, String Email, String Username, String Password, String UserStatus) throws Exception {

		payload = new User();
		endpoints = new UserEndPoints();
		faker = new Faker();

		payload.setId(Integer.valueOf(Id));
		payload.setFirstName(FirstName);
		payload.setLastName(LastName);
		payload.setUsername(Username);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(PhoneNumber);
		payload.setUserStatus(Integer.valueOf(UserStatus));

		Response response1 = endpoints.CreateUser(payload);

		System.out.println("Response of Create user :" + endpoints.GetUser(payload.getUsername()).asPrettyString());

		Response OriginalUser = endpoints.GetUser(this.payload.getUsername());

		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setUsername(faker.name().username());
		payload.setPassword(faker.internet().password());

		Response response2 = endpoints.UpdateUser(this.payload, this.payload.getUsername());

		System.out.println(
				"Response after update user :" + endpoints.GetUser(this.payload.getUsername()).asPrettyString());

		Response UpdatedUser = endpoints.GetUser(this.payload.getUsername());

		assertThat(UpdatedUser.jsonPath().get("id"), is(this.payload.getId()));
		assertThat(UpdatedUser.jsonPath().get("username"), is(this.payload.getUsername()));
		assertThat(UpdatedUser.jsonPath().get("firstName"), is(this.payload.getFirstName()));
		assertThat(UpdatedUser.jsonPath().get("lastName"), is(this.payload.getLastName()));
		assertThat(UpdatedUser.jsonPath().get("password"), is(this.payload.getPassword()));
		assertThat(UpdatedUser.jsonPath().get("phone"), is(this.payload.getPhone()));
		assertThat(UpdatedUser.jsonPath().get("userStatus"), is(this.payload.getUserStatus()));

	}
}
