package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsApiTest extends BaseTest {

    private void deleteProductAndExpectStatus(String productId, int expectedStatusCode) {
        given(adminAuthSpec)
                .pathParam("id", productId)
        .when()
                .delete("/products/{id}")
        .then()
                .statusCode(expectedStatusCode);
    }

    private void getProductAndExpectStatus(String productId, int expectedStatusCode) {
        given(adminAuthSpec)
                .pathParam("id", productId)
        .when()
                .get("/products/{id}")
        .then()
                .statusCode(expectedStatusCode);
    }


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

        given(authSpec)
                .pathParam("id", productId)
                .body(updateBody)
        .when()
                .put("/products/{id}")

        .then()
                .statusCode(200);

        given()
                .pathParam("id", productId)
        .when()
                .get("/products/{id}")
        .then()
                .body("name", equalTo("Updated Name"))
                .body("price", equalTo(99.99f));

    }

    @Test
    void createAndDeleteProduct_shouldRemoveProductPermanently(){
        Response productResponse = given().when().get("/products");
        String categoryId = productResponse.jsonPath().getString("data[0].category.id");
        String brandId = productResponse.jsonPath().getString("data[0].brand.id");
        String imageId = productResponse.jsonPath().getString("data[0].product_image.id");

        Map<String,Object> newProductBody = new HashMap<>();

        newProductBody.put("name", "Test Product For Deletion");
        newProductBody.put("description", "Created for delete test purposes");
        newProductBody.put("price", 99.99);
        newProductBody.put("category_id", categoryId);
        newProductBody.put("brand_id", brandId);
        newProductBody.put("product_image_id", imageId);
        newProductBody.put("is_location_offer", 0);
        newProductBody.put("is_rental", 0);
        newProductBody.put("co2_rating", "A");

        String newProductId =
        given(adminAuthSpec)
                .body(newProductBody)
        .when()
                .post("/products")
        .then()
                .statusCode(201)
                .extract().path("id");

        deleteProductAndExpectStatus(newProductId, 204);
        getProductAndExpectStatus(newProductId, 404);
        deleteProductAndExpectStatus(newProductId, 404);
    }

}
