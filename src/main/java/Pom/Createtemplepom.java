package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BaseClassHB.TestUtilities;

/**
 * Page Object: "Create Temple" form.
 * Demo mapping: https://demoqa.com/text-box (Name / Email / Address form).
 * Replace the locators with your real application's fields.
 */
public class Createtemplepom {

    private final WebDriver driver;

    private final By templeName    = By.id("userName");
    private final By templeEmail   = By.id("userEmail");
    private final By templeAddress = By.id("currentAddress");
    private final By submitButton  = By.id("submit");
    private final By outputName    = By.id("name");

    public Createtemplepom(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void createTemple(String name, String email, String address) {
        TestUtilities.waitForVisible(driver, templeName).sendKeys(name);
        driver.findElement(templeEmail).sendKeys(email);
        driver.findElement(templeAddress).sendKeys(address);

        WebElement submit = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        TestUtilities.waitForClickable(driver, submitButton);
        js.executeScript("arguments[0].click();", submit);
    }

    public String getCreatedName() {
        return TestUtilities.waitForVisible(driver, outputName).getText();
    }

    /** The email field gets the CSS class "field-error" when the email is invalid. */
    public boolean isEmailInvalid() {
        String css = driver.findElement(templeEmail).getAttribute("class");
        return css != null && css.contains("field-error");
    }
}
