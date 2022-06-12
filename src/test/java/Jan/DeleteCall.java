package Jan;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteCall {
	
	@Test
	public void deleteCall() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured.when()
		           .delete("posts/1");
		
		System.out.println(resp.body().asString());
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println(resp.time());
		System.out.println(resp.header("Etag"));
		Assert.assertEquals(resp.header("Etag"), "W/\"2-vyGp6PvFo4RvsFtPoIWeCReyIC8\"");
		//show only in master branch
		
		//this is comment
	
	}
	
	@Test
	public void deleteCall_01() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response resp = RestAssured.when()
		           .delete("posts/1");
		
		System.out.println(resp.body().asString());
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println(resp.time());
		System.out.println(resp.header("Etag"));
		Assert.assertEquals(resp.header("Etag"), "W/\"2-vyGp6PvFo4RvsFtPoIWeCReyIC8\"");
		//this commnet is added from master remote branch
	
	}
	
	

}
