package demoqa.tests;

import org.junit.jupiter.api.Test;

import static demoqa.utils.DataGenerating.*;

public class RegistrationTests extends TestBase {

    @Test
    void allFieldsTest() {

        //values for input fields, checkboxes, etc (using demoqa.utils.DataGenerating)
        String firstName = getFirstName(),
                lastName = getLasName(),
                email = getEmail(),
                gender = getGender(),
                mobileNumber = getMobileNumber10Digits(),
                dayOfBirth = getDayOfBirth(),
                monthOfBirth = getMonthOfBirth(),
                yearOfBirth = getYearOfBirth(),
                subject1 = getSubject(),
                subject2 = getAnotherSubject(),
                hobby = getHobby(),
                file = "1.jpg",
                pathToPictures = "pictures/",
                currentAddress = getAddress(),
                state = getState(),
                city = getCity();

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
