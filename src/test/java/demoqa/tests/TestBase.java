package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.helpers.Attach;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import demoqa.config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

// Preparing all for tests
public class TestBase {
    // The configuration is formed by specifying values, which are taken from properties files (in the resources folder)
    // or from the command line:
    // - type of browser (e.g. "chrome"; command line: -D"browser.name"="chrome"; file: browser.name=chrome)
    // - version of browser (e.g. "113.0"; command line: -D"browser.version"="113.0"; file: browser.version=113.0)
    // - if remote browser needed (e.g. true; command line: -D"is.remote.browser.enable"="true"; file: is.remote.browser.enable=true)
    // - url of remote browser (Selenoid) (e.g. "https://selenoid.autotests.cloud/wd/hub"; command line: -D"remote.browser.url"="https..."; file: remote.browser.url=https...)
    // - base url to test (e.g. "https://demoqa.com"; command line: -D"base.url"="https..."; file: base.url=https...)
    // - browser size (e.g. "1920x1080"; command line: -D"browser.size"="1920x1080"; file: browser.size=1920x1080)

    // Configuration files can be specified by command line too, for example:
    //   gradle clean test -D"browser_config"="remote_selenoid"
    // This command will read configs from files remote_selenoid.properties in the resources folder.

    // Another example of command file which mixed config file and direct values:
    //   gradle clean test -D"browser_config"="remote_selenoid" -D"browser.size"="1920x1080" -D"base.url"="https://demoqa.com".


    private static final WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        //putting browser and its version into Selenide config
        Configuration.browser = webDriverConfig.getBrowserName();
        Configuration.browserVersion = webDriverConfig.getBrowserVersion();

        //putting remote Selenoid properties into Selenide config
        if (webDriverConfig.isRemoteBrowserEnable()) {
            Configuration.remote = webDriverConfig.getRemoteBrowserUrl();
        }

        //to avoid freeze during loading pages
        Configuration.pageLoadStrategy = "eager";

        //url of the site, which is tested
        Configuration.baseUrl = webDriverConfig.getBaseUrl();

        //size of browser
        Configuration.browserSize = webDriverConfig.getBrowserSize();

        //for Allure report
        SelenideLogger.addListener("allure", new AllureSelenide());

        //for video (remote Selenide only)
        if (webDriverConfig.isVideoEnable()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        //Configuration.holdBrowserOpen = true; // show browser to test the tests themselves
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (webDriverConfig.isVideoEnable()) Attach.addVideo();

    }
}
