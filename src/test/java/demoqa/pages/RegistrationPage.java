package demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import demoqa.pages.components.CalendarComponent;
import demoqa.pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

//https://demoqa.com/automation-practice-form
public class RegistrationPage {

    //defining all forms on the page
    CalendarComponent datePicker = new CalendarComponent();
    SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput = $("#lastName"),
                    emailInput = $("#userEmail"),
                    genderSelection = $("#genterWrapper"),
                    mobileInput = $("#userNumber"),
                    subjectsInput = $("#subjectsInput"),
                    hobbiesCheckBoxes = $("#hobbiesWrapper"),
                    fileUpload = $("#uploadPicture"),
                    addressInput = $("#currentAddress"),
                    stateSelection = $("#state"),
                    citySelection = $("#city");

    //submit the form
    SelenideElement submit = $("#submit");

    //resultTable component
    ResultTableComponent resultTable = new ResultTableComponent();

    //describing all actions on the page

    public RegistrationPage open() {
        Selenide.open("/automation-practice-form");
        $(byTagAndText("h5", "Student Registration Form")).shouldBe(visible);
        executeJavaScript("$('#fixedban').remove()"); //removing ad banners
        executeJavaScript("$('footer').remove()"); //removing ad banners
        return this;
    }

    public RegistrationPage setFirstName(String firstName){
        firstNameInput.setValue(firstName);
        return this;
    }


}
