package tests;

import base.BaseTest;
import helper.RequestBodyHelper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AuthApiTest extends BaseTest {

    @Test
    void loginWithValidCredentials_shouldReturnToken() {

        given()
                .contentType("application/json")
                .body(RequestBodyHelper.createLoginBody("customer@practicesoftwaretesting.com",
                        "welcome01"))
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

        given()
                .contentType("application/json")
                .body(RequestBodyHelper.createLoginBody("customer@practicesoftwaretesting.com",
                        "wrongpassword"))
                .when()
                .post("/users/login")
                .then().statusCode(401);
    }

}
