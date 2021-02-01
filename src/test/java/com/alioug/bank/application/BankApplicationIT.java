package com.alioug.bank.application;

import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.TransactionLog;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.time.NowSupplier;
import com.alioug.bank.infra.time.NowSupplierMock;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BankApplicationIT {

    private final AccountRepositoryPort accountRepositoryPort = new AccountRepositoryAdapter();
    private final TransactionRepositoryPort transactionRepositoryPort = new TransactionRepositoryAdapter();

    private final NowSupplier nowSupplier = new NowSupplierMock();

    private BankApplication bankApplication;

    @Before
    public void init() {
        bankApplication = new BankApplication(accountRepositoryPort, transactionRepositoryPort, nowSupplier);
    }

    @Test
    public void should_make_a_deposit() {
        bankApplication.makeADeposit(new BigDecimal(500));
        bankApplication.makeADeposit(new BigDecimal(300));

        assertThat(bankApplication.getBalance()).isEqualTo(new BigDecimal(800));
    }

    @Test
    public void should_make_a_withdrawal() {
        bankApplication.makeAWithdrawal(new BigDecimal(300));

        assertThat(bankApplication.getBalance()).isEqualTo(new BigDecimal(-300));
    }

    @Test
    public void should_get_account_statement() {
        bankApplication.makeADeposit(new BigDecimal(500));
        bankApplication.makeAWithdrawal(new BigDecimal(100));
        bankApplication.makeADeposit(new BigDecimal(1000));
        bankApplication.makeAWithdrawal(new BigDecimal(300));

        TransactionLog transactionLog = bankApplication.getAccountStatement();
        System.out.println(transactionLog);

        assertThat(transactionLog.lines()).containsExactly(
                "01/01/2021 00:00\t500\t\t500\n",
                "01/01/2021 00:00\t-100\t\t400\n",
                "01/01/2021 00:00\t1000\t\t1400\n",
                "01/01/2021 00:00\t-300\t\t1100\n"
        );
    }

}