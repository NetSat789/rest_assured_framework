package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import common.BaseClass;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class UserTests extends BaseClass
{

	Faker faker;
	User userPayload;

	//public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	//logs
		
		//logger=LogManager.getLogger(this.getClass());
//		
	}

	@Test(priority = 1)                                         
	public void testCreateUser() 
	{
		Response response = userEndpoints.createUser(userPayload);
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		Response response = userEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		response.then().statusCode(200)

	    // Exact value validations
	    .body("id", equalTo(userPayload.getId()))
	    .body("username", equalTo(userPayload.getUsername()))
	    .body("firstName", equalTo(userPayload.getFirstName()))
	    .body("lastName", equalTo(userPayload.getLastName()))
	    .body("email", equalTo(userPayload.getEmail()))
	    .body("phone", equalTo(userPayload.getPhone()))
         .time(lessThan(1000L))
		
	    // Data type validations
	    .body("id", instanceOf(Integer.class))
	    .body("username", instanceOf(String.class));

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.userStatus =1;

		Response response = userEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all(); // .body().statusCode(200); we also wright validation here
		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking updated data 

		Response responseafterUpdate = userEndpoints.readUser(this.userPayload.getUsername());
		responseafterUpdate.then().log().all();
		Assert.assertEquals(responseafterUpdate.getStatusCode(), 200);
		
		responseafterUpdate.then().statusCode(200)

		    .body("id", equalTo(userPayload.getId()))
		    .body("username", equalTo(userPayload.getUsername()))
		    .body("firstName", equalTo(userPayload.getFirstName()))
		    .body("lastName", equalTo(userPayload.getLastName()))
		    .body("email", equalTo(userPayload.getEmail()))
		    .body("phone", equalTo(userPayload.getPhone()))

		    // Data type validations
		    .body("id", instanceOf(Integer.class))
		    .body("username", instanceOf(String.class));

	}
	
	@Test(priority = 4) 
	public void testDeleteUserByName() {

		Response response = userEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
