package com.nagarro.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Locator for loginbutton
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[2]/div/div/div/div/a/span")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div/form/div[1]/input")
    private WebElement emailMobileField;

    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div/form/div[3]/button")
    private WebElement requestOtpButton;

    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div/div/div[1]")
    private WebElement otpSentMessage;

    // New Account link
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div/form/div[4]/a")
    private WebElement createAccountLink;

    // New Account Text
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[1]/span/span")
    private WebElement newAccountText;

    // Invalid Login Error Message
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div/form/div[1]/span[2]/span")
    private WebElement invalidLoginMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods for login functionality
    public void clickLoginButton() {
        if (loginButton != null) {
            // Use JavaScript Executor to click on the login button
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginButton);
        } else {
            throw new RuntimeException("Login button is not found on the page");
        }
    }

    public void enterEmailOrMobile(String emailOrMobile) {
        if (emailMobileField != null) {
            emailMobileField.clear();
            emailMobileField.sendKeys(emailOrMobile);
        } else {
            throw new RuntimeException("Email/Mobile input field is not found on the page");
        }
    }

    public void clickRequestOtpButton() {
        if (requestOtpButton != null) {
            requestOtpButton.click();
        } else {
            throw new RuntimeException("Request OTP button is not found on the page");
        }
    }

    public String getOtpSentMessage() {
        if (otpSentMessage != null) {
            return otpSentMessage.getText();
        } else {
            throw new RuntimeException("OTP sent message element is not found on the page");
        }
    }

    // Method to click on "Create an Account" link
    public void clickCreateAccountLink() {
        if (createAccountLink != null) {
            createAccountLink.click();
        } else {
            throw new RuntimeException("Create Account link is not found on the page");
        }
    }

    // Method to get the "Looks like you're new here!" text
    public String getNewAccountText() {
        if (newAccountText != null) {
            return newAccountText.getText();
        } else {
            throw new RuntimeException("New account text element is not found on the page");
        }
    }

    // Method to get the Invalid Login error message
    public String getInvalidLoginMessage() {
        if (invalidLoginMessage != null) {
            return invalidLoginMessage.getText();
        } else {
            throw new RuntimeException("Invalid login message element is not found on the page");
        }
    }
}
