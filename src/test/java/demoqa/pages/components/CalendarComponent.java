package demoqa.pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    //selectors
    SelenideElement yearPicker = $(".react-datepicker__year-select"),
                    monthPicker = $(".react-datepicker__month-select");

    public void setDate(String day, String month, String year){
        yearPicker.selectOption(year);
        monthPicker.selectOption(month);
        day = day.length()<2 ? "0" + day;
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day)).click();

    }

}
