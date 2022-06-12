package Jan;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraApi {

	
	
	public String getSessionId() {
		RestAssured.baseURI ="http://localhost:8080/rest/";
		Response resp = RestAssured.given()
		           .body("{ \"username\": \"admin\", \"password\": \"admin\" }")
		           .headers("content-type","application/json")
		           .when()
		           .post("auth/1/session");
		JsonPath path = new JsonPath(resp.body().asString());
		String session = path.get("session.value");
		return session;
	}
	
	@Test
	public void addComment() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response resp = RestAssured.given()
				   .body("{\r\n"
				   		+ "    \"body\": \"Added from Postman - Part1\",\r\n"
				   		+ "    \"visibility\": {\r\n"
				   		+ "        \"type\": \"role\",\r\n"
				   		+ "        \"value\": \"Administrators\"\r\n"
				   		+ "    }\r\n"
				   		+ "}")
				   .headers("Content-type","application/json")
				   .headers("cookie","JSESSIONID="+getSessionId())
				   .when()
				   .post("issue/API-24/comment");
		JsonPath path = new JsonPath(resp.body().asString());
		Assert.assertEquals(path.get("body"), "Added from Postman - Part1");
		System.out.println(resp.body().asPrettyString());		
	}
	
	@Test
	public void updateComment() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response resp = RestAssured.given()
				   .body("{\r\n"
				   		+ "    \"body\": \"Updated from Eclipse\",\r\n"
				   		+ "    \"visibility\": {\r\n"
				   		+ "        \"type\": \"role\",\r\n"
				   		+ "        \"value\": \"Administrators\"\r\n"
				   		+ "    }\r\n"
				   		+ "}")
				   .headers("Content-type","application/json")
				   .headers("cookie","JSESSIONID="+getSessionId())
				   .when()
				   .put("issue/API-24/comment/10021");
	}
}
