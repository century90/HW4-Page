package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    private int firstCard = 0;
    private int secondCard = 1;

    @Test
    void shouldTransferMoneyFromCard1ToCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(secondCard);
        int amount = 5000;
        transferPage.transferMoney(DataHelper.getFirstCardInfo().getCardNumber(), amount);
        assertEquals(balanceOfCard1 - amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(balanceOfCard2 + amount, dashboardPage.getCardBalance(secondCard));
    }

    @Test
    void shouldTransferMoneyFromCard2ToCard1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(firstCard);
        int amount = 3000;
        transferPage.transferMoney(DataHelper.getSecondCardInfo().getCardNumber(), amount);
        assertEquals(balanceOfCard1 + amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(balanceOfCard2 - amount, dashboardPage.getCardBalance(secondCard));
    }

    @Test
    void shouldTransferFromCard2ToCard1OverBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(firstCard);
        int amount = 15000;
        transferPage.transferMoney(DataHelper.getSecondCardInfo().getCardNumber(), amount);
        transferPage.errorMassage();
    }

    @Test
    void shouldTransferFromCard1ToCard2OverBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(secondCard);
        int amount = 15000;
        transferPage.transferMoney(DataHelper.getFirstCardInfo().getCardNumber(), amount);
        transferPage.errorMassage();
    }

    @Test
    void shouldTransferFromCard2ToCard1Zero() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(firstCard);
        int amount = 0;
        transferPage.transferMoney(DataHelper.getSecondCardInfo().getCardNumber(), amount);
        assertEquals(balanceOfCard1 + amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(balanceOfCard2 - amount, dashboardPage.getCardBalance(secondCard));
    }

    @Test
    void shouldTransferFromCard1ToCard2Zero() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.sucessfulLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.typeCode(verificationCode);

        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.replenish(secondCard);
        int amount = 0;
        transferPage.transferMoney(DataHelper.getFirstCardInfo().getCardNumber(), amount);
        assertEquals(balanceOfCard1 + amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(balanceOfCard2 - amount, dashboardPage.getCardBalance(secondCard));
    }
}