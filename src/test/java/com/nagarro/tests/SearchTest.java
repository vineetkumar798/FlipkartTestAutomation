package com.nagarro.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nagarro.base.BaseTest;
import com.nagarro.pages.SearchPage;
import com.nagarro.utilities.ExcelReader;

public class SearchTest extends BaseTest {

    public String getPropertyValue(String key) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException {
        List<String[]> testData = ExcelReader.readExcel("src/main/resources/testdata/TestCases.xlsx");
        return testData.stream()
                .filter(data -> data[0].equalsIgnoreCase("SearchProduct") && data[1].equalsIgnoreCase("Yes"))
                .map(data -> new Object[]{data[2]})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "searchData", description = "Search for valid product")
    public void testSearchProduct(String searchTerm) {
        test = extentReports.createTest("Test Valid Product Search");
        test.log(Status.INFO, "Starting test with search term: " + searchTerm);

        SearchPage searchPage = new SearchPage(driver);
        driver.get(properties.getProperty("url"));

        searchPage.searchForProduct(searchTerm);
        Assert.assertTrue(searchPage.isSearchResultDisplayed(), "Search results were not displayed for: " + searchTerm);
        test.log(Status.PASS, "Valid product search test passed.");
    }

    @Test(description = "Search for invalid product")
    public void testInvalidSearchProduct() {
        test = extentReports.createTest("Test Invalid Product Search");
        test.log(Status.INFO, "Starting test with invalid search term.");

        String invalidSearchTerm = getPropertyValue("invalidSearchTerm");

        SearchPage searchPage = new SearchPage(driver);
        driver.get(properties.getProperty("url"));

        searchPage.searchForProduct(invalidSearchTerm);
        Assert.assertTrue(searchPage.isSearchResultNotFound(), "Unexpected search results were displayed for: " + invalidSearchTerm);
        test.log(Status.PASS, "Invalid product search test passed.");
    }
    
    @Test(description = "Verify delivery availability for a valid pincode")
    public void testDeliveryForValidPincode() {
        test = extentReports.createTest("Test Delivery with Valid Pincode");
        test.log(Status.INFO, "Starting test for valid pincode.");

        SearchPage searchPage = new SearchPage(driver);
        driver.get(properties.getProperty("url"));

        // Perform search
        searchPage.searchForProduct(getPropertyValue("searchterm"));
        Assert.assertTrue(searchPage.isSearchResultDisplayed(), "Search result not displayed.");
        test.log(Status.INFO, "Clicking on the first search result.");

        searchPage.clickFirstSearchResult();

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Wait for the pincode input field to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pincodeInputId\"]")));

        // Interact with the pincode input field
        pincodeInput.clear();
        pincodeInput.sendKeys(getPropertyValue("validpincode"));
        searchPage.clickCheck();

        // Wait for the delivery success message to be visible
        WebElement deliverySuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class=\"hVvnXm\"]")
        ));

        Assert.assertTrue(deliverySuccessMessage.isDisplayed(), "Delivery success message not displayed for valid pincode.");
        test.log(Status.PASS, "Delivery available for valid pincode test passed.");
    }


    @Test(description = "Verify delivery message for an invalid pincode")
    public void testDeliveryForInvalidPincode() {
        test = extentReports.createTest("Test Delivery with Invalid Pincode");
        test.log(Status.INFO, "Starting test for invalid pincode.");

        SearchPage searchPage = new SearchPage(driver);
        driver.get(properties.getProperty("url"));

        // Perform search
        searchPage.searchForProduct(getPropertyValue("searchterm"));
        Assert.assertTrue(searchPage.isSearchResultDisplayed(), "Search result not displayed.");
        test.log(Status.INFO, "Clicking on the first search result.");

        searchPage.clickFirstSearchResult();

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Wait for the pincode input field to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pincodeInputId\"]")));

        // Interact with the pincode input field
        pincodeInput.clear();
        pincodeInput.sendKeys(getPropertyValue("invalidpincode"));
        searchPage.clickCheck();

        // Wait for the invalid pincode message to be visible
        WebElement invalidPincodeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//div[@class=\"nyRpc8\"]"
        )));

        // Verify the invalid pincode message is displayed
        Assert.assertEquals(invalidPincodeMessage.getText(),"Not a valid pincode", "Invalid pincode message not displayed.");
        test.log(Status.PASS, "Invalid pincode test passed.");
    }


}
