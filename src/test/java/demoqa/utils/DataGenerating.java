package demoqa.utils;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerating {

    //units for autogenerating data
    private static final Faker autogenRu = new Faker(new Locale("ru"));
    private static final Faker autogenEn = new Faker(new Locale("en"));

    //describing the data, which RegistrationPage contained

    private static final String[] genders = {"Male", "Female", "Other"};

    private static final String[] hobbies = {"Sports", "Reading", "Music"};

    private static final String[] subjects = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
            "Biology", "Commerce", "Accounting", "Economics", "Computer Science",
            "Arts", "Social Studies", "History", "Civics"};

    public static final String file = "1.jpg";
    public static final String pathToPictures = "pictures/";

    private static final String[] states = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private static final HashMap<String, String[]> statesAndCities = new HashMap<>();
    static {
        statesAndCities.put(states[0], new String[]{"Delhi", "Gurgaon", "Noida"});
        statesAndCities.put(states[1], new String[]{"Agra", "Lucknow", "Merrut"});
        statesAndCities.put(states[2], new String[]{"Karnal", "Panipat"});
        statesAndCities.put(states[3], new String[]{"Jaipur", "Jaiselmer"});
    }

    //generating data utils

    private static String stateUtil; //auxiliary value to remember state as cities depend on states
    private static String firstSubject; //auxiliary value which lets to add two subjects into the 'subject field'

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

    static public Calendar getDateOfBirth() {
        GregorianCalendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(autogenEn.date().birthday(10, 120)); // (10, 120) - range of age
        return dateOfBirth;
    }

    static public String getAddress() {
        return autogenRu.address().fullAddress();
    }

    //specific generating data utils for RegistrationPage

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
