package demoqa.pages.registration_page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import demoqa.pages.components.CalendarComponent;
import demoqa.pages.components.ResultTableComponent;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

//https://demoqa.com/automation-practice-form
public class RegistrationPage {

    //selectors of all fields on the page
    CalendarComponent calendarComponent = new CalendarComponent();
    SelenideElement firstNameInput = $("input#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderSelection = $("#genterWrapper"),
            mobileInput = $("#userNumber"),
            dateOfBirthPicker = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckBoxes = $("#hobbiesWrapper"),
            fileUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelection = $("#state"),
            citySelection = $("#city");

    //submit the form selector
    SelenideElement submit = $("button#submit");

    //resultTable component selector
    ResultTableComponent resultTable = new ResultTableComponent();

    //describing all actions on the page

    public RegistrationPage open() {
        try {
            Selenide.open("/automation-practice-form");
        } catch (TimeoutException e) {
            executeJavaScript("window.stop();"); //to avoid timeout exception because of long downloading of the page
            e.printStackTrace(System.out);
            System.out.println("WARNING:");
            System.out.println("***Timeout exception occurred. Trying to stop downloading the page and continue anyway***");
        }
        $(byTagAndText("h5", "Student Registration Form")).shouldBe(visible);
        executeJavaScript("$('#fixedban').remove()"); //removing ad banners
        executeJavaScript("$('footer').remove()"); //removing ad banners
        return this;
    }


    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderSelection.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setMobileNumber(String mobileNumber) {
        mobileInput.setValue(mobileNumber);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthPicker.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbiesCheckBoxes.$(byText(hobby)).parent().click();
        return this;
    }

    public RegistrationPage uploadFile(String file) {
        fileUpload.uploadFromClasspath(file);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateSelection.click();
        stateSelection.$(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        citySelection.click();
        citySelection.$(byText(city)).click();
        return this;
    }

    public RegistrationPage submit() {
        submit.click();
        return this;
    }

    public RegistrationPage verifyResultPageAppear() {
        resultTable.verifyAppear();
        return this;
    }

    public RegistrationPage verifyResult(String key, String result) {
        resultTable.verifyResult(key, result);
        return this;
    }

}
