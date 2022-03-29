
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/* Google Revoke this automation with Gmail
		 import org.openqa.selenium.By;
		import org.openqa.selenium.Keys;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.chrome.ChromeDriver;
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/nahid.mahmud/Downloads/chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("nahid.mahmud.qa");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Password);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		String url= driver.getCurrentUrl();
		*/
		
        //Go to this url >>https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
	    //type your email and password , then just copy the url you get after;
		
		//this is the url >> https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgu9ojUfr8K1VECCkoRE5LiDXrRN4nrVd59rp7HvSEn1LIAL94zfeDoau5hpZvOwQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWiy47XF-PR2ZHkGM04Y8r4XejeMqf1Dqkgltu5H8gXB7JAFyRzDy0plgg3hSf1xNQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialCode= url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		//parse the json and get the value
		
		JsonPath js= new JsonPath(accessTokenResponse);
		String accessToken= js.getString("access_token");
		
		
		GetCourse gc= given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> apiCourses=gc.getCourses().getApi();
		for(int i= 0; i<apiCourses.size();i++) {
			
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
				System.out.println(gc.getCourses().getMobile().get(1).getCourseTitle());
			}
		}
		//System.out.println(response);
	}

}
