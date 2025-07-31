#### **Setup Steps for Playwright**

1. Navigate to your Project Root:  
   Open a terminal and cd into your project directory.  
2. Initialize Playwright:  
   Run the following command and follow the interactive prompts.  
   npm init playwright@latest

   * It will ask if you want to use TypeScript or JavaScript (choose TypeScript).  
   * It will ask where to put your tests (the default tests folder is fine).  
   * It will ask to add a GitHub Actions workflow (you can choose yes or no, I choose currently no).
   * Install Playwright browsers? (I recommend yes, because with every release, Playwright updates the versions of the browsers it supports) 
3. Review the Project Structure:  
   This creates:  
   * tests/: Where your test specs live.  
   * playwright.config.ts: The main configuration file.