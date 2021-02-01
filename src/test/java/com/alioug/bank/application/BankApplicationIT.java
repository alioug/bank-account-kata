package com.alioug.bank.application;

import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.usecase.NowSupplier;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;
import org.junit.Before;
import org.junit.Test;

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
    public void should_get_account_statement() {
        bankApplication.makeADeposit(500);
        bankApplication.makeAWithdrawal(100);
        bankApplication.makeADeposit(1000);
        bankApplication.makeAWithdrawal(300);

        TransactionLog transactionLog = bankApplication.getAccountStatement();
        System.out.println(transactionLog);

        assertThat(transactionLog.lines()).containsExactly(
                "01/01/2021 00:00\t5,00\t\t5,00\n",
                "01/01/2021 00:00\t-1,00\t\t4,00\n",
                "01/01/2021 00:00\t10,00\t\t14,00\n",
                "01/01/2021 00:00\t-3,00\t\t11,00\n"
        );
    }

    public static class NowSupplierMock extends NowSupplier {
        @Override
        public String get() {
            return "01/01/2021 00:00";
        }
    }

}