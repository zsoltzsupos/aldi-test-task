#### **Test Cases**

**Test Case 1: Add a single product to the shopping list (Positive Scenario)**

* **Test Case ID:** TC-001  
* **Title:** Verify a logged-in user can add a single product to their shopping list.  
* **Priority:** High  
* **Preconditions:**  
  1. The user has a valid, registered account on new.aldi.us.  
  2. The user is logged into their account.  
  3. The user's shopping list is either empty or in a known state.  
* **Test Steps:**  
  1. Navigate to the ALDI US homepage.  
  2. Browse or search for a specific product (e.g., "Bananas").  
  3. On the product details page or product listing, locate and click the "Add" button (shopping cart icon on the left side).  
  4. Navigate to the "Cart" page.  
* **Expected Results:**  
  1. A success message should appear after clicking the "Add" button and the button change their color.  
  2. The selected product ("Bananas") should be visible in the shopping list.  
  3. The total item count on the shopping list should update correctly.

**Test Case 2: Attempt to add a product without being logged in (Negative Scenario)**

* **Test Case ID:** TC-002  
* **Title:** Verify a non-logged-in user is prompted to log in when attempting to add a product to a shopping list.  
* **Priority:** High  
* **Preconditions:**  
  1. The user is not logged into an account.  
  2. The user is browsing the new.aldi.us website.  
* **Test Steps:**  
  1. Navigate to the ALDI US homepage.  
  2. Browse or search for any product.  
  3. On the product details page or listing, click the "Add" button (shopping cart icon on the left side).  
* **Expected Results:**  
  1. The user should be redirected to the login/signup page.    
  3. The product should **not** be added to any list at this stage.
   
**Test Case 3: Add multiple products and verify the shopping list (Positive Scenario)**

* **Test Case ID:** TC-003  
* **Title:** Verify a logged-in user can add multiple, distinct products to their shopping list and the list updates correctly.  
* **Priority:** Medium  
* **Preconditions:**  
  1. The user has a valid, registered account on new.aldi.us.  
  2. The user is logged into their account.  
* **Test Steps:**  
  1. Navigate to a product category page (e.g., "Dairy & Eggs"), after click "Shop All Dairy & Eggs".  
  2. Click the "Add to Shopping List" button for the first product (e.g., "Cream Chesse").  
  3. Click the "Add to Shopping List" button for a second, different product (e.g., "Salted Butter Sticks").  
  4. Search for a third product in a different category (e.g., "Bread") and add it to the list.  
  5. Navigate to the "Shopping List" page.  
* **Expected Results:**  
  1. All three distinct products ("Cream Chesse", "Salted Butter Sticks", "Bread") should appear in the shopping list.  
  2. The quantity for each item should be "1".  
  3. The total item count displayed on the shopping list page should accurately reflect the number of unique items added (in this case, 3).

### **2\. Bug Reporting**

#### **Sample Bug Report**

* **Bug ID:** BUG-001  
* **Title:** Adding the same item to the shopping list multiple times creates duplicate entries instead of increasing quantity.  
* **Environment:**  
  * **URL:** new.aldi.us  
  * **Browser:** Google Chrome Version 126.0  
  * **OS:** Windows 11  
* **Severity:** Major  
* **Priority:** Medium  
* **Steps to Reproduce:**  
  1. Log into a valid user account.  
  2. Navigate to the product detail page for a specific item (e.g., "Pure Olive Oil").  
  3. Click the "Add to Shopping List" button once.  
  4. Click the "Add to Shopping List" button on the *same product* a second time.  
  5. Navigate to the "Shopping List" page to view its contents.  
* **Expected Result:** The shopping list should contain a single entry for "Pure Olive Oil" with a quantity of 2\.  
* **Actual Result:** The shopping list contains two separate, identical entries for "Pure Olive Oil", each with a quantity of 1\. This clutters the list and makes it difficult for users to manage quantities.  
* **Attachments:** duplicate\_list\_item\_bug.png (A screenshot showing the two identical line items in the shopping list).