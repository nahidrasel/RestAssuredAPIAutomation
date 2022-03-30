import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("English-BD");
		p.setPhone_number("02267836789");
		p.setWebsite("https://rahulshetty.com");
		p.setName("Point chev");
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);	
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427392);
		p.setLocation(l);
		
		ResponseSpecification resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification req= new  RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req)
		.body(p);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		
		String responseString= response.asString();
		System.out.println(responseString);

	}

}
