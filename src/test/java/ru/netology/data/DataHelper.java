package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.openqa.selenium.Keys;

public class DataHelper {
       open("http://localhost:8080");
    }

    @Value
    public static class CardInfo {
        // public String CardAprooved;
        String cardAprooved = "4444 4444 4444 4441";
//      String cardDecline = "4444 4444 4444 4442";
//      String cardNotDB = "4444 4422 2444 4442";
    }

    public static String getAproovedCard() {

        return new CardInfo().cardAprooved;

        // return new CardInfo("4444 4444 4444 4441");
    }

//    public static String getDeclineCard() {

        // return new CardInfo().cardDecline;
//        return new CardInfo().CardDecline;
//    }
//
//    public static String getNotDBCard() {
//
//        return new CardInfo().cardNotDB;
//    }

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

    public static String generateInvalidTwoCVC() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    public static String generateInvalidFourCVC() {
        Faker faker = new Faker();
        return faker.number().digits(4);
    }

    public static String generateNameSpecialCharacter() {
        return "@%*&!";

    }

//    public static String generateSpaceName() {
//        return click.sendKeys(Keys.Space);
//    }

    public static String generateRandomText(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
