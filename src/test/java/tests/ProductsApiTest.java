package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsApiTest extends BaseTest {

    @Test
    void getNonExistingProduct_shouldReturn404 () {

        given()
                .pathParam("id", "99999")
        .when()
                .get("/products/{id}")
        .then()
                .statusCode(404);
    }

    @Test
    void getProductsByCategory_shouldReturnFilteredProducts() {
        given()
                .queryParam("by_category", "01KVSYRW7GQSX627634VM6TQC7")
        .when()
                .get("/products")
        .then()
                .statusCode(200)
                .body("data.size()", greaterThan(0))
                .body("data.category.id", everyItem(equalTo("01KVSYRW7GQSX627634VM6TQC7")));
        ;
    }
}
