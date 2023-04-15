package demoqa.tests;

import org.junit.jupiter.api.Test;

import static demoqa.utils.DataGenerating.*;

public class TextBoxTests extends TestBase {

    //testing all fields on the page
    @Test
    void textBoxTests() {
        textBoxPage.open().removeBanners();

        textBoxPage.setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentsAddress)
                .submit();

        textBoxPage.verifyResult("Name:", fullName)
                .verifyResult("Email:", email)
                .verifyResult("Current Address :", currentAddress)
                .verifyResult("Permananet Address :", permanentsAddress);
    }
}
