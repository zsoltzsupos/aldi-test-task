import { test, expect } from '@playwright/test';

test.describe('Login Feature', () => {

  // This block runs before each test in this describe block  
  test.beforeEach(async ({ page }) => {  
    // Navigate to the login page before each test  
    // Currently I use a demo webshop
    await page.goto('https://practicesoftwaretesting.com/');
    await page.getByText('Sign in').click()
  });

  test('log in successfully with valid credentials', async ({ page }) => {  
    // 1\. Find the email input field by ID and type the username  
    await page.locator('#email').fill('Teszt123@gmail.com');

    // 2\. Find the password input field by ID and type the password  
    await page.locator('#password').fill('Teszt12345@');

    // 3\. Find the login button and click it  
    await page.getByRole('button', {name: 'Login'}).click();

    // 4\. Assert that the login was successful by checking the URL  
    await expect(page).toHaveURL(/.account/); 
  });

});