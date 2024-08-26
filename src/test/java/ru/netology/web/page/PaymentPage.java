package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
//import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement payButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    public PaymentPage() {
      heading.shouldBe(Condition.visible);
  }

      public void payWithDebitCard() {
        payButton.click();
    }

      public void payWithCreditCard() {
        creditButton.click();
    }

      public void clickContinueButton() {
        continueButton.click();
    }

    private static ElementsCollection fields = $$(".");
    private SelenideElement inputCardNumber = $([""]);
    private SelenideElement inputMonth = $([""]);
    private SelenideElement inputYears = $([""]);
    private SelenideElement inputOwner = $([""]);
    private SelenideElement inputCVV = $([""]);
    private SelenideElement approvedMessage = $([""]);
    private SelenideElement declimedMessage = $([""]);

      public void findWrongFormat() {
        $(byText("Неверный формат")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void requiredField() {
        $(byText("Поле обязательное для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void incorrectDate() {
        $(byText("Неверно указан срок действия карты")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void timeLimitDate() {
        $(byText("Истёк срок действия карты")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void findApprovedMessage() {
        $(byText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void findDeclimedMessage() {
      $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void fillValidField() { //заполение полей верными данными
        inputCardNumber.setValue(DataHelper.getAproovedCard());
        inputMonth.setValue(DataHelper.generateMonth());
        inputYears.setValue(DataHelper.generateYear());
        inputOwner.setValue(DataHelper.generateValidCardHolder());
        inputCVV.setValue(DataHelper.generateCVC());
        clickContinueButton();
    }

    public void fillDeclinedCardField() { //заполенние полей с отказанной картой
        inputCardNumber.setValue(DataHelper.getDeclineCard());
        inputMonth.setValue(DataHelper.generateMonth());
        inputYears.setValue(DataHelper.generateYear());
        inputOwner.setValue(DataHelper.generateValidCardHolder());
        inputCVV.setValue(DataHelper.generateCVC());
        clickContinueButton();
    }

    public void fillInvalidCardField() { //заполение полей с картой не в БД
        inputCardNumber.setValue(DataHelper.getNotDBCard());
        inputMonth.setValue(DataHelper.generateMonth());
        inputYears.setValue(DataHelper.generateYear());
        inputOwner.setValue(DataHelper.generateValidCardHolder());
        inputCVV.setValue(DataHelper.generateCVC());
        clickContinueButton()
    }

    public void emptyField() { //пустые поля карты
        clickContinueButton()
    }

    public void emptyMonth(){
        
    }
}
