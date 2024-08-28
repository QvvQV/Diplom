package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.Card;
import ru.netology.page.CardPage;
import ru.netology.data.SQLHelper;
//import ru.netology.web.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeTests {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
//        var CardPage = new CardPage();
//        var AproovedCard = DataHelper.getAproovedCard();
//        var ValidHolder = CardPage.fillValidField();
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
    }

    @AfterEach
    void cleanBase() {
        SQLHelper.cleanBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should Buy Field NumberCard, Month, Year, CVC Letter")
    void negativeBuyFieldNumMonYearCVCLetter() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("Qfgye");
        $("[placeholder=\"08\"]").setValue("Qfgye");
        $("[placeholder=\"22\"]").setValue("Qfgye");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Semenov Petrov");
        $("[placeholder=\"999\"]").setValue("Qfgye");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field NumberCard, Month, Year, CVC Letter")
    void negativeCreditFieldNumMonYearCVCLetter() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("Qfgye");
        $("[placeholder=\"08\"]").setValue("Qfgye");
        $("[placeholder=\"22\"]").setValue("Qfgye");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Semenov Petrov");
        $("[placeholder=\"999\"]").setValue("Qfgye");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Buy Field Owner Numbers")
    void negativeBuyFieldOwnerNumbers() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("125");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field Owner Numbers")
    void negativeCreditFieldOwnerNumbers() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("125");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Buy Field All Special Characters")
    void negativeBuyFieldAllSpechial() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("#%*!{}");
        $("[placeholder=\"08\"]").setValue("#%*!{}");
        $("[placeholder=\"22\"]").setValue("#%*!{}");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("#%*!{}");
        $("[placeholder=\"999\"]").setValue("#%*!{}");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field All Special Characters")
    void negativeCreditAllSpechial() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("#%*!{}");
        $("[placeholder=\"08\"]").setValue("#%*!{}");
        $("[placeholder=\"22\"]").setValue("#%*!{}");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("#%*!{}");
        $("[placeholder=\"999\"]").setValue("#%*!{}");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

//    @Test
//    @DisplayName("Should Buy Field All Space")
//    void negativeBuyFieldAllSpace() {
//        $(byText("Купить")).click();
//        $("placeholder=\"0000 0000 0000 0000\"]").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"08\"]").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"22\"]").doubleClick.sendKeys(Keys.Space);
//         $$(".input__inner").findBy(text("Владелец")).$(".input__control").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"999\"]").doubleClick.sendKeys(Keys.Space);
//        $(byText("Продолжить")).click();
//        $("[.input__inner]")).shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Неверный формат"));
//        $("[.input__inner]")).shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Поле обязательное для заполнения"));
//    }
//
//    @Test
//    @DisplayName("Should Credit Field All Space")
//    void negativeCreditFieldAllSpace() {
//        $(byText("Купить в кредит")).click();
//        $("placeholder=\"0000 0000 0000 0000\"]").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"08\"]").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"22\"]").doubleClick.sendKeys(Keys.Space);
//        $$(".input__inner").findBy(text("Владелец")).$(".input__control").doubleClick.sendKeys(Keys.Space);
//        $("[placeholder=\"999\"]").doubleClick.sendKeys(Keys.Space);
//        $(byText("Продолжить")).click();
//        $("[.input__inner]")).shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Неверный формат"));
//        $("[.input__inner]")).shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Поле обязательное для заполнения"));
//    }

//    @Test
//    @DisplayName("Should Buy Field Month One Number")
//    void negativeBuyFieldMonthOneNumbers() {
//        $(byText("Купить")).click();
//        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
//        $("[placeholder=\"08\"]").setValue("1");
//        $("[placeholder=\"22\"]").setValue("24");
//        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
//        $("[placeholder=\"999\"]").setValue("456");
//        $(byText("Продолжить")).click();
//        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Неверный формат"));
//    }

//    @Test
//    @DisplayName("Should Credit Field Month One Number")
//    void negativeCreditFieldMonthOneNumbers() {
//        $(byText("Купить в кредит")).click();
//        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
//        $("[placeholder=\"08\"]").setValue("1");
//        $("[placeholder=\"22\"]").setValue("24");
//        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
//        $("[placeholder=\"999\"]").setValue("456");
//        $(byText("Продолжить")).click();
//        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Неверный формат"));
//    }

    @Test
    @DisplayName("Should Buy Field Year One Number")
    void negativeBuyFieldMonthOneNumbers() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("01");
        $("[placeholder=\"22\"]").setValue("2");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field Year One Number")
    void negativeCreditFieldMonthOneNumbers() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("01");
        $("[placeholder=\"22\"]").setValue("2");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Buy Field Existen Month")
    void negativeBuyFieldExistenMonth() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("13");
        $("[placeholder=\"22\"]").setValue("2");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field Existen Month")
    void negativeCreditFieldExistenMonth() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("13");
        $("[placeholder=\"22\"]").setValue("2");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Buy Field Past Year")
    void negativeBuyFieldPastYear() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("12");
        $("[placeholder=\"22\"]").setValue("23");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.notification_status_ok]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена Банком.")); //скорре всего истёк срок годности
    }

    @Test
    @DisplayName("Should Credit Field Past Year")
    void negativeCreditFieldPastYear() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("12");
        $("[placeholder=\"22\"]").setValue("23");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.notification_status_ok]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена Банком.")); //скорре всего истёк срок годности
    }

    @Test
    @DisplayName("Should Buy Field More Five Years")
    void negativeBuyFieldMoreFiveYears() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("30");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    @Test
    @DisplayName("Should Credit Field More Five Years")
    void negativeCreditFieldMoreFiveYears() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("30");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("456");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    @Test
    @DisplayName("Should Buy Field Two Number CVC")
    void negativeBuyFieldTwoNumberCVC() {
        $(byText("Купить")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("45");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    @DisplayName("Should Credit Field Two Number CVC")
    void negativeCreditFieldTwoNumberCVC() {
        $(byText("Купить в кредит")).click();
        $("placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("45");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }
}
