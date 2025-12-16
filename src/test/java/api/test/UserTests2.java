package api.test;

//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.endpoints.userEndpoints2;
import api.payload.User;
import common.BaseClass;
import io.restassured.response.Response;

public class UserTests2 extends BaseClass
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
		Response response = userEndpoints2.createUser(userPayload);
		response.then()
		.log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		Response response = userEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {   

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.userStatus =1;

		Response response = userEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then()
		.log().all(); // .body().statusCode(200); we also wright validation here
		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking updated data 

		Response responseafterUpdate = userEndpoints2.readUser(this.userPayload.getUsername());
		responseafterUpdate.
		then().log().all();
		Assert.assertEquals(responseafterUpdate.getStatusCode(), 200);
        System.out.println("passed");

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {

		Response response = userEndpoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
         System.out.println("passed");
	}

}
