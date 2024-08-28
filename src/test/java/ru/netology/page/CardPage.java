package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.Card;
import ru.netology.data.SQLHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage {
    private SelenideElement payButton = $(byText("Купить"));
    private SelenideElement payCreditButton = $(byText("Купить в кредит"));
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    public CardPage() {
      heading.shouldBe(Condition.visible);
  }

      public CardPage payWithDebitCard() {
        payButton.click();
        // return new CardPage();
    }

    public CardPage goToBuyCreditPage() {
        payCreditButton.click();
        // return new CardPage();
    }

      public void clickContinueButton() {
        continueButton.click();
    }

    private static final ElementsCollection fields = $$(".input__control");
    private SelenideElement inputCardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement inputMonth = $("[placeholder=\"08\"]");
    private SelenideElement inputYears = $("[placeholder=\"22\"]");
    private SelenideElement inputOwner = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private SelenideElement inputCVV = $("[placeholder=\"999\"]");
    private SelenideElement approvedMessage = $(".notification_status_ok");
    private SelenideElement declimedMessage = $(".notification_status_error .icon_name_close");

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

    public void findContinue() {
        $(byText("Продолжить")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void fillValidField() { //заполнение полей верными данными
        inputCardNumber.setValue(DataHelper.getAproovedCard());
        // inputMonth.setValue(DataHelper.generateMonth());
        // inputYears.setValue(DataHelper.generateYear());
        inputOwner.setValue(DataHelper.generateValidCardHolder());
        inputCVV.setValue(DataHelper.generateCVC());
        clickContinueButton();
    }

//    public fillDeclinedCardField() { //заполнение полей с отказанной картой
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateValidCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillInvalidCardField() { //заполнение полей с картой не в БД
//        inputCardNumber.setValue(DataHelper.getNotDBCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateValidCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void emptyField() { //пустые поля
//        clickContinueButton();
//    }
//
//    public void fillInvalidHolderAproovedCard(){ //заполнение поля владелец кириллицей с подтвержд картой
//        inputCardNumber.setValue(DataHelper.getAproovedCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateInvalidCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillUpperHolderAproovedCard() { //заполнение поля владелец высоким регистром и только имя с подтвержд картой
//        inputCardNumber.setValue(DataHelper.getAproovedCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateToUpperCaseCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillLowerHolderAproovedcard() { //заполнение поля владелец низким регистром и только имя с подтвержд картой
//        inputCardNumber.setValue(DataHelper.getAproovedCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateToLowerCaseCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//        public void fillInvalidHolderDeclineCard(){ //заполнение поля владелец кириллицей с не подтвержд картой
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateInvalidCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillUpperHolderDeclineCard() { //заполнение поля владелец высоким регистром и только имя с не подтвержд картой
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateToUpperCaseCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillLowerHolderDeclineCard() { //заполнение поля владелец низким регистром и только имя с не подтвержд картой
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateToLowerCaseCardHolder());
//        inputCVV.setValue(DataHelper.generateCVC());
//        clickContinueButton();
//    }
//
//    public void fillAllSpecialCharacter() { //поля заполнены спецсимволами
//        inputCardNumber.setValue(DataHelper.generateNameSpecialCharacter());
//        inputMonth.setValue(DataHelper.generateNameSpecialCharacter());
//        inputYears.setValue(DataHelper.generateNameSpecialCharacter());
//        inputOwner.setValue(DataHelper.generateNameSpecialCharacter());
//        inputCVV.setValue(DataHelper.generateNameSpecialCharacter());
//        clickContinueButton();
//    }
//
////    public void fillAllSpace() { //поля заполенены пробелами
////        inputCardNumber.setValue(DataHelper.generateSpaceName());
////        inputMonth.setValue(DataHelper.generateSpaceName());
////        inputYears.setValue(DataHelper.generateSpaceName());
////        inputOwner.setValue(DataHelper.generateSpaceName());
////        inputCVV.setValue(DataHelper.generateSpaceName());
////        clickContinueButton();
////    }
//
//    public void fillTwoNumberCVV() { //CVV заполнено двумя цифрами
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateValidCardHolder());
//        inputCVV.setValue(DataHelper.generateInvalidTwoCVC());
//        clickContinueButton();
//    }
//
//    public void fillFourNumberCVV() { //CVV заполнено четырьмя цифрами
//        inputCardNumber.setValue(DataHelper.getDeclineCard());
//        inputMonth.setValue(DataHelper.generateMonth());
//        inputYears.setValue(DataHelper.generateYear());
//        inputOwner.setValue(DataHelper.generateValidCardHolder());
//        inputCVV.setValue(DataHelper.generateInvalidFourCVC());
//        clickContinueButton();
//    }
}
