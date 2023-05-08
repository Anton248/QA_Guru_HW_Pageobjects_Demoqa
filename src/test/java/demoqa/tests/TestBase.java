package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.helpers.Attach;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

// Preparing browser for all tests
public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        //remote browser on Selenoid
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Configuration.baseUrl = "https://demoqa.com";  //site to test (from command line)
        Configuration.browserSize = "1920x1080";

        //for Allure report
        SelenideLogger.addListener("allure", new AllureSelenide());

        //for video
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        //Configuration.holdBrowserOpen = true; // show browser to test the tests themselves
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
