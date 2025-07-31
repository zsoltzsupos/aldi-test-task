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