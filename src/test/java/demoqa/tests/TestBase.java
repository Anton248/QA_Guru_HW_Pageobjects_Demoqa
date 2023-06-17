package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoqa.helpers.Attach;
import demoqa.pages.registration_page.RegistrationPage;
import demoqa.pages.TextBoxPage;
import demoqa.config.BaseUrlConfig;
import demoqa.config.BrowserAndSelenoidConfig;
import demoqa.config.BrowserSizeConfig;
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
    // - url of Selenoid (e.g. "https://selenoid.autotests.cloud/wd/hub"; command line: -D"selenoid.url"="https..."; file: selenoid.url=https...)
    // - user of Selenoid (e.g. "user1"; command line: -D"selenoid.user"="user1"; file: selenoid.user=user1)
    // - password of Selenoid (e.g. "qwerty"; command line: -D"selenoid.password"="qwerty"; file: selenoid.password=qwerty)
    // - base url to test (e.g. "https://demoqa.com"; command line: -D"base.url"="https..."; file: base.url=https...)
    // - browser size (e.g. "1920x1080"; command line: -D"browser.size"="1920x1080"; file: browser.size=1920x1080).

    // Configuration files can be specified by command line too, for example:
    //   gradle clean test -D"browser_config"="remote_selenoid" -D"base_url_config"="base_url" -D"browser_size_config"="browser_size"
    // This command will read configs from files remote_selenoid.properties, base_url.properties and browser_size.properties in the resources folder.

    // Another example of command file which mixed config file and direct values:
    //   gradle clean test -D"browser_config"="remote_selenoid" -D"browser.size"="1920x1080" -D"base.url"="https://demoqa.com".


    private static final BrowserAndSelenoidConfig browserConfig = ConfigFactory.create(BrowserAndSelenoidConfig.class, System.getProperties());
    private static final BrowserSizeConfig browserSizeConfig = ConfigFactory.create(BrowserSizeConfig.class, System.getProperties());
    private static final BaseUrlConfig baseUrlConfig = ConfigFactory.create(BaseUrlConfig.class, System.getProperties());
    RegistrationPage registrationPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        //putting browser and its version into Selenide config
        Configuration.browser = browserConfig.getBrowserName();
        Configuration.browserVersion = browserConfig.getBrowserVersion();

        //putting remote Selenoid properties into Selenide config
        String selenoidUrl = browserConfig.getSelenoidUrl();
        String selenoidLogin = browserConfig.getSelenoidUser();
        String selenoidPassword = browserConfig.getSelenoidPassword();
        if (selenoidLogin != null) {
            selenoidUrl = selenoidUrl.replaceAll("https://", "");
            Configuration.remote = "https://" + selenoidLogin + ":" + selenoidPassword + "@" + selenoidUrl;
        } else if (selenoidUrl != null)
            Configuration.remote = selenoidUrl;

        //to avoid freeze during loading pages
        Configuration.pageLoadStrategy = "eager";

        //url of the site, which is tested
        Configuration.baseUrl = baseUrlConfig.getBaseUrl();

        //size of browser
        Configuration.browserSize = browserSizeConfig.getBrowserSize();

        //for Allure report
        SelenideLogger.addListener("allure", new AllureSelenide());

        //for video (remote Selenide only)
        if (selenoidLogin != null) {
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
        if (browserConfig.getSelenoidUser() != null) Attach.addVideo();

    }
}
