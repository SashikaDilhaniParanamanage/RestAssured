package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NegativeTestCase {

	//Missing email and password
	 @Test
	    public void testCase1() {
	      
	        RestAssured.baseURI = "https://reqres.in/api";
	        
	        JSONObject request = new JSONObject();
	
	        
	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON) 
	                .body(request.toJSONString())   
	            .when()
	                .post("/login")  
	            .then()
	                .statusCode(400) 
	                .log().all()     
	                .extract().response();

	        
	        Assert.assertTrue(response.asString().contains("error"), "Error message missing");
	    }
	 
	 // Invalid Email
	    @Test
	    public void testInvalidEmail() {
	        JSONObject request = new JSONObject();
	        request.put("email", "invalid@gmail.com"); 
	        request.put("password", "cityslicka");    

	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(request.toJSONString())
	            .when()
	                .post("/login")
	            .then()
	                .statusCode(400) 
	                .log().all()
	                .extract().response();

	        Assert.assertTrue(response.asString().contains("error"), "Error message missing");
	    }

	    // Invalid Password
	    @Test
	    public void testInvalidPassword() {
	        JSONObject request = new JSONObject();
	        request.put("email", "eve.holt@reqres.in"); 
	        request.put("password", "wrongpassword");   

	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(request.toJSONString())
	            .when()
	                .post("/login")
	            .then()
	                .statusCode(200)
	                .log().all()
	                .extract().response();

	        Assert.assertNotNull(response.jsonPath().getString("token"), "Token is missing in response");

	    }

	    // Missing Email
	    @Test
	    public void testMissingEmail() {
	        JSONObject request = new JSONObject();
	        request.put("password", "cityslicka");

	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(request.toJSONString())
	            .when()
	                .post("/login")
	            .then()
	                .statusCode(400)
	                .log().all()
	                .extract().response();

	        Assert.assertTrue(response.asString().contains("error"), "Error message missing");
	    }

	    // Missing Password
	    @Test
	    public void testMissingPassword() {
	        JSONObject request = new JSONObject();
	        request.put("email", "eve.holt@reqres.in");

	        Response response =
	            RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(request.toJSONString())
	            .when()
	                .post("/login")
	            .then()
	                .statusCode(400)
	                .log().all()
	                .extract().response();

	        Assert.assertTrue(response.asString().contains("error"), "Error message missing");
	    }

}
