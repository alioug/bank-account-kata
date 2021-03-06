package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.time.NowSupplier;
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

public class MakeDepositTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final TransactionRepositoryPort transactionRepositoryPort = Mockito.mock(TransactionRepositoryAdapter.class);
    private final AccountRepositoryPort accountRepositoryPort = Mockito.mock(AccountRepositoryAdapter.class);
    private final NowSupplier nowSupplier = new NowSupplier();

    private MakeDeposit makeDeposit;

    @Before
    public void init() {
        makeDeposit = new MakeDeposit(transactionRepositoryPort, accountRepositoryPort, nowSupplier);
    }

    @Test
    public void should_update_transaction_and_account_repositories_when_deposit() {
        Account account = new Account("0");
        makeDeposit.execute(account, new BigDecimal(500));

        verify(transactionRepositoryPort, Mockito.atLeastOnce()).deposit(eq(account.getId()), eq(new BigDecimal(500)), anyString());
        verify(accountRepositoryPort, atLeastOnce()).save(any());
    }
}