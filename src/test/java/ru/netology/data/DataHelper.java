package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class User {
        private String login;
        private String password;

    }

    public static User getUser() {
        return new User("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(User user) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String[] card;
    }

    public static String getNumberCard(int index) {
        var cards = new Card(new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"});
        return cards.card[index];
    }

    //сумма в пределах баланса
    public static int getValidAmount(int balance) {
        return new Random().nextInt(Math.abs(balance)) + 1;
    }

    //сумма превышает баланс
    public static int getInvalidAmount(int balance) {
        return Math.abs(balance) + new Random().nextInt(1000) + 1;
    }

}