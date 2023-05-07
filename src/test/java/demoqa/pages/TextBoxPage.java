package demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

// https://demoqa.com/text-box
public class TextBoxPage {

    //selectors of all fields on the page
    SelenideElement fullNameInput = $("input#userName"),
            emailInput = $("input#userEmail"),
            currentAddressInput = $("textarea#currentAddress"),
            permanentAddressInput = $("textarea#permanentAddress"),
            outputComponentSelector = $("#output");

    //submit the form selector
    SelenideElement submit = $("button#submit");

    public TextBoxPage open() {
        try {
            Selenide.open("/text-box");
        } catch (TimeoutException e) {
            executeJavaScript("window.stop();"); //to avoid timeout exception because of long downloading of the page
            e.printStackTrace(System.out);
            System.out.println("WARNING:");
            System.out.println("***Timeout exception occurred. Trying to stop downloading the page and continue anyway***");
        }
        return this;
    }

    public TextBoxPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        fullNameInput.setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submit() {
        submit.click();
        return this;
    }

    public TextBoxPage verifyResult(String key, String value) {
        outputComponentSelector.$(byText(key)).shouldHave(text(value));
        return this;
    }
}
