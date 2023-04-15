package demoqa.tests;

import org.junit.jupiter.api.Test;

import static demoqa.utils.DataGenerating.*;

public class RegistrationTests extends TestBase {

    @Test
    void allFieldsTest() {
        registrationPage.open();
        registrationPage.removeBanners();

        //filling the input fields, clicking the checkboxes, etc
        //values are generated in utils/DataGenerating

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
                .verifyResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .verifyResult("Subjects", subject1)
                .verifyResult("Subjects", subject2)
                .verifyResult("Hobbies", hobby1)
                .verifyResult("Hobbies", hobby2)
                .verifyResult("Picture", file)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city);
    }
}
