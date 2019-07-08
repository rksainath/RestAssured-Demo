package restAssured;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import Files.Resuables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetNameResults {
	
	@Test
	public void ResultsTest() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		Response response =given()
				.param("location", "-33.8670522,151.1957362")
				.param("radius", "1500")
				.param("type", "restaurant")
				.param("keyword", "cruise")
				.param("key", "AIzaSyB29u1pZcl7CgOcTCJglHjKS2VkdlgpdY4").log().all()

				.when().get("/maps/api/place/nearbysearch/json")

				.then().log().body().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath jp = Resuables.responseToJSON(response);
		int count = jp.get("results.size()");
		for(int i=0; i<count; i++) {
			System.out.println(jp.get("results["+i+"].name"));
		}
		
		

	}

}
