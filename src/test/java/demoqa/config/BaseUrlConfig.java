package demoqa.config;

import org.aeonbits.owner.Config;

//name of the config files (in the folder resources)
@Config.Sources({
        "classpath:${base_url_config}.properties",  // kinda System.getProperty("base_url_config")
        "classpath:base_url.properties"  //if ${base_url_config}.properties doesn't exist, then get config from base_url.properties in resources folder
})
public interface BaseUrlConfig extends Config{

    @Key("base.url")
    String getBaseUrl();
}
