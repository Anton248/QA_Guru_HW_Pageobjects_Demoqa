package demoqa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static demoqa.utils.DataInitialize.*;
import static io.qameta.allure.Allure.step;

@Tag("TextBox")
public class TextBoxTests extends TestBase {

    //testing all fields on the page
    @Test
    @Tag("Blocker")
    @DisplayName("Testing all fields in the text box page")
    void textBoxTests() {

        //values for input fields (using demoqa.utils.Utils)
        String fullName = getFullName(),
                email = getEmail(),
                currentAddress = getAddress(),
                permanentsAddress = getAddress();

        step("open the page and remove banners", ()-> {
            textBoxPage.open().removeBanners();
        });
        step("fill text fields", ()-> {
            textBoxPage.setFullName(fullName)
                    .setEmail(email)
                    .setCurrentAddress(currentAddress)
                    .setPermanentAddress(permanentsAddress)
                    .submit();
        });
        step("check results", ()-> {
            textBoxPage.verifyResult("Name:", fullName)
                    .verifyResult("Email:", email)
                    .verifyResult("Current Address :", currentAddress)
                    .verifyResult("Permananet Address :", permanentsAddress);
        });

    }
}
