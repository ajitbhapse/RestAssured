package Jan;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutCall {
	
	@Test
	public void putMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured.given()
				.body("{\r\n"
						+ "    \"id\": \"1\",\r\n"
						+ "    \"title\": \"foo\",\r\n"
						+ "    \"body\": \"This is for JAN\",\r\n"
						+ "    \"userId\": \"1\"\r\n"
						+ "  }")
				.headers("Content-type","application/json")
				.when()
				.put("posts/1");
		System.out.println(resp.body().asString());
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println(resp.time());
		System.out.println(resp.header("Etag"));
		Assert.assertEquals(resp.header("Etag"), "W/\"4d-hrP0IGWNDMpzh4IimEolHb1IQOc\"");
	}

}
