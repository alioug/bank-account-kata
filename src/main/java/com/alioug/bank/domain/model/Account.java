package com.alioug.bank.domain.model;

public class Account {

    private int accountId;
    private int balanceInCents;

    public Account() {
        balanceInCents = 0;
    }

    Account(int accountId, int balanceInCents) {
        this.accountId = accountId;
        this.balanceInCents = balanceInCents;
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

    public Account clone() {
        return new Account(this.accountId, this.balanceInCents);
    }
}
