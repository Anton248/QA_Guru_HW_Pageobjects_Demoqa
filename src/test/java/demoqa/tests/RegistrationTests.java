package demoqa.tests;

import demoqa.utils.DataGenerating;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static demoqa.utils.DataGenerating.*;

public class RegistrationTests extends TestBase {

    //testing all fields on the page
    @Test
    void allFieldsTest() {

        //values for input fields, checkboxes, etc (using demoqa.utils.DataGenerating)

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
                subject2 = getAnotherSubject(),
                hobby = getHobby(),
                file = DataGenerating.file,
                pathToPictures = DataGenerating.pathToPictures,
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
