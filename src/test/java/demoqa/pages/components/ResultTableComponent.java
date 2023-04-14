package demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    //selectors
    SelenideElement resultDialogSelector = $(".modal-content"),
                    headSelector = $("#example-modal-sizes-title-lg"),
                    tableSelector = $("div.table-responsive");

    public void verifyAppear(){
        resultDialogSelector.should(appear);
        headSelector.shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyResult(String key, String value){
        resultDialogSelector.$(byTagAndText("td", key)).sibling(0).shouldHave(text("value"));
    }

}
