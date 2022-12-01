package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement login = $x("//input[@name='login']");
    private SelenideElement password = $x("//input[@name='password']");
    private SelenideElement button = $x("//button[@data-test-id='action-login']");

    public VeryficationPage sucessfulLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VeryficationPage();
    }

}