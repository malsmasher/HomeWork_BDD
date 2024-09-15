package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankTest {

    DashboardPage dashboardPage;
    int balanceFirstCard;
    int balanceSecondCard;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");

        var user = DataHelper.getUser();
        var loginPage = new LoginPage();
        var verification = loginPage.authorizationOnLoginPage(user);

        var code = DataHelper.getVerificationCode(user);
        dashboardPage = verification.verificationCode(code);

        balanceFirstCard = dashboardPage.getCardBalance(0);
        balanceSecondCard = dashboardPage.getCardBalance(1);


    }


    @Test
    @DisplayName("Should successfully transfer funds")
    void shouldSuccessfullyTransferFunds() {

        var translationPage = dashboardPage.selectDeposit(1);


        int amount = DataHelper.getValidAmount(balanceFirstCard);
        dashboardPage = translationPage.depositTranslation(amount, DataHelper.getNumberCard(0));

        int expectedFirst = balanceFirstCard - amount;
        int expectedSecond = balanceSecondCard + amount;

        assertAll(
                () -> assertEquals(expectedFirst, dashboardPage.getCardBalance(0)),
                () -> assertEquals(expectedSecond, dashboardPage.getCardBalance(1)));


    }


    @Test
    @DisplayName("Transfer amount more balance")
    void transferAmountMoreBalance() {

        var translationPage = dashboardPage.selectDeposit(1);

        int amount = DataHelper.getInvalidAmount(balanceFirstCard);
        String errorMessage = translationPage.invalidDepositTranslation(amount, DataHelper.getNumberCard(0));

        assertEquals("Недостаточно средств на карте", errorMessage);

    }
}
