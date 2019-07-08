package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Resuables {
	public static XmlPath responseToXMl(Response response) {
//		Convert the reponse to string first
		
		String responseString = response.asString();
		
//		Convert the string by sending it to Xml Path
		
		XmlPath xmlpath = new XmlPath(responseString);
		
		return xmlpath;

	}
	
	public static JsonPath responseToJSON(Response response) {
//		Convert the reponse to string first
		
		String responseString = response.asString();
		
//		Convert the string by sending it to Json Path
		
		JsonPath jsonpath = new JsonPath(responseString);
		
		return jsonpath;

	}
}
