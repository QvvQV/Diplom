package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        public String CardAprooved = "4444 4444 4444 4441";
        public String CardDecline = "4444444444444442";
        public String CardNotDB = "4444442224444442";
        public String CardEmpty = "";
    }

    public static String getAproovedCard() {
        return new CardInfo().CardAprooved;
    }

    public static String getDeclineCard() {
        return new CardInfo().CardDecline;
    }

    public static String getEmptyCard() {
        return new CardInfo().CardEmpty;
    }

    public static String getNotDBCard() {
        return new CardInfo().CardNotDB;
    }

    public static String generateYear(int shift) {
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateMonth(int shift) {
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String generateValidCardHolder(String locale) {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateInvalidCardHolder(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateToUpperCaseCardHolder(String locale) {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        return name.toUpperCase();
    }

    private static String generateToLowerCaseCardHolder(String locale) {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        return name.toLowerCase();
    }

    private static String generateCVC() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    private static String generateInvalidTwoCVC() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    private static String generateInvalidFourCVC() {
        Faker faker = new Faker();
        return faker.number().digits(4);
    }
}