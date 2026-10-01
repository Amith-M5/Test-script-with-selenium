# Holybharat – Selenium Java Test Automation Framework

UI automation framework built with **Selenium WebDriver (Java), TestNG, Maven, Extent Reports**, following the **Page Object Model**.
Demo tests run against public practice sites ([the-internet.herokuapp.com](https://the-internet.herokuapp.com), [demoqa.com](https://demoqa.com)).

## Features
- Page Object Model (`Pom` package)
- Reusable base classes: `TestBase` (browser setup) and `TestBaseforlogin` (auto-login)
- Config-driven (`config.properties`): browser (Chrome / Firefox / Edge), headless mode, URLs
- TestNG listener + Extent HTML report with screenshots on failure
- Data-driven tests with `@DataProvider`
- Positive and negative test cases

## Project structure
```
src/main/java
 ├── BaseClassHB
 │    ├── TestBase.java          browser launch / close, reads config
 │    ├── TestBaseforlogin.java  logs in before each test
 │    ├── TestUtilities.java     screenshots, explicit waits
 │    ├── Extentmanager.java     Extent report setup
 │    └── Listerners.java        TestNG listener (pass / fail / skip + screenshot)
 └── Pom
      ├── LgPom.java             Login page
      ├── ProfilePOM.java        Profile page
      └── Createtemplepom.java   Create-temple form
src/main/resources/config.properties
src/test/java
 ├── loginPage/LoginTest.java
 ├── createTemplePage/CreatetempleTest.java
 └── Profile/ProfileTest.java
testng.xml
```

## How to run
Requirements: Java 17+, Maven, Google Chrome.
```
git clone https://github.com/<your-username>/Holybharat-Selenium-Framework.git
cd Holybharat-Selenium-Framework
mvn clean test
```
Report: `test-output/ExtentReport.html` (open in a browser).

## Author
Amith M – Automation Test Engineer
