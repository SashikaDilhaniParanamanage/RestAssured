package testcases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTestCases {

    @Test
    public void testCase1() {
        // Correct Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Correct request body
        JSONObject request = new JSONObject();
        request.put("email", "eve.holt@reqres.in");  // Valid email from Reqres
        request.put("password", "cityslicka");       // Valid password from Reqres

        // Send POST request
        Response response =
            RestAssured.given()
                .contentType(ContentType.JSON)  // Set Content-Type
                .body(request.toJSONString())   // Attach request body
            .when()
                .post("/login")  // Correct API endpoint
            .then()
                .statusCode(200)  // Ensure 200 OK response
                .log().all()      // Log response
                .extract().response();

        // Validate response contains token
        Assert.assertNotNull(response.jsonPath().getString("token"), "Token is missing in response");
    }
}
