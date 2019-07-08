package restAssured;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import Files.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PostAndDelete {
	Properties properties = new Properties();

	@BeforeTest()
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				"/home/sainath/Documents/Rest-Assured/Rest-Demo/src/test/java/Files/data.properties");
		properties.load(fis);
	}

	@Test
	public void postTest() {
		RestAssured.baseURI = properties.getProperty("HOST");

		// Post request

		Response response = given()

				.queryParam("key", properties.getProperty("value"))

				.body(Payload.getRequestLoad())

				.when().post(Resource.getAddResource()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"))

				// Grab the response as string and convert it to JSON to parse and get the
				// placeId value

				.extract().response();

		String responseString = response.asString();
		JsonPath jp = new JsonPath(responseString);
		String placeId = jp.get("place_id");
		System.out.println(placeId);

		// Using the place id value delete the newly posted request

		given().queryParam("key", properties.getProperty("value")).body("{	\"place_id\":" + " \"" + placeId + "\"} ")
				.when().post(Resource.getDelResource()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}

}
