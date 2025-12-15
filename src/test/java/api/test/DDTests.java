package api.test;

//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import common.BaseClass;
import io.restassured.response.Response;
public class DDTests extends BaseClass
{
//	public Logger logger;

	@Test(priority = 1, dataProvider = "Data",dataProviderClass = DataProviders.class)
	void testPostuser(String userId, String userName, String firstName, String lastName, String email, String password, String phone)
	{
//		logger=LogManager.getLogger(this.getClass());
//	    private static final Logger logger = LogManager.getLogger();

	    // Convert only the ID to integer
	    User userPayload = new User();
	    int id = Integer.parseInt(userId);   // this is fine because Excel has 1010, 1020, 1030
        userPayload.setId(id);
	    userPayload.setUsername(userName);
	    userPayload.setFirstName(firstName);
	    userPayload.setLastName(lastName);
	    userPayload.setEmail(email);
	    userPayload.setPassword(password);
	    userPayload.setPhone(phone);

	    Response response = userEndpoints.createUser(userPayload);
	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals("application/json", response.contentType());
		response.then().log().all();

	}


	@Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	void testDeleteUserByName(String userName)
	{

	    Response response = userEndpoints.deleteUser(userName);
	    Assert.assertEquals(response.getStatusCode(), 200, "User delete failed for: " + userName);

	}

}