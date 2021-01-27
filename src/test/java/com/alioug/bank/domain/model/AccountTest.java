package com.alioug.bank.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AccountTest {

    private final Account account = new Account();

    @Test
    public void should_update_balance_after_deposit() {
        account.deposit(100);
        account.deposit(500);
        Assertions.assertThat(account.getBalanceInCents()).isEqualTo(600);
    }

    @Test
    public void should_update_balance_after_withdrawal() {
        account.withdrawal(500);
        Assertions.assertThat(account.getBalanceInCents()).isEqualTo(-500);
    }
}