package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	
	public void addBook(String isbn, String aisle) 
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().
				header("Content-Type","application/json").
		body(payload.Addbook(isbn,aisle)).
		when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js= ReUsableMethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);
		
		
		
	}
	@Test(dataProvider="BooksData")
	public void deleteBook(String isbn, String aisle) 
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().
		body(payload.Addbook(isbn,aisle)).
		when()
		.post("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
	}
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		//array= collection of elements
		//multidimentional array=collection of arrays
		return new Object[][] { {"okljhd","8959"},{"dfghjf","3256"},{"lkjhui","4896"} };
	}
	
}
