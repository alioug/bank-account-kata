package com.alioug.bank.domain.transaction;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private final String accountId;
    private final BigDecimal amount;
    private final String date;

    public Transaction(String accountId, BigDecimal amount, String date) {
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal getamount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return accountId.equals(that.accountId) && amount.equals(that.amount) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount, date);
    }
}
