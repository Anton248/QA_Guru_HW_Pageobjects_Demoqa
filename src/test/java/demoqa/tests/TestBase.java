package demoqa.tests;

import com.codeborne.selenide.Configuration;
import demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;

// Preparing browser for all tests
public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";  //site to test
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // show browser to test the tests themselves
    }
}
