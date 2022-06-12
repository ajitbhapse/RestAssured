import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpMethods {

	@Test
	public void getCall() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
		
		Response resp = RestAssured.given()
				   .header("Connection","keep-alive")
				   .when()
				   .get("posts");
		
		System.out.println("Body : "+resp.body().asPrettyString());
		System.out.println("Status code :" +resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("Time :" +resp.getTime());
		System.out.println("Header : "+resp.header("x-content-type-options"));
	}

	@Test
	public void createResource() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured.given()
					.body("{\r\n"
							+ "    \"title\": \"foo\",\r\n"
							+ "    \"body\": \"bar\",\r\n"
							+ "    \"userId\": \"1\"\r\n"
							+ "  }")
					.header("Content-type", "application/json; charset=UTF-8")
					.when()
					.post("posts");
		
		System.out.println("Body : "+resp.body().asPrettyString());
		System.out.println("Status code :" +resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 201);
		System.out.println("Time :" +resp.getTime());
		System.out.println("Header : "+resp.header("Content-Type"));
	}
	
	@Test
	public void updateResource() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
		Response resp  = RestAssured.given()
		           .body("{\r\n"
		           		+ "    \"id\": \"1\",\r\n"
		           		+ "    \"title\": \"foo\",\r\n"
		           		+ "    \"body\": \"bar\",\r\n"
		           		+ "    \"userId\": \"1\"\r\n"
		           		+ "  }")
		           .header("Content-type", "application/json; charset=UTF-8")
		           .when()
		           .put("posts/1");
		
		System.out.println("Body : "+resp.body().asPrettyString());
		System.out.println("Status code :" +resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("Time :" +resp.getTime());
		System.out.println("Header : "+resp.header("Content-Type"));
	}
	
	@Test
	public void updateResourcePatch() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
		Response resp  = RestAssured.given()
		           .body("{\r\n"
		           		+ "    \"id\": \"1\",\r\n"
		           		+ "    \"title\": \"foo\",\r\n"
		           		+ "    \"body\": \"bar\",\r\n"
		           		+ "    \"userId\": \"1\"\r\n"
		           		+ "  }")
		           .header("Content-type", "application/json; charset=UTF-8")
		           .when()
		           .patch("posts/1");
		
		System.out.println("Body : "+resp.body().asPrettyString());
		System.out.println("Status code :" +resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("Time :" +resp.getTime());
		System.out.println("Header : "+resp.header("Content-Type"));
	}
	
	@Test
	public void deleteResource() {
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured
						.when()
						.delete("posts/1");
		
		System.out.println("Body : "+resp.body().asPrettyString());
		System.out.println("Status code :" +resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("Time :" +resp.getTime());
		System.out.println("Header : "+resp.header("Content-Type"));
	}
	
	public void finalme() {
		final int age = 0;
		System.out.println(age);
	}
	
	public static void main(String[] args) {
		HttpMethods HttpMethods = new HttpMethods();
		HttpMethods.finalme();
	}
}
