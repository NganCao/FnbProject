package com.fnb.utils.helpers;

import net.datafaker.Faker;
import java.util.Locale;

public class HelperDataFaker {

    private static Faker faker;
    //    private static Locale locale = new Locale("vi");;
    public static Faker createFaker() {
        faker = new Faker(new Locale("vi"));
        return faker;
    }

    public static Faker createFakerByLocale(String locateName) {
        faker = new Faker(new Locale(locateName));
        return faker;
    }

    public static Faker getFaker() {
        if (faker == null) {
            faker = createFaker();
        }
        return faker;
    }

    public static Faker getFakerByLocale(String locateName) {
        faker = createFakerByLocale(locateName);
        return faker;
    }

    public static void setFaker(Faker faker) {
        HelperDataFaker.faker = faker;
    }

    public static String generatePhoneNumber () {
        return getFaker().phoneNumber().cellPhone();
    }

    public static String generatePasswordRange (int minimum, int maximum) {
        return getFaker().internet().password(minimum, maximum);
    }

    public static String generatePasswordFrom (int maximum) {
        return getFaker().internet().password(maximum, 1);
    }

    public static String generateRandom (int length) {
        return getFaker().number().digits(length);
    }
}
