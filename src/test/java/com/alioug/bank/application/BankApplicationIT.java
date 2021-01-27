package com.alioug.bank.application;

import com.alioug.bank.domain.model.TransactionLog;
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

    @Test
    public void should_make_a_withdrawal() {
        bankApplication.makeAWithdrawal(300);

        assertThat(bankApplication.getBalance()).isEqualTo(-300);
    }

    @Test
    public void should_get_account_history() {
        bankApplication.makeADeposit(500);
        bankApplication.makeAWithdrawal(100);
        bankApplication.makeADeposit(1000);
        bankApplication.makeAWithdrawal(300);

        TransactionLog transactionLog = bankApplication.getAccountHistory();
        System.out.println(transactionLog);

        assertThat(transactionLog.size()).isEqualTo(4);
    }
}