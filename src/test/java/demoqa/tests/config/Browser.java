package demoqa.tests.config;

public enum Browser {
    CHROME ("chrome"),
    FIREFOX ("firefox"),
    EDGE ("edge");

    private final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public String toString() {
        return browserName;
    }
}
