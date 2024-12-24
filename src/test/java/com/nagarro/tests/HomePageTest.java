package com.nagarro.tests;

import com.aventstack.extentreports.Status;
import com.nagarro.base.BaseTest;
import com.nagarro.pages.HomePage;
import com.nagarro.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify the cart button is visible on the home page")
    public void testCartButtonVisibility() {
        test = extentReports.createTest("Test Cart Button Visibility");
        test.log(Status.INFO, "Starting test for cart button visibility.");

        driver.get(properties.getProperty("url"));
        HomePage homePage = new HomePage(driver, wait);
        Assert.assertTrue(homePage.isCartVisible(), "Cart button is not visible on the home page.");
        test.log(Status.PASS, "Cart button visibility test passed.");
    }

    @Test(priority = 2)
    public void testNavigateToElectronicsCategory() {
        test = extentReports.createTest("Test Navigation to Electronics Category");
        test.log(Status.INFO, "Starting test for electronics category navigation.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));
        homePage.navigateToElectronicsCategory();
        Assert.assertTrue(homePage.getPageTitle().toLowerCase().contains("electronics"), "Navigation to electronics category failed.");
        test.log(Status.PASS, "Navigation to electronics category test passed.");
    }

    @Test(priority = 3)
    public void testBecomeSellerLink() {
        test = extentReports.createTest("Test Become Seller Link");
        test.log(Status.INFO, "Starting test for 'Become a Seller' link.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));
        homePage.clickBecomeSeller();
        Assert.assertTrue(homePage.getPageTitle().toLowerCase().contains("seller"), "'Become a Seller' page was not displayed.");
        test.log(Status.PASS, "'Become a Seller' link test passed.");
    }

    @Test(priority = 4)
    public void testFlipkartLogoVisibility() {
        test = extentReports.createTest("Test Flipkart Logo Visibility");
        test.log(Status.INFO, "Starting test for Flipkart logo visibility.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));
        Assert.assertTrue(homePage.isLogoDisplayed(), "Flipkart logo is not visible on the homepage.");
        test.log(Status.PASS, "Flipkart logo visibility test passed.");
    }


    @Test(priority = 5)
    public void testFooterLinksVisibility() {
        test = extentReports.createTest("Test Footer Links Visibility");
        test.log(Status.INFO, "Starting test for footer links visibility.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));
        Assert.assertTrue(homePage.isFooterLinkDisplayed("Contact Us"), "Contact Us link is not visible.");
        Assert.assertTrue(homePage.isFooterLinkDisplayed("Careers"), "Careers link is not visible.");
        test.log(Status.PASS, "Footer links visibility test passed.");
    }

    @Test(priority = 6)
    public void testAddToCartFunctionality() {
        test = extentReports.createTest("Test Add to Cart Functionality");
        test.log(Status.INFO, "Starting test for Add to Cart functionality.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url")); 
        homePage.searchProduct(properties.getProperty("searchterm")); 

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.addFirstProductToCart();

        Assert.assertTrue(searchResultsPage.isProductAddedToCart(), "Product was not added to the cart.");
        test.log(Status.PASS, "Add to Cart functionality test passed.");
    }
    
    @Test(priority = 7, description = "Verify the search box has the correct placeholder text")
    public void testSearchBoxPlaceholderText() {
        test = extentReports.createTest("Test Search Box Placeholder Text");
        test.log(Status.INFO, "Starting test for search box placeholder text.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));

        // Verify that the placeholder text in the search box is as expected
        String placeholderText = homePage.getSearchBoxPlaceholderText();
        Assert.assertEquals(placeholderText, properties.getProperty("placeholderText"), "Search box placeholder text is incorrect.");
        test.log(Status.PASS, "Search box placeholder text test passed.");
    }
    
    @Test(priority = 8, description = "Verify the functionality of 24 x7 Customer care link")
    public void testCustomerCareLink() {
        test = extentReports.createTest("Test Customer Care Link");
        test.log(Status.INFO, "Starting test for Customer Care link functionality.");

        // Initialize HomePage object
        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url"));

        // Click on the three-dot button
        homePage.clickThreeDotButton();
        test.log(Status.INFO, "Clicked on the three-dot button.");

        // Click on the "24 x7 Customer care" option
        homePage.clickCustomerCareOption();
        test.log(Status.INFO, "Clicked on 24x7 Customer care option.");

        // Read expected header text and error message from config.properties
        String expectedHeaderText = properties.getProperty("customerCarePageHeader");
        String errorMessage = properties.getProperty("customerCareErrorMessage");

        // Verify the Help Center page has loaded by checking the header text
        String headerText = homePage.getHelpCenterPageHeaderText();
        Assert.assertEquals(headerText, expectedHeaderText, errorMessage);

        test.log(Status.PASS, "Customer Care link functionality test passed.");
    }
    
    @Test(priority = 9, description = "Verify the Advertise link functionality")
    public void testAdvertiseLink() {
        test = extentReports.createTest("Test Advertise Link Functionality");
        test.log(Status.INFO, "Starting test for Advertise link functionality.");

        // Initialize HomePage object
        HomePage homePage = new HomePage(driver,wait);
        driver.get(properties.getProperty("url"));

        // Click on the three-dot button
        homePage.clickThreeDotButton();
        test.log(Status.INFO, "Clicked on the three-dot button.");

        // Click on the "Advertise" link
        homePage.clickAdvertiseLink();
        test.log(Status.INFO, "Clicked on 'Advertise' link.");

        // Assert that the page has navigated to the correct page (Login page)
        String loginMessage = homePage.getLoginToYourAccountMessage();
        Assert.assertTrue(loginMessage.contains("Login to your account"), "Advertise page did not open as expected.");
        test.log(Status.PASS, "Advertise link functionality test passed.");
    }
    
    
    @Test(priority = 10, description = "Verify Round Trip option visibility after clicking on Flight Bookings")
    public void testRoundTripOption() {
        test = extentReports.createTest("Test Round Trip Option Visibility");
        test.log(Status.INFO, "Starting test for Round Trip option visibility.");

        HomePage homePage = new HomePage(driver, wait);
        driver.get(properties.getProperty("url")); 

        boolean isRoundTripVisible = homePage.verifyRoundTripOption();
        Assert.assertTrue(isRoundTripVisible, "Round Trip option is not visible after clicking on Flight Bookings.");
        
        test.log(Status.PASS, "Round Trip option visibility test passed.");
    }
    
    
        @Test(priority = 11, description = "Verify the Terms of Use link and its navigation")
        public void testTermsOfUseLink() {
            test = extentReports.createTest("Test Terms of Use Link Navigation");
            test.log(Status.INFO, "Starting test for Terms of Use link.");

            // Initialize HomePage object
            HomePage homePage = new HomePage(driver, wait);

            driver.get(properties.getProperty("url"));  

            // Click on the Terms of Use link
            homePage.clickTermsOfUse();
            test.log(Status.INFO, "Clicked on the Terms of Use link.");

            // Verify that the Terms of Use page is displayed
            Assert.assertTrue(homePage.isTermsOfUsePageDisplayed(), "Terms of Use page was not displayed.");
            test.log(Status.PASS, "Terms of Use link navigation test passed.");
        }
        
        @Test(priority = 12, description = "Verify the Gift Cards link and its navigation")
        public void testGiftCardsLink() {
            test = extentReports.createTest("Test Gift Cards Link Navigation");
            test.log(Status.INFO, "Starting test for Gift Cards link.");

            // Initialize HomePage object
            HomePage homePage = new HomePage(driver,wait);

            driver.get(properties.getProperty("url"));  

            // Click on the Gift Cards link
            homePage.clickGiftCards();
            test.log(Status.INFO, "Clicked on the Gift Cards link.");

            // Verify that the Gift Card Store page is displayed
            Assert.assertTrue(homePage.isGiftCardStorePageDisplayed(), "Gift Card Store page was not displayed.");
            test.log(Status.PASS, "Gift Cards link navigation test passed.");
        }




}
