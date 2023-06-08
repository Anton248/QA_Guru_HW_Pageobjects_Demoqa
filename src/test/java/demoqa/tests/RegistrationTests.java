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
import static io.qameta.allure.Allure.step;

@Tag("practice_form")
public class RegistrationTests extends TestBase {

    @Test
    @Tag("smoke") @Tag("blocker")
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

        //executing of the test
        step("Open the page and remove banners", ()-> {
            registrationPage.open().removeBanners();
        });
        step("Fill the input fields, click the checkboxes, etc", ()-> {
            step("Set name", ()-> {
                registrationPage.setFirstName(firstName).setLastName(lastName);
            });
            step("Set email and mobile number", ()-> {
                registrationPage.setEmail(email).setMobileNumber(mobileNumber);
            });
            step("Set gender", ()-> {
                registrationPage.setGender(gender);
            });
            step("Set date of birth", ()-> {
                registrationPage.setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
            });
            step("Set subjects and hobby", ()-> {
                registrationPage.setSubject(subject1).setSubject(subject2).setHobby(hobby);
            });
            step("Upload pic", ()-> {
                registrationPage.uploadFile(pathToPictures + file);
            });
            step("Set address", ()-> {
                registrationPage.setAddress(currentAddress);
            });
            step("Set state and city", ()-> {
                registrationPage.setState(state).setCity(city);
            });
        });
        step("Submit the form", ()-> {
            registrationPage.submit();
        });
        step("Check that all fields were filled right", ()-> {
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
        });

    }
}
