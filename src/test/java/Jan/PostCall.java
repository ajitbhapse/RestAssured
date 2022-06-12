package Jan;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostCall {
	
	
	@Test
	public void postMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured.given()
				   .body("{\r\n"
				   		+ "    \"title\": \"foo\",\r\n"
				   		+ "    \"body\": \"bar\",\r\n"
				   		+ "    \"userId\": \"1\"\r\n"
				   		+ "  }") 
				   .headers("Content-type","application/json")
				   .when()
				   .post("posts");
		System.out.println(resp.body().asString());
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 201);
		System.out.println(resp.time());
		System.out.println(resp.header("Etag"));
		Assert.assertEquals(resp.header("Etag"), "W/\"43-e0UvNeXth+6+06UFNnGIVUOlAcw\"");
	}

}
