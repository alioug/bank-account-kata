package com.alioug.bank.application;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankApplicationIT {

    private final BankApplication bankApplication = new BankApplication();

    @Test
    public void should_make_a_deposit() {
        bankApplication.makeADeposit(500);
        bankApplication.makeADeposit(300);
        assertThat(bankApplication.getBalance()).isEqualTo(800);
    }
}