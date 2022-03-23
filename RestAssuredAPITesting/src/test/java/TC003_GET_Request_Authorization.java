import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request_Authorization {
	@Test
	public void AuthenticationTest() 
	{
		//Specify Base URI
		RestAssured.baseURI="https://restful-booker.herokuapp.com/auth";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authscheme= new PreemptiveBasicAuthScheme();
		authscheme.setUserName("admin");
		authscheme.setPassword("password123");
		
		//Authentication
		RestAssured.authentication=authscheme;
		
		
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		Response response= httpRequest.request(Method.POST,"/");
				
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is:"+responseBody);
		
		//Status code
		int statusCode= response.getStatusCode();
		System.out.println("Response code is :"+statusCode);
		Assert.assertEquals(statusCode, 200);	
			
	}
}
