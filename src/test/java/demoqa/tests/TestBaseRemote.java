package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.helpers.Attach;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.remote.DesiredCapabilities;

// Preparing browser for all tests
@Tag("remote")
public class TestBaseRemote {
    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        //remote browser on Selenoid (from command line)
        Configuration.remote = System.getProperty("selenoid_url");

        //url of the site, which is tested (from command line)
        Configuration.baseUrl = System.getProperty("site_base_url", "https://demoqa.com");

        //type of browser (from command line)
        Configuration.browser = System.getProperty("browser", "chrome");

        //size of browser (from command line)
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
