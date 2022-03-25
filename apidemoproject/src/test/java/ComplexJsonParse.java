import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		
	JsonPath js= new JsonPath(payload.CoursePrice());
	
	//print No of courses returned by API
	int count=js.getInt("courses.size()");
	System.out.println(count);
	
	//print purchase amount
	int totalAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
		
	//print the title of first course
	String titleFirstCourse= js.get("courses[2].title");
	System.out.println(titleFirstCourse);
	
	//print all courses title and their prices
	
	for(int i=0;i<count;i++) {
		String courseTitles= js.get("courses["+i+"].title");
		int price = js.get("courses["+i+"].price");
		
		System.out.println(courseTitles);
		System.out.println(price);
	}
	
	}
}