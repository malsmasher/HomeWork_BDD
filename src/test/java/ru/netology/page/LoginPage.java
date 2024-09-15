package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    public LoginPage() {
        $("[data-test-id='login'] input")
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }


    //Авторизация
    public Verification authorizationOnLoginPage(DataHelper.User user) {
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();

        return new Verification();
    }


}