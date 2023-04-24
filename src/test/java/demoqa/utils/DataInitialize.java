package demoqa.utils;

import com.github.javafaker.Faker;
import demoqa.pages.registration_page.page_data.Genders;
import demoqa.pages.registration_page.page_data.Hobbies;
import demoqa.pages.registration_page.page_data.Subjects;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static demoqa.utils.Utils.randomValueFromArray;

public class DataInitialize {

    //units for autogenerating data
    private static final Faker autogenRu = new Faker(new Locale("ru"));
    private static final Faker autogenEn = new Faker(new Locale("en"));

    //describing the data, which RegistrationPage contained

    private static final String[] genders = {Genders.MALE.toString(), Genders.FEMALE.toString(), Genders.OTHER.toString()};

    private static final String[] hobbies = {Hobbies.MUSIC.toString(), Hobbies.READING.toString(), Hobbies.SPORTS.toString()};

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
    private static ArrayList<Subjects> unusedSubjects = new ArrayList<>(Arrays.asList(Subjects.values())); //auxiliary value which lets to add more subjects into the 'subject field'

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
        Subjects[] unused = unusedSubjects.toArray(new Subjects[0]);
        Subjects subject = randomValueFromArray(unused);
        unusedSubjects.remove(subject);
        return subject.toString();
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
