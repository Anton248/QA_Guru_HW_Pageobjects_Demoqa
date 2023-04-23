package demoqa.tests;

import com.codeborne.selenide.Configuration;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import org.junit.jupiter.api.BeforeAll;

// Preparing browser for all tests
public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";  //site to test
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true; // show browser to test the tests themselves
    }
}
