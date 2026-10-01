package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BaseClassHB.TestUtilities;

/**
 * Page Object: Profile / account area (shown after login).
 * Demo mapping: the "secure area" page of the practice site.
 */
public class ProfilePOM {

    private final WebDriver driver;

    private final By heading      = By.tagName("h2");
    private final By logoutButton = By.cssSelector("a[href='/logout']");
    private final By flashMessage = By.id("flash");

    public ProfilePOM(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeading() {
        return TestUtilities.waitForVisible(driver, heading).getText();
    }

    public boolean isLogoutDisplayed() {
        return TestUtilities.waitForVisible(driver, logoutButton).isDisplayed();
    }

    public void logout() {
        TestUtilities.waitForClickable(driver, logoutButton).click();
    }

    public String getMessage() {
        return TestUtilities.waitForVisible(driver, flashMessage).getText();
    }
}
