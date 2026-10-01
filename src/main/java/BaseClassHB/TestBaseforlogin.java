package BaseClassHB;

import org.testng.annotations.BeforeMethod;

import Pom.LgPom;

/**
 * Use this base class for tests that need a logged-in user.
 * TestBase.setUp() runs first (browser launch), then this login step runs.
 */
public class TestBaseforlogin extends TestBase {

    @BeforeMethod
    public void loginBeforeTest() {
        LgPom login = new LgPom(driver);
        login.open(prop.getProperty("loginUrl"));
        login.login(prop.getProperty("username"), prop.getProperty("password"));
    }
}
