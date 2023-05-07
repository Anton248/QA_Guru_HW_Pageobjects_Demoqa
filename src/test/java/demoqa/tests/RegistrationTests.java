package demoqa.tests;

import demoqa.utils.DataInitialize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static demoqa.utils.DataInitialize.*;

public class RegistrationTests extends TestBase {

    @Test
    @Tag("PracticeForm") @Tag("Blocker")
    @DisplayName("Testing all fields in the practice form")
    void allFieldsTest() {

        //preparing values for input fields, checkboxes, etc (using demoqa.utils.Utils)

        Calendar dateOfBirth = getDateOfBirth(); //to arrange proper dependencies between day, month and year

        String firstName = getFirstName(),
                lastName = getLasName(),
                email = getEmail(),
                gender = getGender(),
                mobileNumber = getMobileNumber10Digits(),
                dayOfBirth = String.valueOf(dateOfBirth.get(Calendar.DAY_OF_MONTH)),
                monthOfBirth = Month.of(dateOfBirth.get(Calendar.MONTH)+1)
                        .getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("en")),
                yearOfBirth = String.valueOf(dateOfBirth.get(Calendar.YEAR)),
                subject1 = getSubject(),
                subject2 = getSubject(),
                hobby = getHobby(),
                file = DataInitialize.file,
                pathToPictures = DataInitialize.pathToPictures,
                currentAddress = getAddress(),
                state = getState(),
                city = getCity();

        //opening the page and removing banners
        registrationPage.open().removeBanners();

        //filling the input fields, clicking the checkboxes, etc
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobileNumber(mobileNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject1)
                .setSubject(subject2)
                .setHobby(hobby)
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
                .verifyResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .verifyResult("Subjects", subject1)
                .verifyResult("Subjects", subject2)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", file)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city);
    }
}
