package demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    //selectors
    SelenideElement mainSelector = $("div.modal-content"),
                    tableSelector = $("div.table-responsive");

    public void verifyAppear(){
        mainSelector.$(byText("Thanks for submitting the form")).shouldBe(visible);
    }

    public void verifyResult(String key, String value){
        tableSelector.$(byTagAndText("td", key)).sibling(0).shouldHave(text("value"));
    }


}
