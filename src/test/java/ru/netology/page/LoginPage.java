package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private selenideElement LOGIN = $x("//input[@name='login']");
    private selenideElement PASSWORD = $x("//input[@name='password']");
    private selenideElement BUTTON = $x("//button[@data-test-id='action-login']");

    public VeryficationPage sucessfulLogin(DataHelper.AuthInfo info) {
        LOGIN.setValue(info.getLogin());
        PASSWORD.setValue(info.getPassword());
        BUTTON.click();
        return new VeryficationPage();
    }

}