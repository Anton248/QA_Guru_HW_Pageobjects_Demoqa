package demoqa.tests.config;

import org.aeonbits.owner.Config;

//Names of the config files (in the folder resources). Only one file is used.
//If the first file doesn't exist, then second file is used, etc.
@Config.Sources({
        "classpath:${browser_config}.properties",
        "classpath:remote_selenoid.properties",
        "classpath:local_browser.properties"
})

//magically getting properties from files (in the resources folder) or from System.properties (if exists). System.properties have priority.
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

    @Key("selenoid.video.url")
    String getSelenoidVideoUrl();

}
