package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TranslationPage {

    private final SelenideElement AmountInput = $("[data-test-id='amount'] input");
    private final SelenideElement FromInput = $("[data-test-id='from'] input");
    private final SelenideElement TransferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement ErrorMessage = $("[data-test-id='error-notification'] .notification__content");

    public TranslationPage() {
        AmountInput.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    private void translation(int amount, String card) {
        AmountInput.setValue(String.valueOf(amount));
        FromInput.setValue(card);
        TransferButton.click();
    }

    private String errorMessage() {
        return ErrorMessage.shouldBe(Condition.visible, Duration.ofSeconds(15)).text();
    }

    public DashboardPage depositTranslation(int amount, String card) {
        translation(amount, card);
        return new DashboardPage();
    }

    public String invalidDepositTranslation(int amount, String card) {
        translation(amount, card);
        return errorMessage();
    }
}
