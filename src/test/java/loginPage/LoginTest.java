package loginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClassHB.TestBase;
import Pom.LgPom;

public class LoginTest extends TestBase {

    @Test(priority = 1, description = "Login with valid credentials")
    public void validLoginTest() {
        LgPom login = new LgPom(driver);
        login.open(prop.getProperty("loginUrl"));
        login.login(prop.getProperty("username"), prop.getProperty("password"));

        Assert.assertTrue(login.getMessage().contains("You logged into a secure area!"),
                "Success message not displayed");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            { "tomsmith",  "wrongPassword", "Your password is invalid!" },
            { "wrongUser", "SuperSecretPassword!", "Your username is invalid!" }
        };
    }

    @Test(priority = 2, dataProvider = "invalidCredentials",
          description = "Login with invalid credentials shows an error")
    public void invalidLoginTest(String user, String pass, String expectedError) {
        LgPom login = new LgPom(driver);
        login.open(prop.getProperty("loginUrl"));
        login.login(user, pass);

        Assert.assertTrue(login.getMessage().contains(expectedError),
                "Expected error message not displayed");
    }
}
