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

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[contains(text(), '✕')]")
    private WebElement closeLoginPopupButton;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[3]/div/a[2]")
    private WebElement cartButton;
    
    @FindBy(xpath = "//img[@alt='Electronics']")
    private WebElement electronicsCategory;
    
    @FindBy(xpath = "//a[contains(text(),'Become a Seller')]")
    private WebElement becomeSellerLink;
    
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[1]/a/picture/img")
    private WebElement flipkartLogo;
    
    @FindBy(linkText = "Contact Us")
    private WebElement contactUsLink;

    @FindBy(linkText = "Careers")
    private WebElement career;
    
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[5]/div/div/div/div/a/img")
    WebElement threeDotButton;

    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[5]/div/div/div/ul/a[2]/li")
    WebElement customerCareOption;

    @FindBy(xpath = "//h1[@class='ogUXNW']")
    WebElement helpCenterPageHeader;
    
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[5]/div/div/div/ul/a[3]/li")
    private WebElement advertiseLink;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div/div[2]/div/p[1]")
    private WebElement loginToYourAccountMessage;
    
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[2]/div/div/div/div/a/span")  
    WebElement loginButtonSpan;
    
    @FindBy(xpath = "//div[contains(@class,'_1ruvv2')]")  
    WebElement userProfileIcon;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[2]/div[2]/div/div/div/ul/a[2]/li")  
    WebElement myProfileOption;
    
    @FindBy(xpath = "//*[@id='container']/div/div[1]/div/div/div/div/div[1]/div/div/div/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/a[4]/div/div/span/span")
    private WebElement flightBookingsLink;

    @FindBy(xpath = "//*[@id='container']/div/div[2]/div[1]/div/div[2]/div/div[1]/div/div/label[2]/div[2]/div")
    private WebElement roundTripOption;
    
 
    @FindBy(xpath = "//*[@aria-label='Terms Of Use']")
    WebElement termsOfUseLink;

    
    @FindBy(xpath = "//h2[@id='flipkart-terms-of-use']")
    WebElement termsOfUseHeading;
    
 
    @FindBy(xpath = "//a[@aria-label='Gift Cards']")
    WebElement giftCardsLink;

    
    @FindBy(xpath = "//h1[@class='_0FYq1K']")
    WebElement giftCardStoreHeading;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopup() {
        wait.until(ExpectedConditions.visibilityOf(closeLoginPopupButton));
        closeLoginPopupButton.click();
    }

    public void searchProduct(String product) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(product);
        searchButton.submit();
    }

    public boolean isCartVisible() {
        wait.until(ExpectedConditions.visibilityOf(cartButton));
        return cartButton.isDisplayed();
    }
    
    public void navigateToElectronicsCategory() {
        wait.until(ExpectedConditions.visibilityOf(electronicsCategory));
        electronicsCategory.click();
    }
    
    public void clickBecomeSeller() {
        wait.until(ExpectedConditions.visibilityOf(becomeSellerLink));
        becomeSellerLink.click();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public boolean isLogoDisplayed() {
        return flipkartLogo.isDisplayed();
    }
    
    public boolean isFooterLinkDisplayed(String linkText) {
        switch (linkText) {
            case "Contact Us":
                return contactUsLink.isDisplayed();
            case "Careers":
                return career.isDisplayed();
            default:
                return false;
        }
    }
    
    public String getSearchBoxPlaceholderText() {
        return searchBox.getAttribute("placeholder");
    }
    
    // Method to click on the three-dot button
    public void clickThreeDotButton() {
        threeDotButton.click();
    }

    // Method to click on the "24 x7 Customer care" option
    public void clickCustomerCareOption() {
        customerCareOption.click();
    }

    // Method to get the header text of the Help Center page
    public String getHelpCenterPageHeaderText() {
        return helpCenterPageHeader.getText();
    }
    
    public void clickAdvertiseLink() {
        advertiseLink.click();
    }

    public String getLoginToYourAccountMessage() {
        return loginToYourAccountMessage.getText();
    }
    
    public void openUserProfileMenu() {
        userProfileIcon.click();
    }

    // Method to click on the My Profile option
    public void clickMyProfileOption() {
        myProfileOption.click();
    }

    // Method to verify navigation to the My Profile page
    public boolean isMyProfilePageDisplayed() {
        return driver.getCurrentUrl().contains("account");
    }
    
    public void clickLoginButton(WebDriver driver) {
        // Wait for the Login button span to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonSpan));

        // Scroll to the Login button to ensure it's in view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);

        // Try clicking the parent div of the span (if the span itself is not clickable)
        try {
            loginButton.findElement(By.xpath("..")).click();  // Click the parent div of the span
        } catch (Exception e) {
            // If clicking the parent div doesn't work, use JavaScript click as a fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        }
    }
    
 // Method to click on Flight Bookings and verify Round Trip option visibility
    public boolean verifyRoundTripOption() {
        // Click on the Flight Bookings link
        flightBookingsLink.click();

        // Wait until the Round Trip option is visible
        wait.until(ExpectedConditions.visibilityOf(roundTripOption));
        
        // Return true if the Round Trip option is visible, otherwise false
        return roundTripOption.isDisplayed();
    }
    
    // Method to click on the Terms of Use link
    public void clickTermsOfUse() {
        termsOfUseLink.click();
    }

    // Method to verify the Terms of Use page heading
    public boolean isTermsOfUsePageDisplayed() {
        return termsOfUseHeading.isDisplayed() &&
               termsOfUseHeading.getText().equals("Flipkart Terms of Use");
    }
    
 // Method to click on the Gift Cards link
    public void clickGiftCards() {
        giftCardsLink.click();
    }

    // Method to verify the Gift Card Store page heading
    public boolean isGiftCardStorePageDisplayed() {
        return giftCardStoreHeading.isDisplayed() &&
               giftCardStoreHeading.getText().equals("Gift Card Store");
    }
    
}
