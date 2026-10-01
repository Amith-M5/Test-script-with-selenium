package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BaseClassHB.TestUtilities;

/**
 * Page Object: Login page.
 * Demo site: https://the-internet.herokuapp.com/login
 */
public class LgPom {

    private final WebDriver driver;

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashMessage  = By.id("flash");

    public LgPom(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String username, String password) {
        TestUtilities.waitForVisible(driver, usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getMessage() {
        return TestUtilities.waitForVisible(driver, flashMessage).getText();
    }
}
