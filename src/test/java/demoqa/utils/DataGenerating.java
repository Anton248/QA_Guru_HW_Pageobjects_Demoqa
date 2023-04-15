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

    //setting some data for RegistrationPage

    static private final Calendar dateOfBirth = new GregorianCalendar();
    static {
        dateOfBirth.setTime(autogenEn.date().birthday(10, 120)); // (10, 120) - range of age
    }

    private static final String[] genders = {"Male", "Female", "Other"};

    private static final String[] hobbies = {"Sports", "Reading", "Music"};
    private static final String[] subjects = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
            "Biology", "Commerce", "Accounting", "Economics", "Computer Science",
            "Arts", "Social Studies", "History", "Civics"};

    private static String firstSubject; //auxiliary value letting to add two subjects into the field

    public static final String file = "1.jpg";
    public static final String pathToPictures = "pictures/";

    static private final String[] states = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    static private final HashMap<String, String[]> statesAndCities = new HashMap<>();
    static {
        statesAndCities.put(states[0], new String[]{"Delhi", "Gurgaon", "Noida"});
        statesAndCities.put(states[1], new String[]{"Agra", "Lucknow", "Merrut"});
        statesAndCities.put(states[2], new String[]{"Karnal", "Panipat"});
        statesAndCities.put(states[3], new String[]{"Jaipur", "Jaiselmer"});
    }
    static private String stateUtil; //auxiliary value to remember state as cities depend on states

    static public <T> T randomValueFromArray(T[] array) {
        return array[ThreadLocalRandom.current().nextInt(0, array.length-1)];
    }

    static public String getFirstName() {
        return autogenRu.name().firstName();
    }

    static public String getLasName() {
        return autogenRu.name().lastName();
    }

    static public String getFullName() {
        return autogenRu.name().fullName();
    }

    static public String getEmail() {
        return autogenEn.internet().emailAddress();
    }

    static public String getDayOfBirth() {
        return String.valueOf(dateOfBirth.get(Calendar.DAY_OF_MONTH));
    }

    static public String getMonthOfBirth() {
        return Month.of(dateOfBirth.get(Calendar.MONTH))
                .getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("en"));
    }

    static public String getYearOfBirth() {
        return String.valueOf(dateOfBirth.get(Calendar.YEAR));
    }

    static public String getAddress() {
        return autogenRu.address().fullAddress();
    }

    //specific utils for RegistrationPage

    static public String getGender() {
        return randomValueFromArray(genders);
    }

    static public String getMobileNumber10Digits() {
        return ThreadLocalRandom.current().nextInt(10000, 99999) + ""
                + ThreadLocalRandom.current().nextInt(10000, 99999);
    }

    static public String getSubject() {
        firstSubject = randomValueFromArray(subjects);
        return firstSubject;
    }

    static public String getAnotherSubject() {
        String anotherSubject = randomValueFromArray(subjects);
        while (anotherSubject.equals(firstSubject)){
            anotherSubject = randomValueFromArray(subjects);
        }
        return anotherSubject;
    }

    static public String getHobby() {
        return randomValueFromArray(hobbies);
    }

    static public String getState() {
        stateUtil = randomValueFromArray(states);
        return stateUtil;
    }

    static public String getCity() {
        String[] cities = statesAndCities.get(stateUtil);
        return randomValueFromArray(cities);
    }
}
