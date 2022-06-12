import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCall {
	
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";	
	}
	
	

	public String createSeesion() {
		
		Response response =  RestAssured.given()
					.body("{ \"username\": \"ajitbhapse2788\", \"password\": \"admin\" }")
					.header("content-type","application/json")
					.when()
					.post("http://localhost:8080/rest/auth/1/session");
		JsonPath jpath = new JsonPath(response.getBody().asString());
		return jpath.get("session.value");	
	}
	
	
	@Test
	public void getComments() {
		Response response = RestAssured.given()
				   .header("Content-Type","application/json")
		           .header("Cookie","JSESSIONID="+createSeesion())
		           .when()
		           .get("issue/RES-5/comment");
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jpath = new JsonPath(response.getBody().asString());
		System.out.println(response.getBody().asPrettyString());
		System.out.println(jpath.get("comments[0].body"));
		Assert.assertEquals(jpath.get("comments[0].body"), "this comment is added for understanding of API");	
	}
	
	@Test
	public void createComment() {
		Response response = RestAssured.given()
				
				   .header("Content-Type","application/json")
		           .header("Cookie","JSESSIONID="+createSeesion())
		           .body("{\r\n"
		           		+ "    \"body\": \"todays date is 20/01/2022/8:36\",\r\n"
		           		+ "    \"visibility\": {\r\n"
		           		+ "        \"type\": \"role\",\r\n"
		           		+ "        \"value\": \"Administrators\"\r\n"
		           		+ "    }\r\n"
		           		+ "}")
		           .when()
		           .post("issue/RES-5/comment");
		System.out.println(response.getStatusCode());
		
		JsonPath jpath = new JsonPath(response.getBody().asString());
		System.out.println(jpath.get("body"));
		
	}
	
	@Test
	public void deleteComment() {
		Response response = RestAssured.given()
				
				   .header("Content-Type","application/json")
		           .header("Cookie","JSESSIONID="+createSeesion())
		           .when()
		           .delete("issue/RES-5/comment/10003");
		System.out.println(response.getStatusCode());
		
	}
	
	@Test
	public void updateComment() {
		Response response = RestAssured.given()
				
				   .header("Content-Type","application/json")
		           .header("Cookie","JSESSIONID="+createSeesion())
		           .body("{\r\n"
		           		+ "    \"body\": \"updated on 20/01/2021\",\r\n"
		           		+ "    \"visibility\": {\r\n"
		           		+ "        \"type\": \"role\",\r\n"
		           		+ "        \"value\": \"Administrators\"\r\n"
		           		+ "    }\r\n"
		           		+ "}")
		           .when()
		           .put("issue/RES-5/comment/10002");
		
		System.out.println(response.getStatusCode());
	}
}
