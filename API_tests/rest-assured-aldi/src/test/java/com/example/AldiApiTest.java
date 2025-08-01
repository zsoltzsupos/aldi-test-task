import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * This class contains basic REST Assured tests for the automationexercise.com API.
 */
public class UserApiTests {

    /**
     * This method sets up the base URI for all tests in this class.
     * The @BeforeAll annotation means it runs once before any test methods.
     */
    @BeforeAll
    public static void setup() {
        // Set the base URL for all subsequent requests
        RestAssured.baseURI = "https://reqres.in/api";
    }
}

