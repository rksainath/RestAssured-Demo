package restAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Files.Payload;
import Files.Resuables;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LibApi {

	@Test(enabled=false)
	public void getLibrary() {
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = given().param("AuthorName", "Rahul")							
				
							.when().get("/Library/GetBook.php")
							.then().assertThat().statusCode(200)
							.and().contentType(ContentType.JSON).and().extract().response();
		
		JsonPath jp = Resuables.responseToJSON(response);
		System.out.println(jp.get("isbn"));
	}
	
	@Test(dataProvider="LibData")
	public void postLibrary(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = given()
							.headers("Content-Type","application/json")
							
							.body(Payload.getLibraryLoad(isbn,aisle))
				
							.when().post("/Library/Addbook.php")
							.then().assertThat().statusCode(200)
							.and().contentType(ContentType.JSON).and().extract().response();
		
				
		JsonPath jp = Resuables.responseToJSON(response);
		System.out.println(jp.get("ID"));
	}
	
	@DataProvider(name="LibData")
	public Object[][] feeData() {
		return new Object[][] {{"ssdv","354"},{"sdfs","746"},{"ghje","399"}};
	}
}
