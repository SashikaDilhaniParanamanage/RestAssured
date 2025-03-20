package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NegativeTestCase {

	 @Test
	    public void testCase1() {
	        // Correct Base URI
	        RestAssured.baseURI = "https://reqres.in/api";

	        // Correct request body
	        JSONObject request = new JSONObject();
	
	        // Send POST request
	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON)  // Set Content-Type
	                .body(request.toJSONString())   // Attach request body
	            .when()
	                .post("/login")  // Correct API endpoint
	            .then()
	                .statusCode(400)  // Ensure 200 OK response
	                .log().all()      // Log response
	                .extract().response();

	        // Validate response contains token
	        Assert.assertTrue(response.asString().contains("error"), "Error message missing");
	    }
}
