package testcases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTestCases {

	// Valid email and password
    @Test
    public void testCase1() {
        // Correct Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Correct request body
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");  
        request.put("password", "cityslicka");       

        // Send POST request
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
}
