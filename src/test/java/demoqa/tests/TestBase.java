package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.helpers.Attach;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import demoqa.tests.config.BrowserAndSelenoidConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.remote.DesiredCapabilities;

// Preparing browser for all tests
@Tag("remote")
public class TestBase {
    BrowserAndSelenoidConfig browserConfig = ConfigFactory.create(BrowserAndSelenoidConfig.class, System.getProperties());
    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        //remote browser on Selenoid (from command line)
        String selenoidUrl = System.getProperty("selenoid_url");
        String selenoidLoginPassword = System.getProperty("selenoid_login_password"); //format is {login}:{password}
        selenoidUrl = selenoidUrl.replaceAll("https://", "");
        Configuration.remote = "https://" + selenoidLoginPassword + "@" + selenoidUrl;

        //to avoid freeze during loading pages
        Configuration.pageLoadStrategy = "eager";

        //url of the site, which is tested (from command line)
        Configuration.baseUrl = System.getProperty("site_base_url", "https://demoqa.com");

        //type of browser and its version (from command line) (example - 'chrome:100.0')
        String[] browser = System.getProperty("browser", "chrome:100.0").split(":");
        Configuration.browser = browser[0];
        Configuration.browserVersion = browser[1];

        //size of browser (from command line)
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");

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
