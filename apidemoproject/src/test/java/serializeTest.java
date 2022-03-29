import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("(+91) 983 893 3937");
		p.setPhone_number(DEFAULT_BODY_ROOT_PATH);
		p.setWebsite(DEFAULT_BODY_ROOT_PATH);
		p.setName(DEFAULT_BODY_ROOT_PATH);
		
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res= given().queryParam("key", "qaclick123")
		.body("")
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		String response= res.asString();
		System.out.println(response);

	}

}
