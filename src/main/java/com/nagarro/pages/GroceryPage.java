package com.nagarro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class GroceryPage {

    WebDriver driver;

    // Element for navigating to the Grocery section
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/a[1]/div/div/span/span")
    WebElement grocerySection;

    // Element for entering the pincode
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div[1]/form/input")
    WebElement pincodeField;

    // Element for current location button
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div[1]/form/div/button")
    WebElement currentLocationButton;

    // Search box element
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
    WebElement searchBox;

    // Search icon button
    @FindBy(xpath = "//button[@class='MJG8Up']")
    WebElement searchIconButton;

    // Success message text for product search
    @FindBy(xpath = "//div[@class=\"b3YJls\"]")
    WebElement pincodeMessageText;
    
    @FindBy(xpath = "//div[@class=\"MmnNsC\"]") 
    WebElement bufferingSpinner;

    public GroceryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on the Grocery section
    public void clickGrocerySection() {
        grocerySection.click();
    }

    // Method to enter the pincode
    public void enterPincode(String pincode) {
    	pincodeField.clear();
        pincodeField.sendKeys(pincode);
    }

    // Method to click on current location button
    public void clickCurrentLocationButton() {
        currentLocationButton.click();
    }

    // Method to search for a product
    public void searchProduct(String product) {
        searchBox.sendKeys(product);
        searchIconButton.click();
    }

    // Method to get the success message
    public String getPincodeMessage() {
        return pincodeMessageText.getText();
    }
    
    public WebElement getBufferingSpinner() {
        return bufferingSpinner;
    }
}
