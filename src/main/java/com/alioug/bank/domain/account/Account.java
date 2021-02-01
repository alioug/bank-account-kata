package com.alioug.bank.domain.account;

public class Account {

    private int accountId;
    private int balanceInCents;

    public Account() {
        balanceInCents = 0;
    }

    public Account(Account account) {
        this.accountId = account.accountId;
        this.balanceInCents = account.balanceInCents;
    }

    public void deposit(int amountInCents) {
        if(amountInCents < 0) {
            throw new IllegalArgumentException("amount must be positive : " + amountInCents);
        }
        balanceInCents += amountInCents;
    }

    public void withdrawal(int amountInCents) {
        if(amountInCents < 0) {
            throw new IllegalArgumentException("amount must be positive : " + amountInCents);
        }
        balanceInCents -= amountInCents;
    }

    public int getId() {
        return accountId;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }
}
