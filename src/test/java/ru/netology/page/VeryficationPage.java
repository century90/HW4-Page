package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class VeryficationPage {
    private selenideElement PAGE_TITLE = $x("//h2[contains(.,'Интернет Банк')]");
    private selenideElement CODE_INPUT = $x("//input[@name='code']");
    private selenideElement ENTER_BUTTON = $x("//span[@class='button__content']");

    public DashboardPage typeCode(DataHelper.VerificationCode info){
        CODE_INPUT.setValue(info.getCode());
        ENTER_BUTTON.click();
        return new DashboardPage();
    }
}