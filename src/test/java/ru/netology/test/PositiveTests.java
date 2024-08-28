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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveTests {

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
//        var CardPage = new CardPage();
//        var AproovedCard = DataHelper.getAproovedCard();
//        var ValidHolder = CardPage.fillValidField();
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
    }

    // @AfterEach
    // void cleanBase() {
    //     SQLHelper.cleanBase();
    // }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    CardPage cardPage = new Card Page();

    @Test
    @DisplayName("Should Buy successful Op_on") //вынести логику в другой файл
    void positiveBuySuccessfullOp(){
        cardPage.payWithDebitCard();
        cardPage.fillValidField();
        cardPage.findApprovedMessage();
    }

    @Test
    @DisplayName("Should Buy successful Operation")
    void positiveBuyAllFieldApproved() {
//         var startPage = new CardPage();
// //        var credit =startPage.goToBuyCreditPage();
//         var payment=startPage.payWithDebitCard();
//         DataHelper.getAproovedCard();
//        payment.findApprovedMessage();
//        assertEquals("APPROVED", SQLHelper.getCreditCardStatus());

        $(byText("Купить")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("125");
        $(byText("Продолжить")).click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    @DisplayName("Should Buy Refuse Operation")
    void positiveBuyRefuseOperation() {

        $(byText("Купить")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4444");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("125");
        $(byText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]")
        .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Should Buy Empty")
    void positiveBuyEmpty() {

        $(byText("Купить")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("");
        $("[placeholder=\"08\"]").setValue("");
        $("[placeholder=\"22\"]").setValue("");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("");
        $("[placeholder=\"999\"]").setValue("");
        $(byText("Продолжить")).click();
        $(byText("Неверный формат")).parent().$("class=\"input__sub\"");
//                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(byText("Поле обязательное для заполнения")).parent().$("class=\"input__sub\"");
//                .shouldHave(Condition.exactText("Поле обязательное для заполнения"));
    }

    @Test
    @DisplayName("Should Credit Refuse Operation")
    void positiveCreditrefuseOperation() {

        $(byText("Купить в кредит")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4444");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("125");
        $(byText("Продолжить")).click();
       $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]")
        .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("Should Credit successful Operation")
    void positiveCreditAllFieldApproved() {
//         var startPage = new CardPage();
// //        var credit =startPage.goToBuyCreditPage();
//         var payment=startPage.payWithDebitCard();
//         DataHelper.getAproovedCard();
//        payment.findApprovedMessage();
//        assertEquals("APPROVED", SQLHelper.getCreditCardStatus());

        $(byText("Купить в кредит")).click();
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue("4444 4444 4444 4441");
        $("[placeholder=\"08\"]").setValue("08");
        $("[placeholder=\"22\"]").setValue("24");
        $$(".input__inner").findBy(text("Владелец")).$(".input__control").setValue("Petr Semenov");
        $("[placeholder=\"999\"]").setValue("125");
        $(byText("Продолжить")).click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    @DisplayName("Should Credit Empty")
    void positiveCreditEmpty() {

        $(byText("Купить в кредит")).click();
        $("[placeholder=\"0000 0000 0000 0000] input").setValue("");
        $("[placeholder=\"08\"] input").setValue("");
        $("[placeholder=\"22\"] input").setValue("");
        $("[#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input]").setValue("");
        $("[placeholder=\"999\"] input").setValue("");
        $(byText("Продолжить")).click();
        $("[.input__inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(text("Неверный формат"));
        $("[.input_inner]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(text("Поле обязательное для заполнения"));
    }
}
