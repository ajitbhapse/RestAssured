package Jan;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCall {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
	}
	
	@Test
	public void getHttp() {
		
		Response response = RestAssured.given()
		           .headers("User-Agent","PostmanRuntime/7.29.0")
		           .when()
		           .get("posts/1");
		//System.out.println("response body :" +response.body().asPrettyString());
		
		JsonPath path = new JsonPath(response.body().asPrettyString());
		Assert.assertEquals(path.get("title"), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("status code :" + response.getStatusCode());
		Headers  heads = response.getHeaders();
//		
//		for (Header header : heads) {
//			System.out.println(header.getName() +" "+ header.getValue());
//		}
	}
	
	@Test
	public void postCall() {
		Response response = RestAssured.given()
		           .body("{\r\n"
		           		+ "    \"title\": \"foo\",\r\n"
		           		+ "    \"body\": \"bar\",\r\n"
		           		+ "    \"userId\": \"1\"\r\n"
		           		+ "  }")
		           .headers("Content-type","application/json")
		           .when()
		           .post("posts");
		
			JsonPath path = new JsonPath(response.getBody().asString());
		    String body = path.get("body");
		    Assert.assertEquals(body, "bar");
		    //on status code 
		    Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void putCall() {
		Response response = RestAssured.given()
		           .body("{\r\n"
		           		+ "    \"title\": \"foo\",\r\n"
		           		+ "    \"body\": \"updating from script\",\r\n"
		           		+ "    \"userId\": \"1\"\r\n"
		           		+ "  }")
		           .headers("Content-type","application/json")
		           .when()
		           .put("posts/1");
		JsonPath path = new JsonPath(response.getBody().asString());
		String body = path.get("body");
		Assert.assertEquals(body, "updating from script");
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void deleteCall() {
		Response response = RestAssured.when()
		           .delete("posts/1");
		System.out.println("body :"+response.getBody().asPrettyString());
		System.out.println("status code :"+response.getStatusCode());
		System.out.println("time :"+response.getTime());
		
		//this is comment added for testing
	}
	
	@Test
	public void filteringResource() {
		 Response response = RestAssured.when()
				 						.get("posts?userId=1");
		 System.out.println(response.getBody().asPrettyString());
		
	}
}
