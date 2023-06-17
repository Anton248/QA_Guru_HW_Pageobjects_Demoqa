package demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${browser_size_config}.properties",  // kinda System.getProperty("browser_size_config")
        "classpath:browser_size.properties"  //if ${browser_size_config}.properties doesn't exist, then get config from browser_size.properties in resources folder
})
public interface BrowserSizeConfig extends Config{

    @Config.Key("browser.size")
    String getBrowserSize();
}
