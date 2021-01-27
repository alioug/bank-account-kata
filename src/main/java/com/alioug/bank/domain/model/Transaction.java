package com.alioug.bank.domain.model;

public class Transaction {

    private int accountId;
    private final int amountInCents;
    private final String date;

    public Transaction(int accountId, int amountInCents, String date) {
        this.accountId = accountId;
        this.amountInCents = amountInCents;
        this.date = date;
    }

    public int getAmountInCents() {
        return amountInCents;
    }

    public String getDate() {
        return date;
    }
}
