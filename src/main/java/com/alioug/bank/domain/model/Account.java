package com.alioug.bank.domain.model;

public class Account {

    private int accountId;
    private int balanceInCents;

    public Account() {
        balanceInCents = 0;
    }

    public void deposit(int amountInCents) {
        balanceInCents += amountInCents;
    }

    public int getId() {
        return accountId;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }
}
