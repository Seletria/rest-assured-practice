package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class ProductsApiTest extends BaseTest {

    @Test
    void getNonExistingProduct_shouldReturn404 () {

        given()
                .pathParam("id", "99999")
                .when()
                .get("/products/{id}")
                .then().statusCode(404);
    }
}
