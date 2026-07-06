package base;

import helper.RequestBodyHelper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static RequestSpecification authSpec;
    @BeforeAll
    static void setUp() {
        Locale.setDefault(Locale.US);
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com";

        String token = given()
                .contentType("application/json")
                .body(RequestBodyHelper.createLoginBody(
                        "customer@practicesoftwaretesting.com", "welcome01"
                ))
                .when().post("/users/login")
                .then()
                .statusCode(200)
                .extract().path("access_token");

        authSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setContentType("application/json")
                .build();
    }
}
