package restAssured;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import Files.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PostXml {
	Properties properties = new Properties();


	@BeforeTest()
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				"/home/sainath/Documents/Rest-Assured/Rest-Demo/src/test/java/Files/data.properties");
		properties.load(fis);
		
	}

	@Test
	public void postTest() throws IOException {
		String XmlPayload = GenerateStringFromResource("/home/sainath/Documents/Rest-Assured/Rest-Demo/src/test/java/Files/XmlPayLoad.xml");
		RestAssured.baseURI = properties.getProperty("HOST");

		// Post request

		Response response = given()

				.queryParam("key", properties.getProperty("value"))

				.body(XmlPayload)

				.when().post(Resource.getAddXmlResource())
				.then().assertThat().statusCode(200).and()
				.contentType(ContentType.XML).and().extract().response();

	
		XmlPath xp = Resuables.responseToXMl(response);
		
		System.out.println(xp.get("response.place_id"));
		
	
	

	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
