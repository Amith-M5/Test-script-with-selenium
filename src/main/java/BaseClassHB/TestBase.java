package BaseClassHB;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Parent class for all tests: reads config.properties,
 * launches the browser before each test and closes it after.
 */
public class TestBase {

    public static WebDriver driver;     // static so Listerners can take a screenshot on failure
    protected static Properties prop = new Properties();

    static {
        try (InputStream in = TestBase.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in src/main/resources");
            }
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    @BeforeMethod
    public void setUp() {
        String browser = prop.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        switch (browser) {
            case "firefox":
                FirefoxOptions ff = new FirefoxOptions();
                if (headless) ff.addArguments("-headless");
                driver = new FirefoxDriver(ff);
                break;
            case "edge":
                EdgeOptions edge = new EdgeOptions();
                if (headless) edge.addArguments("--headless=new");
                driver = new EdgeDriver(edge);
                break;
            default:
                ChromeOptions chrome = new ChromeOptions();
                if (headless) chrome.addArguments("--headless=new");
                chrome.addArguments("--start-maximized", "--disable-notifications");
                driver = new ChromeDriver(chrome);   // Selenium Manager downloads the driver automatically
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
