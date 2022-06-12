import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPI {

	
	public String getSeesionId() {
		RestAssured.baseURI = "http://localhost:8080/";
		Response resp = RestAssured.given().header("content-type", "application/json")
				.body("{ \"username\": \"ajitbhapse2788\", \"password\": \"admin123\" }").when()
				.post("rest/auth/1/session");

		JsonPath path = new JsonPath(resp.asPrettyString());
		String sessionId = path.get("session.value");
		return sessionId;
	}
	
	
	@Test
	public void getComment() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response resp = RestAssured.given()
								   .header("Cookie", "JSESSIONID=" + getSeesionId())
								   .when()
								   .get("issue/DEC-3/comment");
		JsonPath path = new JsonPath(resp.asPrettyString());
		String comment = path.get("comments[0].body");
		System.out.println(comment);
		String authorEmail = path.get("comments[0].author.emailAddress");
		System.out.println(authorEmail);
		Assert.assertEquals(comment, "This comment is added from postman");
		Assert.assertEquals(authorEmail, "ajitbhapse@gmail.com");
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test
	public void addComment() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response resp = RestAssured.given()
								   .header("Cookie", "JSESSIONID=" + getSeesionId())
								   .header("Content-Type","application/json")
								   
								   .when()
								   .delete("issue/DEC-3/comment/10104");
	}
}
