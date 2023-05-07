package demoqa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static demoqa.utils.DataInitialize.*;

public class TextBoxTests extends TestBase {

    //testing all fields on the page
    @Test
    @Tag("TextBox") @Tag("Blocker")
    @DisplayName("Testing all fields in the text box page")
    void textBoxTests() {

        //values for input fields (using demoqa.utils.Utils)
        String fullName = getFullName(),
                email = getEmail(),
                currentAddress = getAddress(),
                permanentsAddress = getAddress();

        textBoxPage.open().removeBanners();

        //filling fields
        textBoxPage.setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentsAddress)
                .submit();

        //checking results
        textBoxPage.verifyResult("Name:", fullName)
                .verifyResult("Email:", email)
                .verifyResult("Current Address :", currentAddress)
                .verifyResult("Permananet Address :", permanentsAddress);
    }
}
