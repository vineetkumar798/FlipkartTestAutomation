package com.nagarro.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nagarro.base.BaseTest;
import com.nagarro.pages.GroceryPage;

public class GroceryPageTest extends BaseTest {

    @Test
    public void testProductSearch() {
        // Create a new test in ExtentReports
        test = extentReports.createTest("Product Search Test");
        test.log(Status.INFO, "Starting product search test.");

        try {
            // Initialize the GroceryPage object to interact with the page
            GroceryPage groceryPage = new GroceryPage(driver);

            // Step 1: Navigate to the website
            driver.get(properties.getProperty("url"));
            test.log(Status.INFO, "Navigated to the website: " + properties.getProperty("url"));

            // Step 2: Click on the Grocery section
            groceryPage.clickGrocerySection();
            test.log(Status.INFO, "Clicked on the Grocery section.");

            // Step 3: Enter a valid pincode
            groceryPage.enterPincode(properties.getProperty("grocerypin"));
            groceryPage.clickCurrentLocationButton();
            test.log(Status.INFO, "Entered pincode: " + properties.getProperty("grocerypin") + " and clicked on Current Location button.");

            // Step 6: Get the Pincode message 
            String pincodeMessage = groceryPage.getPincodeMessage();
            test.log(Status.INFO, "Pincode message displayed: " + pincodeMessage);

            // Step 7: Validate the pincode message
            Assert.assertEquals(pincodeMessage, "Verify Delivery Pincode");
            test.log(Status.PASS, "Product search test passed.");

        } catch (Exception e) {
            test.log(Status.FAIL, "Product search test failed. " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
