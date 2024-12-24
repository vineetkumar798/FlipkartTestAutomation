package com.nagarro.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;

    // Locator for the first product and Add to Cart button
    @FindBy(xpath = "(//*[@class='KzDlHZ'])[1]") 
    private WebElement firstProduct;

    @FindBy(xpath = "//span[contains(text(),'Cart')]")
    private WebElement cartIcon;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to add the first product to the cart
    public void addFirstProductToCart() {
        // Click the first product
        firstProduct.click();

        // Store the original window handle
        String originalWindow = driver.getWindowHandle();

        // Wait for the new tab to open and switch to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); 

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Re-locate the Add to Cart button after switching context
        By addToCartButtonLocator = By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']");
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator));

        // Use JavaScriptExecutor to ensure the button is visible and interactable
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

        // Click the Add to Cart button
        addToCartButton.click();

        // Close the new tab and switch back to the original window
        driver.close();
        driver.switchTo().window(originalWindow);
    }


    // Method to verify if the product was added to the cart
    public boolean isProductAddedToCart() {
        return cartIcon.isDisplayed();
    }
}
