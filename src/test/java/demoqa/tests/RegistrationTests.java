package demoqa.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationTests extends TestBase{

    //values for input fields, checkboxes, etc
    String firstName = "Ivan",
            lastName = "Ivanov",
            email = "ivanov@ivanov.com",
            gender = "Male",
            mobileNumber = "1234567890",
            dayOfBirth = "2",
            monthOfBirth = "February",
            yearOfBirth = "2000",
            subject1 = "Chemistry",
            subject2 = "Computer Science",
            hobby1 = "Sports",
            hobby2 = "Reading",
            file = "1.jpg",
            pathToPictures = "pictures/",
            currentAddress = "Москва, ул. Уличная, д.5, кв.111",
            state = "Uttar Pradesh",
            city = "Agra";

    @Test
    void allFieldsTest() {
        open("/automation-practice-form");

        //deleting ad banners
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //filling the input fields, clicking the checkboxes, etc
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobileNumber(mobileNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject1)
                .setSubject(subject2)
                .setHobby(hobby1)
                .setHobby(hobby2)
                .uploadFile(pathToPictures + file)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city);


        //submit the form
        registrationPage.submit();

        //checking that all fields were filled right
        registrationPage.verifyResultPageAppear()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobileNumber)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)
                .verifyResult("Student Email", email)

    }
}
