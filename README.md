# Flipkart Automation Tests

This project contains automation tests for the Flipkart website using Selenium, Java, TestNG, and Maven.

## Prerequisites
- Java JDK 8 or later
- Maven
- IntelliJ IDEA or Eclipse IDE (Optional, for code editing)

## Setup

### 1. Clone the repository:
git clone 

Install dependencies:
mvn clean install

Run the tests:
mvn test

###Test Data (Excel)
The test data is stored in the loginTestData.xlsx file located in the src/main/resources/testdata folder.

###Test Cases

###Test Case 1. Test Cart Button Visibility
1. Priority: 1
2. Groups: smoke, regression
3. Description: Verifies if the cart button is visible on the home page.
4. Steps:
5. Launch the browser.
6. Navigate to "https://www.flipkart.com/"
7. Check if the cart button is visible.
8. Expected Result: Cart button should be visible.

###Test Case 2. Test Navigation to Electronics Category
1. Priority: 2
2. Description: Verifies if the navigation to the Electronics category works correctly.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click on the Electronics category.
7. Verify the page title contains "electronics".
8. Expected Result: The page title should contain "electronics".

###Test Case 3. Test 'Become a Seller' Link
1. Priority: 3
2. Description: Verifies if the 'Become a Seller' link navigates to the correct page.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click on the 'Become a Seller' link.
7. Verify the page title contains "seller".
8. Expected Result: The page title should contain "seller".

###Test Case 4. Test Flipkart Logo Visibility
1. Priority: 4
2. Description: Verifies if the Flipkart logo is visible on the homepage.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Check if the Flipkart logo is displayed.
7. Expected Result: Flipkart logo should be visible.

###Test Case 5. Test Footer Links Visibility
1. Priority: 5
2. Description: Verifies if the footer links are visible.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Check if the "Contact Us" and "Careers" footer links are visible.
7. Expected Result: Both "Contact Us" and "Careers" links should be visible.

###Test Case 6. Test Add to Cart Functionality
1. Priority: 6
2. Description: Verifies if the Add to Cart functionality works correctly.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Search for a product.
7. Add the first product to the cart.
8. Verify if the product is added to the cart.
9. Expected Result: The product should be successfully added to the cart.

###Test Case 7.Test Search Box Placeholder Text
1. Priority: 7
2. Description: Verifies if the placeholder text in the search box is correct.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Check the placeholder text in the search box.
7. Expected Result: The placeholder text should match the expected value from the properties file.

###Test Case 8. Test Customer Care Link
1. Priority: 8
2. Description: Verifies if the "24 x7 Customer Care" link navigates correctly.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click the three-dot button and select "24 x7 Customer care".
7. Verify if the Help Center page loads with the expected header text.
8. Expected Result: The Help Center page should be displayed with the expected header.

###Test Case 9. Test Advertise Link
1. Priority: 9
2. Description: Verifies if the Advertise link navigates correctly to the login page.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click the three-dot button and select "Advertise".
7. Verify if the "Login to your account" page is displayed.
8. Expected Result: The "Login to your account" page should open successfully.

###Test Case 10. Test Round Trip Option Visibility (Flight Bookings)
1. Priority: 10
2. Description: Verifies if the Round Trip option is visible after clicking on Flight Bookings.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click on the Flight Bookings option.
7. Verify if the Round Trip option is visible.
8. Expected Result: Round Trip option should be visible after clicking on Flight Bookings.

###Test Case 11.Test Terms of Use Link
1. Priority: 11
2. Description: Verifies if the Terms of Use link navigates correctly.
3. Steps:
4. Launch the browser.
5. Navigate to "https://www.flipkart.com/"
6. Click on the Terms of Use link.
7. Verify if the Terms of Use page is displayed.
8. Expected Result: The Terms of Use page should be displayed correctly.

###Test Case 12: Gift Cards Link
1. Launch browser
2. Navigate to "https://www.flipkart.com/"
3. Click on the Gift Cards link
4. Verify if the Gift Card Store page is displayed correctly
5. Expected Result: The Gift Card Store page should be displayed correctly.

###Test Case 13: Valid Login Test
1. Launch browser
2. Navigate to the Flipkart login page
3. Click on the "Login" button
4. Enter a valid mobile number
5. Test Data: Mobile Number: 7505344789
6. Click the "Request OTP" button
7. Verify that the OTP message is displayed correctly
8. Expected Message: "Please enter the OTP sent to 7505344789" 

###Test Case 14: Invalid Login Test
1. Launch browser
2. Navigate to the Flipkart login page
3. Click on the "Login" button
4. Enter an invalid mobile number
5. Test Data: Invalid Input: abcd
6. Click the "Request OTP" button
7. Verify that the appropriate error message is displayed
8. Expected Message: "Please enter valid Email ID/Mobile number"

###Test Case 15: Create Account Test
1. Launch browser
2. Navigate to the Flipkart login page
3. Click on the "Login" button
4. Click on the "Create an Account" link
5. Verify that the text "Looks like you're new here!" is displayed

###Test Case16: Search for a Valid Product
1. Launch the browser.
2. Navigate to "https://www.flipkart.com/"
3. The search term (e.g., "Laptop") is read from the Excel file TestCases.xlsx
4. The test case executes only if the "Execution Required" column is marked as "Yes" for the SearchProduct  test case.
6. Enter the search term in the search field.
7. Click the "Search" button.
8. Verify that search results are displayed for the entered search term.
9. Confirm that the results are relevant to the search term (e.g., laptops are displayed).
10. Pass the test if search results are displayed correctly.

###Test Case 17: Search for an Invalid Product
1. Launch the browser
2. Navigate to the "https://www.flipkart.com/"
3. Read the invalid search term from the config.properties file
4. Enter the invalid search term in the search field
5. Click the "Search" button
6. Verify that no results are displayed for the invalid search term
7. Confirm that the message "No results found" or similar is shown
8. Pass the test if the correct message is displayed

###Test Case 18: Verify Delivery Availability for a Valid Pincode
1. Launch the browser
2. Navigate to the "https://www.flipkart.com/" 
3. Perform a product search 
4. Click on the first search result
5. Switch to the new tab that opens for the product
6. Wait for the pincode input field to be visible
7. Enter a valid pincode 
8. Click on the "Check" button to verify delivery availability
9. Verify that the delivery success message is displayed for the valid pincode
10. Pass the test if the delivery success message appears

###Test Case 19: Verify Delivery Message for an Invalid Pincode
1. Launch the browser
2. Navigate to the "https://www.flipkart.com/" 
3. Perform a product search 
4. Click on the first search result
5. Switch to the new tab that opens for the product
6. Wait for the pincode input field to be visible
7. Enter an invalid pincode 
8. Click on the "Check" button to verify delivery availability
9. Verify that the invalid pincode message is displayed 
10. Pass the test if the invalid pincode message appears

###Test Case 20: Grocery Page Test
1. Launch the browser
2. Navigate to the "https://www.flipkart.com/"
3. Click on the "Grocery" section
4. Enter a valid pincode 
5. Click on the "Current Location" button to check delivery availability
6. Verify that the message "Verify Delivery Pincode" is displayed
7. Pass the test if the pincode message matches the expected value


