package demoqa.utils;

import com.github.javafaker.Faker;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerating {

    //units for autogenerating data
    static private final Faker autogenRu = new Faker(new Locale("ru"));
    static private final Faker autogenEn = new Faker(new Locale("en"));

    //RegistrationPage

    static private final Calendar dateOfBirth = new GregorianCalendar();
    static {
        dateOfBirth.setTime(autogenEn.date().birthday(10,110));
    }

    //Database for states and cities
    static private final String[] states = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    static private final HashMap<String, String[]> statesAndCities = new HashMap<>();
    static {
        statesAndCities.put(states[0], new String[]{"Delhi", "Gurgaon", "Noida"});
        statesAndCities.put(states[1], new String[]{"Agra", "Lucknow", "Merrut"});
        statesAndCities.put(states[2], new String[]{"Karnal", "Panipat"});
        statesAndCities.put(states[3], new String[]{"Jaipur", "Jaiselmer"});
    }

    //values to use in test directly
    static public final String firstName = autogenRu.name().firstName(),
            lastName = autogenRu.name().lastName(),
            email = autogenEn.internet().emailAddress(),
            gender = new String[]{"Male", "Female", "Other"}[ThreadLocalRandom.current().nextInt(0, 2)],
            mobileNumber = ThreadLocalRandom.current().nextInt(10000, 99999) + "" + ThreadLocalRandom.current().nextInt(10000, 99999),
            dayOfBirth = String.valueOf(dateOfBirth.get(Calendar.DAY_OF_MONTH)),
            monthOfBirth =  Month.of(dateOfBirth.get(Calendar.MONTH)).getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("en")),
            yearOfBirth = String.valueOf(dateOfBirth.get(Calendar.YEAR)),
            subject1 = new String[]{"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                    "Biology", "Commerce", "Accounting", "Economics",
                    "Arts", "Social Studies", "History", "Civics"}[ThreadLocalRandom.current().nextInt(0, 14)],
            subject2 = "Computer Science",
            hobby1 = new String[]{"Sports", "Reading"}[ThreadLocalRandom.current().nextInt(0, 1)],
            hobby2 = "Music",
            file = "1.jpg",
            pathToPictures = "pictures/",
            currentAddress = autogenRu.address().fullAddress(),
            state = states[ThreadLocalRandom.current().nextInt(0, states.length-1)],
            city = statesAndCities.get(state)[ThreadLocalRandom.current().nextInt(0, statesAndCities.get(state).length-1)];

    //TextBoxPage

    //values to use in test directly
    static public final String fullName = autogenRu.name().fullName(),
            permanentsAddress = autogenRu.address().fullAddress();
            //email and currentAddress are the same as for RegistrationPage
}
