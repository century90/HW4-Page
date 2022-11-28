package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private selenideElement TRANSFER_PAGE_TITLE = $x("//h2[@data-test-id='dashboard']");
    private selenideElement AMOUNT_INPUT = $("[data-test-id='amount'] input");
    private selenideElement FROM_INPUT = $("[data-test-id='from'] input");
    private selenideElement ACTION_TRANSFER_BTN = $x("//button[@data-test-id='action-transfer']");
    private selenideElement CANCEL_BTN = $x("//button[@data-test-id='action-cancel']");
    private selenideElement ERROR_MESSAGE = $x("//div[@data-test-id='error-notification']");

    public TransferPage() {
        TRANSFER_PAGE_TITLE.shouldBe(Condition.visible);
    }

    public void errorMassage() {
        ERROR_MESSAGE.shouldHave(exactText("Ошибка!"))
                .shouldBe(Condition.visible);
    }

    public DashboardPage transferMoney(String cardFrom, int sum) {
        AMOUNT_INPUT.sendKeys(Keys.LEFT_CONTROL + "A");
        AMOUNT_INPUT.sendKeys(Keys.BACK_SPACE);
        AMOUNT_INPUT.setValue(String.valueOf(sum));
        FROM_INPUT.sendKeys(Keys.LEFT_CONTROL + "A");
        FROM_INPUT.sendKeys(Keys.BACK_SPACE);
        FROM_INPUT.setValue(cardFrom);
        ACTION_TRANSFER_BTN.click();
        return new DashboardPage();
    }
}