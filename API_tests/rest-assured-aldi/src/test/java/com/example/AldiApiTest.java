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
        Task newTask = new Task("TASK3 REST-assured", "Complete the API testing assignment.", false);

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

    @Test
    @Order(2)
    @DisplayName("GET /tasks/{id} - Should retrieve an existing task")
    void testRetrieveTaskById() {
        given()
            .log().all()
            .pathParam("id", createdTaskId) // Use the ID from the created task

        .when()
            .get("/{id}") // Send GET request to /tasks/{id}

        .then()
            .log().all()
            // --- Expected Status Code ---
            // A successful retrieval should return 200 OK.
            .assertThat().statusCode(200)

            // --- Expected Response Body ---
            // The response should match the data of the task we created.
            .body("id", equalTo(createdTaskId))
            .body("title", equalTo("TASK3 REST-assured"));
    }

    @Test
    @Order(3)
    @DisplayName("PUT /tasks/{id} - Should update an existing task")
    void testUpdateTask() {
        // --- Test Data for Update ---
        Task updatedTask = new Task("Master REST-assured", "Completed assignment and created a full test suite.", true);

        given()
            .log().all()
            .contentType(ContentType.JSON)
            .pathParam("id", createdTaskId) // Identify which task to update
            .body(updatedTask) // Provide the updated task data

        .when()
            .put("/{id}") // Send PUT request to /tasks/{id}

        .then()
            .log().all()
            // --- Expected Status Code ---
            // A successful update should return 200 OK.
            .assertThat().statusCode(200)

            // --- Expected Response Body ---
            // The response should contain the fully updated resource.
            .body("id", equalTo(createdTaskId))
            .body("title", equalTo(updatedTask.getTitle()))
            .body("description", equalTo(updatedTask.getDescription()))
            .body("completed", equalTo(true));
    }

    @Test
    @Order(4)
    @DisplayName("DELETE /tasks/{id} - Should delete an existing task")
    void testDeleteTask() {
        // --- Delete the task ---
        given()
            .log().all()
            .pathParam("id", createdTaskId) // Identify which task to delete

        .when()
            .delete("/{id}") // Send DELETE request to /tasks/{id}

        .then()
            .log().all()
            // --- Expected Status Code ---
            // A successful deletion with no content to return should result in 204 No Content.
            .assertThat().statusCode(204);

        // --- Verification Step ---
        // To confirm deletion, try to GET the same task again and expect it to be gone.
        given()
            .log().all()
            .pathParam("id", createdTaskId)
        .when()
            .get("/{id}")
        .then()
            .log().all()
            // --- Expected Status Code ---
            // A request for a non-existent resource should return 404 Not Found.
            .assertThat().statusCode(404);
    }
}

