package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class Get {
	@Test
	public void Test() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("input", "Museum of Contemporary Art Australia").param("inputtype", "textquery")
				.param("fields", "photos,formatted_address,name,rating,opening_hours,geometry")
				.param("key", "AIzaSyB29u1pZcl7CgOcTCJglHjKS2VkdlgpdY4")

				.when().get("/maps/api/place/findplacefromtext/json")

				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("candidates[0].name", equalTo("Museum of Contemporary Art Australia")).and()
				.header("Server", equalTo("scaffolding on HTTPServer2"));

	}

}

//HTTPServer2
//https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyB29u1pZcl7CgOcTCJglHjKS2VkdlgpdY4