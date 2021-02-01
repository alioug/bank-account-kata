package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.NowSupplierPort;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.time.NowSupplierMock;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class MakeWithdrawalTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final TransactionRepositoryPort transactionRepositoryPort = Mockito.mock(TransactionRepositoryAdapter.class);
    private final AccountRepositoryPort accountRepositoryPort = Mockito.mock(AccountRepositoryAdapter.class);
    private final NowSupplierPort nowSupplier = new NowSupplierMock();

    private MakeWithdrawal makeWithdrawal;

    @Before
    public void init() {
        makeWithdrawal = new MakeWithdrawal(transactionRepositoryPort, accountRepositoryPort, nowSupplier);
    }

    @Test
    public void should_update_account_and_transaction_repositories_when_withdrawal() {
        Account account = new Account("0");
        makeWithdrawal.execute(account, new BigDecimal(500));

        verify(transactionRepositoryPort, atLeastOnce()).withdrawal(eq(account.getId()), eq(new BigDecimal(500)), anyString());
        verify(accountRepositoryPort, atLeastOnce()).save(any());
    }
}