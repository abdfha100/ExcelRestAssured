package excelDriven;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class NormalDrivenTC03 {
	
	@Test
	public void tc03()
	{
		String b ="{\r\n" + 
				"\"name\":\"Soap UI\",\r\n" + 
				"\"isbn\":\"stu\",\r\n" + 
				"\"aisle\":\"22001\",\r\n" + 
				"\"author\":\"Nus\"\r\n" + 
				"}";
		
		RestAssured.baseURI= "http://216.10.245.166";
		
		Response resp = given().
			header("Content-Type", "application/json").	
			body(b).
		when().
		 post("/Library/Addbook.php").
		then().
		 assertThat().statusCode(200).and().
		 contentType(ContentType.JSON).
		extract().
		 response();
		
		String res= resp.asString();
		JsonPath js = new JsonPath(res);
		String id = js.get("ID");
		System.out.println(id);
	}
}
