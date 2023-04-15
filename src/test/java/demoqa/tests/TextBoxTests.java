package demoqa.tests;

import org.junit.jupiter.api.Test;

public class TextBoxTests extends TestBase {

    //values for input fields
    String  fullName = "Иван Иванов",
            email = "ivanov@yahoo.com",
            currentAddress = "Москва, ул. Уличная, д.1, кв. 111\n" +
                             "Индекс: 99999",
            permanentsAddress = "Анадырь, Полярная ул., д.22\n" +
                                "Индекс: 11111";

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
