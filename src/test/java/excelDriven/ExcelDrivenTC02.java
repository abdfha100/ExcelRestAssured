package excelDriven;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.DataDriven;

public class ExcelDrivenTC02 {
	
	@Test
	public void tc02() throws IOException
	{
		
		DataDriven d = new DataDriven();
		ArrayList<String> data = d.getData("RestAddBook", "RestAssured");
		
		HashMap<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("name", data.get(1));
		jsonMap.put("isbn", data.get(2));
		jsonMap.put("aisle", data.get(3));
		jsonMap.put("author", data.get(4));
		
		RestAssured.baseURI= "http://216.10.245.166";
		
		Response resp = given().
			header("Content-Type", "application/json").	
			body(jsonMap).
		when().
		 post("/Library/Addbook.php").
		then().
		 assertThat().statusCode(200).and().
		 contentType(ContentType.JSON).
		extract().
		 response();
		
		String res= resp.asString();
		JsonPath js = new JsonPath(res);
		System.out.println(res);
		String id = js.get("ID");
		System.out.println(id);
		System.out.println("updated the 1st time from workspace");
	}
}
