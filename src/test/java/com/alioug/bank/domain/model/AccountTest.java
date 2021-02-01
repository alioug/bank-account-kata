package com.alioug.bank.domain.model;

import com.alioug.bank.domain.account.Account;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    private final Account account = new Account("0");

    @Test
    public void should_update_balance_after_deposit() {
        account.deposit(new BigDecimal(100));
        account.deposit(new BigDecimal(500));
        Assertions.assertThat(account.getBalance()).isEqualTo(new BigDecimal(600));
    }

    @Test
    public void should_update_balance_after_withdrawal() {
        account.withdrawal(new BigDecimal(500));
        Assertions.assertThat(account.getBalance()).isEqualTo(new BigDecimal(-500));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_only_accept_positive_values_for_deposit() {
        account.deposit(new BigDecimal(-100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_only_accept_positive_values_for_withdrawal() {
        account.withdrawal(new BigDecimal(-100));
    }
}