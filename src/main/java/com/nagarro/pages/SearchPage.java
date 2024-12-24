package com.nagarro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;

    // Locator for searchbox
    @FindBy(name = "q") 
    private WebElement searchBox;

    @FindBy(css = "button[type='submit']") 
    private WebElement searchButton;

    @FindBy(css = "div._1AtVbE") 
    private WebElement searchResults;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
    private WebElement firstSearchResult;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div/div[1]")
    private WebElement searchResultNotFound;

    @FindBy(xpath = "//*[@id=\"pincodeInputId\"]")
    private WebElement pincodeInput;

    @FindBy(xpath = "//span[@class=\"i40dM4\"]")
    private WebElement checkDeliveryButton;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[6]/div/div/div[2]/div[1]/ul/div/div")
    private WebElement deliverySuccessMessage;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[6]/div/div/div[2]/div")
    private WebElement invalidPincodeMessage;

    // Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }

    // Method to search for a product
    public void searchForProduct(String searchTerm) {
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        searchButton.submit();
    }

    // Method to verify if the search result is displayed
    public boolean isSearchResultDisplayed() {
        return firstSearchResult.isDisplayed();
    }

    // Method to verify if the search result not found message is displayed
    public boolean isSearchResultNotFound() {
        return searchResultNotFound.isDisplayed();
    }

    // Method to click on the first search result
    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    // Method to enter pincode and check delivery
    public void enterPincodeAndCheck(String pincode) {
        pincodeInput.clear();
        pincodeInput.sendKeys(pincode);
        checkDeliveryButton.click();
    }

    // Method to verify delivery success message
    public boolean isDeliverySuccessDisplayed() {
        return deliverySuccessMessage.isDisplayed();
    }

    // Method to verify invalid pincode message
    public boolean isInvalidPincodeMessageDisplayed() {
        return invalidPincodeMessage.isDisplayed();
    }
    
    public void clickCheck() {
    	checkDeliveryButton.click();
    }
    
}
