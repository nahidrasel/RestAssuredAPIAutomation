import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;
public class Basics {
	
	public static void main(String[] args) {

		RestAssured.baseURI="https://rahulshettyacademy.com";
	
	//given = all input details
	//when = submit the api
	//then= validate the response
	
	String response= given().log().all().queryParams("key","qaclick123")
	.header("Content-Type","application/json")
	.body(payload.AddPlace()) .when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
	.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js= new JsonPath(response);//for parsing json
	
	String placeId=js.get("palce_id");
	
	System.out.println(placeId);
	}
}
	