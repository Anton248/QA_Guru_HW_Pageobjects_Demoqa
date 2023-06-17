package demoqa.config;

import org.aeonbits.owner.Config;

//Names of the config files (in the folder resources). Only one file is used.
//If the first file doesn't exist, then second file is used, etc.
@Config.Sources({
        "classpath:local_selenoid.properties",
        "classpath:${browser_config}.properties",
        "classpath:remote_selenoid.properties",
        "classpath:local_browser.properties",
        "classpath:local_selenoid.properties"
})

//magically getting properties from files (in the resources folder) or from System.properties (if exists). System.properties have priority.
public interface WebDriverConfig extends Config {

    //for example getting 'chrome' from line 'browser.name="chrome" ' in the file local_browser.properties
    //or getting 'chrome' from System.properties, like System.getProperty("browser.name")
    @Key("browser.name")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browser.version")
    @DefaultValue("113.0")
    String getBrowserVersion();

    @Key("is.remote.browser.enable")
    @DefaultValue("false")
    Boolean isRemoteBrowserEnable();

    @Key("remote.browser.url")
    String getRemoteBrowserUrl();

    @Key("is.video.enable")
    @DefaultValue("false")
    Boolean isVideoEnable();

    @Key("video.url")
    String getSelenoidVideoUrl();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("base.url")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();


}
