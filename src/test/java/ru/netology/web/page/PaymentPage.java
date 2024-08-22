package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
//import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentMethod() {
    private SelenideElement payButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    public PaymentMethod() {
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

    public void findApprovedByBand() {
        $(byText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void findDeclimedByBand() {
      $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
