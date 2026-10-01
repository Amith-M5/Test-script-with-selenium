package createTemplePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClassHB.TestBase;
import Pom.Createtemplepom;

public class CreatetempleTest extends TestBase {

    @Test(priority = 1, description = "Create a temple with valid details")
    public void createTempleWithValidData() {
        Createtemplepom page = new Createtemplepom(driver);
        page.open(prop.getProperty("templeUrl"));
        page.createTemple("Meenakshi Amman Temple", "temple@example.com", "Madurai, Tamil Nadu");

        Assert.assertTrue(page.getCreatedName().contains("Meenakshi Amman Temple"),
                "Created temple name not shown in the output");
    }

    @Test(priority = 2, description = "Create a temple with an invalid email shows validation")
    public void createTempleWithInvalidEmail() {
        Createtemplepom page = new Createtemplepom(driver);
        page.open(prop.getProperty("templeUrl"));
        page.createTemple("Test Temple", "invalid-email", "Chennai");

        Assert.assertTrue(page.isEmailInvalid(), "Email validation error was not shown");
    }
}
