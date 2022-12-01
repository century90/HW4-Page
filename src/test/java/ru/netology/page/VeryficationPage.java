package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class VeryficationPage {
    private SelenideElement pageTitle = $x("//h2[contains(.,'Интернет Банк')]");
    private SelenideElement codeInput = $x("//input[@name='code']");
    private SelenideElement enterButton = $x("//span[@class='button__content']");

    public DashboardPage typeCode(DataHelper.VerificationCode info) {
        codeInput.setValue(info.getCode());
        enterButton.click();
        return new DashboardPage();
    }
}