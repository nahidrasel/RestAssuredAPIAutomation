import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_Request {

	@Test
	public void getUserDetails() 
	{
		//Specify Base URI
		RestAssured.baseURI="http://reqres.in";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		Response response= httprequest.request(Method.GET,"/api/users/");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is:"+responseBody);
		
		//Status code
		int statusCode= response.getStatusCode();
		System.out.println("Response code is :"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status Line Verification
		String statusLine= response.statusLine();
		System.out.println("Response Line is :"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		//Assert.assertEquals(statusLine, "");
		
		
		
	}
}
