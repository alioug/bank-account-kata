package com.alioug.bank.domain.transaction;

import java.util.Objects;

public class Transaction {

    private final String accountId;
    private final int amountInCents;
    private final String date;

    public Transaction(String accountId, int amountInCents, String date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return accountId == that.accountId && amountInCents == that.amountInCents && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amountInCents, date);
    }
}
