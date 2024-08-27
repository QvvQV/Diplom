package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.Card;
import ru.netology.page.CardPage;
import ru.netology.data.SQLHelper;
//import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
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
    void positiveAllFieldApproved(){
//         var startPage = new CardPage();
// //        var credit =startPage.goToBuyCreditPage();
//         var payment=startPage.payWithDebitCard();
//         DataHelper.getAproovedCard();
//        payment.findApprovedMessage();
//        assertEquals("APPROVED", SQLHelper.getCreditCardStatus());

            $(byText("Купить")).click();
            $("[placeholder=\"0000 0000 0000 0000] input").setValue(""4444 4444 4444 4441"");
            $("[placeholder=\"08\"] input").setValue("08");
            $("[placeholder=\"22\"] input").setValue("24");
            $("[#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input]").setvalue("Semenov Petrov");
            $("[placeholder=\"999\"] input").setValue("125");
            $(byText("Продолжить")).click();
            $("[.notification_status_ok]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                    .shouldHave(Condition.text("Операция одобрена Банком."));

    }
}
