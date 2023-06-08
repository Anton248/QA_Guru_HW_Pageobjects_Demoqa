package demoqa.tests.config;
import org.aeonbits.owner.Config;

//name of the config files (in the folder resources)
@Config.Sources({
        "classpath:${browser_config}.properties",  //${browser_config} - from System.properties (kinda System.getProperty("browser_config"))
        "classpath:remote_selenoid.properties",
        "classpath:local_browser.properties",
})

//magically getting properties from files (in the resources folder) or System.properties (if exists)
public interface BrowserAndSelenoidConfig extends Config {

    //for example getting 'chrome' from line 'browser.name="chrome" ' in the file local_browser.properties
    //or getting 'chrome' from System.properties, like System.getProperty("browser.name")
    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("selenoid.url")
    String getSelenoidUrl();

    @Key("selenoid.user")
    String getSelenoidUser();

    @Key("selenoid.password")
    String getSelenoidPassword();

}
