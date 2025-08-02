# Automated Test: Delete Task Feature (JUnit 5 + Selenium)

## Goal

Safely test the "Delete Task" feature by verifying that when a user clicks the **delete** button for a specific task, that task is actually removed from the page.

A good test should:
- Be **reliable**
- Not depend on other tests
- **Clean up after itself**
- **Set up its own data**

---

## 4-Step Test Strategy

### Step 1: Setup (Arrange)

- Automatically create a **new, unique task**.
- Use current timestamp to ensure uniqueness.
- Guarantees the test operates only on data it created.

### Step 2: Action (Act)

- Locate the task on the page.
- Find the corresponding **delete** button.
- Click the button.

### Step 3: Handle the Confirmation Pop-up

- Switch focus to the **alert popup**.
- Confirm deletion via `accept()`.

### Step 4: Verification (Assert)

- Attempt to find the deleted task again.
- Expect a `NoSuchElementException`.
- If the task **is found**, test **fails**.
- If exception is thrown, task was successfully deleted.

---

## Sample JUnit 5 + Selenium Test Code

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:4200/tasks"); // Replace with your app's URL
        // Add login code here if necessary
    }

    @Test
    void shouldDeleteTaskSuccessfully() {
        // --- STEP 1: SETUP - Create a unique task to delete ---
        String taskNameToDelete = "Task to be Deleted - " + System.currentTimeMillis();

        // Simulate task creation (ensure these IDs match your app)
        // driver.findElement(By.id("new-task-input")).sendKeys(taskNameToDelete);
        // driver.findElement(By.id("add-task-btn")).click();

        // --- STEP 2: ACTION - Find the task and click its delete button ---
        WebElement deleteButton = driver.findElement(By.xpath(
            "//div[contains(text(), '" + taskNameToDelete + "')]/following-sibling::button[@class='delete-btn']"
        ));
        deleteButton.click();

        // --- STEP 3: HANDLE CONFIRMATION ---
        Alert confirmationAlert = driver.switchTo().alert();
        confirmationAlert.accept();

        // --- STEP 4: VERIFICATION - Assert the task is gone ---
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.xpath("//div[contains(text(), '" + taskNameToDelete + "')]"));
        }, "The test should fail to find the task because it was deleted.");
    }
}