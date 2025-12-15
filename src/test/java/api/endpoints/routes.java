package api.endpoints;

/*
Swagger urls:
Create User(Post): https://petstore swagger.io/v2/user
Get User(Get):    https://petstore swagger.io/v2/user/{username} 
Update User(Put): https://petstore swagger.io/v2/user/{username}
Delete User(Delete): https://petstore swagger.io/v2/user/{username}


*/
public class routes
{

//	public static String base_url= "https://petstore swogger.io/v2/";
	public static String base_url = "https://petstore.swagger.io/v2";

	//user module 
	
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	
	//store module url
	
	public static String base_url_store = "https://petstore.swagger.io/v2/";

	public static String post_url_store= base_url_store+"/store";
	public static String get_url_store= base_url_store+"/store/order/{orderId}";
	public static String update_url_store= base_url_store+"/store/order/{orderId}";
	public static String delete_url_store= base_url_store+"store/order/{orderId}";
	
	//  /store/inventory
	//store/order
	
	
	
	//pet module url
}







