package demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {


    //selectors of all fields on the page
    SelenideElement fullNameInput = $("input#userName"),
                    emailInput = $("input#userEmail"),
                    currentAddressInput = $("textarea#currentAddress"),
                    permanentAddressInput = $("textarea#permanentAddress"),
                    outputComponentSelector = $("#output");

    //submit the form selector
    SelenideElement submit = $("button#submit");

    public TextBoxPage open(){
        Selenide.open("/text-box");
        return this;
    }

    public TextBoxPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public TextBoxPage setFullName(String fullName){
        fullNameInput.setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email){
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress){
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress){
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submit(){
        submit.click();
        return this;
    }

    public TextBoxPage verifyResult(String key, String value){
        outputComponentSelector.$(byText(key)).shouldHave(text(value));
        return this;
    }







}
