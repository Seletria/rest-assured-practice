package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Locale.setDefault(Locale.US);
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com";
    }
}
