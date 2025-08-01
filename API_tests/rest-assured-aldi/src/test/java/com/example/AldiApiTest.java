import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Use TestMethodOrder to run tests in a specific order for a workflow demonstration.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * This class contains basic REST Assured tests for the automationexercise.com API.
 */
public class UserApiTests {

    // A static variable to hold the ID of the task created, so it can be used across tests.
    private static int createdTaskId;

    @BeforeAll
    static void setup() {
        // Set the base URI for all API requests. This avoids repeating the URL in every test.
        RestAssured.baseURI = "https://api.example.com"; // Replace with the actual API base URL
        RestAssured.basePath = "/tasks";
    }

    @Test
    @Order(1)
    @DisplayName("POST /tasks - Should create a new task successfully")
    void testCreateNewTask() {
        // --- Test Data ---
        // Create a task object to send as the request body.
        Task newTask = new Task("Learn REST-assured", "Complete the API testing assignment.", false);

        // --- API Request and Validation ---
        createdTaskId = given()
            .log().all() // Log the request details for debugging
            .contentType(ContentType.JSON) // Set the content type of the request body
            .body(newTask) // The task object is automatically serialized to JSON

        .when()
            .post() // Send a POST request to the base path (/tasks)

        .then()
            .log().all() // Log the response details
            // --- Expected Status Code ---
            // A new resource creation should return 201 Created.
            .assertThat().statusCode(201)

            // --- Expected Response Body ---
            // The response should contain the details of the created task, including the new ID.
            .body("id", notNullValue()) // Assert that the ID is generated and not null
            .body("title", equalTo(newTask.getTitle()))
            .body("completed", equalTo(false))
            .extract().path("id"); // Extract the ID from the response for the next tests
    }
}

