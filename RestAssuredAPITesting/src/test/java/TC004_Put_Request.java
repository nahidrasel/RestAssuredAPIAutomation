import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Request {

	@Test
	public void RegistrationSuccessful() 
	{
		//Specify Base URI
		RestAssured.baseURI="https://reqres.in";
		
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Request object
		
		//Request payload sending along with POST request
		JSONObject requestParams= new JSONObject();
		requestParams.put("name","nahid");
		
		
		httpRequest.header("Content-Type","application/json");
		
		//it will convert to json format attach data to the request
		httpRequest.body(requestParams.toJSONString());
		
		//Response object
		Response response= httpRequest.request(Method.PUT,"/api/users");
		
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is:"+responseBody);
		
		//Status code
		int statusCode= response.getStatusCode();
		System.out.println("Response code is :"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String successCode= response.jsonPath().get("SuccessCode");
		System.out.println("Success code is :"+successCode);
		//Assert.assertEquals(successCode, "createdAt");
		
			
	}
}

