package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

        Response productsResponse = given()
                .when()
                        .get("/products");

        String categoryId = productsResponse.jsonPath().getString("data[0].category.id");

        given()
                .queryParam("by_category", categoryId)
        .when()
                .get("/products")
        .then()
                .statusCode(200)
                .body("data.size()", greaterThan(0))
                .body("data.category.id", everyItem(equalTo(categoryId)));
        ;
    }

    @Test
    void updateProduct_shouldUpdateSuccessfully(){

        Response productResponse = given().when().get("/products");
        String productId = productResponse.jsonPath().getString("data[0].id");

        Map<String,Object> updateBody = new HashMap<>();
        updateBody.put("name", "Updated Name");
        updateBody.put("price", 99.99);

        given()
                .pathParam("id", productId)
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .put("/products/{id}")
        .then().log().all();

    }
}
