package com.alioug.bank.domain.account;

import java.math.BigDecimal;

public class Account {

    private final String accountId;
    private BigDecimal balance;

    public Account(String accountId) {
        this.accountId = accountId;
        balance = new BigDecimal(0);
    }

    public Account(Account account) {
        this.accountId = account.accountId;
        this.balance = account.balance;
    }

    public void deposit(BigDecimal amount) {
        if(isNegative(amount)) {
            throw new IllegalArgumentException("amount must be positive : " + amount);
        }
        balance = balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        if(isNegative(amount)) {
            throw new IllegalArgumentException("amount must be positive : " + amount);
        }
        balance = balance.subtract(amount);
    }

    private boolean isNegative(BigDecimal amount) {
        return amount.signum() < 0;
    }

    public String getId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
