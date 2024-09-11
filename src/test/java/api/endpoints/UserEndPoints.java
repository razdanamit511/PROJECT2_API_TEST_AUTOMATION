package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import api.payloads.User;
import api.utils.PropertyReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	PropertyReader prop;
	// This will have CRUD operation methods defined to be used in our API tests - Create, Retrieve, Update, Delete

	public UserEndPoints() throws Exception {

		prop = new PropertyReader();

	}

	// Method 1: Create
	public Response CreateUser(User payload) throws Exception {

		Response response = given()
				                   .contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .body(payload)
				           .when() .post(prop.getPostUrl()).andReturn();

		return response;

	}

	// Method 2: Update
	public Response UpdateUser(User payload, String username) throws Exception {

		Response response = given()
				                   .body(payload)
				                   .contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .pathParam("username", username)
				           .when()
				                   .put(prop.getPutUrl()).andReturn();

		return response;

	}

	// Method 3: Delete
	public Response DeleteUser(String username) throws Exception {

		Response response = given()
				                   .contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .pathParam("username", username)
				           .when()
				                   .delete(prop.getDeleteUrl()).andReturn();

		return response;

	}

	// Method 4: Get
	public Response GetUser(String username) throws Exception {

		Response response = given()
				                   .contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .pathParam("username", username)
				           .when()
				                   .get(prop.getGetUrl()).andReturn();

		return response;

	}
}
