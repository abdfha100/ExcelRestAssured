package excelDriven;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HashMapDrivenTC01 {
	
	@Test
	public void tc01()
	{
		/*HashMap<String, Object> jsonMap1 = new HashMap<>();
		jsonMap1.put("accuracy", "51");
		jsonMap1.put("name", "RiverViewHouse");
		jsonMap1.put("phone_number", "(+91)8974563321");
		jsonMap1.put("address", "29, PT Streer, Motingar Bangalore2");
		jsonMap1.put("types", "[\"shoe park\",\"shop\"]");
		jsonMap1.put("website", "http://google.com");
		jsonMap1.put("language", "Hindi-IND");
		HashMap<String, Object> jsonMap2 = new HashMap<>();
		jsonMap2.put("lat", "38.383496");
		jsonMap2.put("lng", "33.427363");
		jsonMap2.put("location", jsonMap2); */
		
		HashMap<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("name", "RestAssuredBasics");
		jsonMap.put("isbn", "pqr");
		jsonMap.put("aisle", "8972");
		jsonMap.put("author", "Zaeem Md");
		
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
		String id = js.get("ID");
		System.out.println(id);
	}
}
