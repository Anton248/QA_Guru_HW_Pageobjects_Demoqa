package demoqa.tests.config;

import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private WebDriverConfig config;

    public WebDriverProvider(WebDriverConfig config) {
        this.config = config;
    }

    public WebDriverProvider() {
        this.config = new WebDriverConfig();
    }

    private WebDriver createWebDriver {

    }

    @Override
    public WebDriver get() {
        return null;
    }
}
