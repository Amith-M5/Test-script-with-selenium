package Profile;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClassHB.TestBaseforlogin;
import Pom.ProfilePOM;

/** Extends TestBaseforlogin, so the user is already logged in when each test starts. */
public class ProfileTest extends TestBaseforlogin {

    @Test(priority = 1, description = "Profile area is displayed after login")
    public void verifyProfileAreaTest() {
        ProfilePOM profile = new ProfilePOM(driver);

        Assert.assertTrue(profile.getHeading().contains("Secure Area"), "Profile heading not shown");
        Assert.assertTrue(profile.isLogoutDisplayed(), "Logout button not displayed");
    }

    @Test(priority = 2, description = "User can logout from the profile area")
    public void logoutTest() {
        ProfilePOM profile = new ProfilePOM(driver);
        profile.logout();

        Assert.assertTrue(profile.getMessage().contains("You logged out of the secure area!"),
                "Logout message not displayed");
    }
}
