package BaseClassHB;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Reusable helper methods (screenshots, waits, timestamps).
 */
public class TestUtilities {

    public static final Duration TIMEOUT = Duration.ofSeconds(10);

    /** Saves a screenshot in /screenshots and returns the file path. */
    public static String captureScreenshot(WebDriver driver, String testName) {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Path target = Paths.get("screenshots", testName + "_" + time + ".png");
        try {
            Files.createDirectories(target.getParent());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
        }
        return target.toAbsolutePath().toString();
    }

    /** Screenshot as Base64 so it can be embedded inside the Extent report. */
    public static String getBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
