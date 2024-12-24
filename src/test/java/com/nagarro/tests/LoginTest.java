package com.nagarro.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.base.BaseTest;
import com.nagarro.pages.LoginPage;
import com.nagarro.utilities.ExcelReader;

public class LoginTest extends BaseTest {

	@Test(description = "Verify OTP message is displayed for valid login")
	public void validLoginTest() throws Exception {
	    test = extentReports.createTest("Valid Login Test");

	    // Read test data from Excel
	    List<String[]> testData = ExcelReader.readSpecificExcel("src/main/resources/testdata/loginTestData.xlsx");
	    String mobileNumber = "";
	    String expectedMessage = "";

	    for (String[] data : testData) {
	        test.info("Data row: " + Arrays.toString(data)); 

	        if (data[0].equals("ValidLogin") && data.length >= 3) {
	            mobileNumber = data[1];
	            expectedMessage = data[2];
	            break;
	        }
	    }

	    Assert.assertFalse(mobileNumber.isEmpty(), "Mobile number not found in test data.");
	    Assert.assertFalse(expectedMessage.isEmpty(), "Expected message not found in test data.");

	    // Perform login actions
	    LoginPage loginPage = new LoginPage(driver);
	    driver.get(properties.getProperty("url"));
	    test.info("Navigated to Flipkart website.");

	    loginPage.clickLoginButton();
	    test.info("Clicked on Login button.");

	    loginPage.enterEmailOrMobile(mobileNumber);
	    test.info("Entered valid mobile number: " + mobileNumber);

	    loginPage.clickRequestOtpButton();
	    test.info("Clicked on Request OTP button.");

	    // Assert the OTP message
	    String actualMessage = loginPage.getOtpSentMessage();
	    test.info("Actual OTP message: " + actualMessage);

	    Assert.assertEquals(actualMessage, expectedMessage, "OTP message validation failed.");
	    test.pass("Valid Login Test passed.");
	}


    @Test(description = "Verify error message is displayed for invalid login")
    public void invalidLoginTest() throws Exception {
        test = extentReports.createTest("Invalid Login Test");

        // Read test data from Excel
        List<String[]> testData = ExcelReader.readSpecificExcel("src/main/resources/testdata/loginTestData.xlsx");
        String invalidInput = "";
        String expectedMessage = "";
        for (String[] data : testData) {
            if (data[0].equals("InvalidLogin")) {
                invalidInput = data[1];
                expectedMessage = data[2];
                break;
            }
        }

        // Perform login actions
        LoginPage loginPage = new LoginPage(driver);
        driver.get(properties.getProperty("url"));
        test.info("Navigated to Flipkart website.");

        loginPage.clickLoginButton();
        test.info("Clicked on Login button.");

        loginPage.enterEmailOrMobile(invalidInput);
        test.info("Entered invalid email/mobile number: " + invalidInput);

        loginPage.clickRequestOtpButton();
        test.info("Clicked on Request OTP button.");

        // Assert the error message
        String actualMessage = loginPage.getInvalidLoginMessage();
        test.info("Actual error message: " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "Error message validation failed.");
        test.pass("Invalid Login Test passed.");
    }
    
 
    @Test(description = "Verify user is redirected to Create Account page with the correct message")
    public void createAccountTest() throws Exception {
        test = extentReports.createTest("Create Account Test");

        // Perform login actions
        LoginPage loginPage = new LoginPage(driver);
        driver.get(properties.getProperty("url"));
        test.info("Navigated to Flipkart website.");

        loginPage.clickLoginButton();
        test.info("Clicked on Login button.");

        // Click on "Create an Account" link
        loginPage.clickCreateAccountLink();
        test.info("Clicked on 'Create an Account' link.");

        // Assert the "Looks like you're new here!" text
        String actualText = loginPage.getNewAccountText();
        test.info("Actual text: " + actualText);

        Assert.assertEquals(actualText, "Looks like you're new here!", "Text verification failed.");
        test.pass("Create Account Test passed.");
    }
}
