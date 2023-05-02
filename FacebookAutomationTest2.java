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

import java.util.concurrent.TimeUnit;

public class FacebookAutomationTest2 {

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
            System.out.println("No accept cookies button found.");
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
    public void makeFacebookpost() {
try{
        WebElement postknapp = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div/div[1]/div" +
                "[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/div/div[1]/div/div[1]"));
        postknapp.click();
        WebElement skriv = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[" +
                "1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/p"));
        skriv.sendKeys("Test1");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement post = driver.findElement(By.xpath("//span[text()='Publicera']"));
        post.click();

        // Wait for the post to be published
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error making a post on Facebook: " + e.getMessage(), e);
    }
}


    @Test
    public void testFacebookPost() {
        acceptCookies();
        loginToFacebook();
        makeFacebookpost();

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}