package demoqa.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerating {

    //unit for autogenerating data
    static private final Faker autogen = new Faker(new Locale("ru"));

    //RegistrationPage

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
    static public final String firstName = autogen.name().firstName(),
            lastName = autogen.name().lastName(),
            email = autogen.internet().emailAddress("en"),
            gender = new String[]{"Male", "Female", "Other"}[ThreadLocalRandom.current().nextInt(0, 2)],
            mobileNumber = ThreadLocalRandom.current().nextInt(10000, 99999) + "" + ThreadLocalRandom.current().nextInt(10000, 99999),
            dayOfBirth = String.valueOf(ThreadLocalRandom.current().nextInt(1, 28)),
            monthOfBirth =  new String[]{
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"}[ThreadLocalRandom.current().nextInt(0, 11)],
            yearOfBirth = String.valueOf(ThreadLocalRandom.current().nextInt(1900, 2023)),
            subject1 = new String[]{"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                    "Biology", "Commerce", "Accounting", "Economics",
                    "Arts", "Social Studies", "History", "Civics"}[ThreadLocalRandom.current().nextInt(0, 14)],
            subject2 = "Computer Science",
            hobby1 = new String[]{"Sports", "Reading"}[ThreadLocalRandom.current().nextInt(0, 1)],
            hobby2 = "Music",
            file = "1.jpg",
            pathToPictures = "pictures/",
            currentAddress = autogen.address().fullAddress(),
            state = states[ThreadLocalRandom.current().nextInt(0, states.length-1)],
            city = statesAndCities.get(state)[ThreadLocalRandom.current().nextInt(0, statesAndCities.get(state).length-1)];

    //TextBoxPage

    //values to use in test directly
    static public final String fullName = autogen.name().fullName(),
            permanentsAddress = autogen.address().fullAddress();
            //email and currentAddress are the same as in RegistrationPage
}
