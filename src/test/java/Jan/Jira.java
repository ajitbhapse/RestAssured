package Jan;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Jira {

	public String getSeesionId() {
		RestAssured.baseURI = "http://localhost:8080/rest/auth/1/session";
		Response response = RestAssured.given().header("content-type", "application/json")
				.body("{ \"username\": \"admin\", \"password\": \"admin\" }").when().post();
		JsonPath path = new JsonPath(response.getBody().asPrettyString());
		String sessionId = path.get("session.value");
		System.out.println(sessionId);
		return sessionId;
	}

	@Test
	public void addCommnets() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/";
		Response response = RestAssured.given()
				.header("Cookie", "JSESSIONID=" + getSeesionId())
				.header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"this comment is added from eclipse automation script\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.when().post("API-10/comment");
		System.out.println("body :" + response.getBody().asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}
	
	@Test
	public void addCommnets_01() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/issue/";
		Response response = RestAssured.given()
				.header("Cookie", "JSESSIONID=" + getSeesionId())
				.header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"this comment is added from eclipse automation script\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.when().post("API-10/comment");
		System.out.println("body :" + response.getBody().asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}
}
