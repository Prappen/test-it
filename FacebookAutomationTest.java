package org.example;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FacebookAutomationTest {

    private static final Logger LOGGER = Logger.getLogger(FacebookAutomationTest.class.getName());

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // Create a ChromeDriver instance
        driver = new ChromeDriver(options);

        // Navigate to Facebook website
        driver.get("https://www.facebook.com");
    }

    public void acceptCookies() {
        try {
            WebElement acceptCookiesButton = driver.findElement(By.cssSelector("button[title='Tillåt endast nödvändiga cookies']"));
            acceptCookiesButton.click();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error accepting cookies: " + e.getMessage(), e);
        }
    }

    public void loginToFacebook() {
        try {
            // Enter login credentials and click login button
            WebElement emailField = driver.findElement(By.id("email"));
            emailField.sendKeys("Johanohrn9@hotmail.com");

            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("1293ohrn!!!");

            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();

            // Wait for login to complete
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error logging in to Facebook: " + e.getMessage(), e);
        }
    }

    public void makeFacebookSearch() {
        try {
            // find the search button element and click it
            WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div[1]/div/div/label[1]"));
            searchButton.click();

            WebElement searchInput = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div[1]/div/div/label/input"));
            searchInput.sendKeys("Julia Öhrn");
            searchInput.sendKeys(Keys.ENTER);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // print the page title to confirm that we are on the search results page
            LOGGER.log(Level.INFO, "Page title: " + driver.getTitle());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error performing Facebook search: " + e.getMessage(), e);
        }
    }

    @Test
    public void testFacebookSearch() {
        acceptCookies();
        loginToFacebook();
        makeFacebookSearch();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}