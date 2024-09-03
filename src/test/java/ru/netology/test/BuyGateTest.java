
package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyGateTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDB();
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
    @DisplayName("Заявка одобрена все поля заполнены верно")
    void buyPositiveAllFieldValidApproved() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Оплата успешна с отклоненной карты")
    void buyPositiveAllFieldValidDeclined() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Возникновение ошибки пустая заявка")
    void buyNegativeAllFieldEmpty() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле номер карты заполнено 15 цифрами")
    void buyNegativeNumberCard15Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getNumberCard15Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле номер карты заполнено 17 цифрами")
    void buyNegativeNumberCard15Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getNumberCard17Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Поле номер карты заполнено 19 цифрами (мах)")
    void buyNegativeNumberCard15Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getNumberCard19Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки покупка не существующей картой")
    void buyNegativeCardNotInDatabase() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitNotificationFailure();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле месяц заполнено одной цифрой")
    void buyNegativeMonth1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле месяц заполнено цифрой 13")
    void buyNegativeMonthOver12() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле месяц заполнено нулями нынешний год")
    void buyNegativeMonth00ThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardMonth00ThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле месяц заполнено нулями будущий год")
    void buyNegativeMonth00OverThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardMonth00OverThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле год заполнено нулями")
    void buyNegativeYear00() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле год 1 символ")
    void buyNegativeYear1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Поле год принимает дату из будущего")
    void buyNegativeYearUnderThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardYearUnderThisYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Возникновение ошибки поле год на 6 лет вперед")
    void buyNegativeYearOverThisYearOn6() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardYearOverThisYearOn6());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение CVC одной цифрой")
    void buyNegativeCvv1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardCvv1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение CVC двумя цифрами")
    void buyNegativeCvv2Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardCvv2Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение Владельца одним словом")
    void buyNegativeOwner1Word() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardHolder1Word());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение Владельца Кириллицей")
    void buyNegativeOwnerCirillic() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение Владельца числом")
    void buyNegativeOwnerNumeric() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardHolderNumeric());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    @DisplayName("Заполнение Владельца спец символами")
    void buyNegativeOwnerSpecialSymbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToBuyPage();
        payment.inputData(DataHelper.getCardSpecialSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
