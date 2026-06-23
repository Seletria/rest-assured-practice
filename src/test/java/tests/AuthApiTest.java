package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AuthApiTest extends BaseTest {

    @Test
    void loginWithValidCredentials_shouldReturnToken() {
        Map<String, String> loginBody = new HashMap<>();

        loginBody.put("email", "customer@practicesoftwaretesting.com");
        loginBody.put("password", "welcome01");

        given()
                .contentType("application/json")
                .body(loginBody)
        .when()
                .post("/users/login")
        .then()
                .statusCode(200)
//                .log().body();
                .body("access_token", notNullValue())
                .body("token_type", equalTo("bearer"));
    }

    @Test
    void loginWithInvalidCredentials_shouldReturnError () {
        Map<String, String> loginBody = new HashMap<>();

        loginBody.put("email", "customer@practicesoftwaretesting.com");
        loginBody.put("password", "wrongpassword");

        given()
                .contentType("application/json")
                .body(loginBody)
                .when()
                .post("/users/login")
                .then().statusCode(401);
    }

}
