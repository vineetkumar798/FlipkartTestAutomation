package com.nagarro.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;
    protected static ExtentReports extentReports;  
    protected ExtentTest test;
    protected Logger logger = Logger.getLogger(BaseTest.class);

    public BaseTest() {
        logger.info("Loading configuration properties...");
        properties = new Properties();
        try (FileInputStream configFile = new FileInputStream("src/main/resources/config/config.properties")) {
            properties.load(configFile);
            logger.info("Configuration loaded successfully.");
        } catch (IOException e) {
            logger.error("Error loading configuration file: ", e);
        }
    }

    @BeforeSuite
    public void setupExtentReports() {
        if (extentReports == null) {
            logger.info("Initializing Extent Reports...");
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(spark);
            logger.info("Extent Reports initialized.");
        }
    }

    @BeforeMethod
    public void initializeDriver() {
        // Initialize the WebDriver
        logger.info("Initializing WebDriver...");
        String browser = properties.getProperty("browser");
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                    logger.info("Running in headless mode.");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            default:
                logger.error("Invalid browser name specified in config.properties.");
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitWait"))));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(properties.getProperty("explicitWait"))));
        logger.info("WebDriver initialized successfully.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Tearing down after test...");
        try {
            if (test != null) { // Check if test is initialized
                if (result.getStatus() == ITestResult.FAILURE) {
                    logger.error("Test failed: " + result.getName());
                    test.log(com.aventstack.extentreports.Status.FAIL, "Test Failed: " + result.getName());
                    test.log(com.aventstack.extentreports.Status.FAIL, result.getThrowable());

                    String screenshotPath = takeScreenshot(result.getName());
                    if (new File(screenshotPath).exists()) {
                        test.addScreenCaptureFromPath(screenshotPath);
                    }
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(com.aventstack.extentreports.Status.PASS, "Test Passed: " + result.getName());
                    logger.info("Test passed: " + result.getName());
                }
            } else {
                logger.warn("Test was not initialized for method: " + result.getName());
            }
        } catch (Exception e) {
            logger.error("Error during tearDown: ", e);
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver closed.");
            }
        }
    }

    @AfterSuite
    public void tearDownExtentReports() {
        logger.info("Flushing Extent Reports...");
        if (extentReports != null) {
            extentReports.flush();
        }
        logger.info("Extent Reports flushed successfully.");
    }

    public String takeScreenshot(String screenshotName) {
        String path = "test-output/screenshots/" + screenshotName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("test-output/screenshots"));
            Files.copy(src.toPath(), Paths.get(path));
            logger.info("Screenshot saved at: " + path);
        } catch (IOException e) {
            logger.error("Failed to save screenshot to " + path, e);
        }
        return path;
    }
}
