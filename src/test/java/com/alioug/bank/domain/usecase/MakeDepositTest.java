package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.model.Account;
import com.alioug.bank.infra.TransactionRepositoryAdapter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MakeDepositTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final TransactionRepositoryPort transactionRepositoryPort = Mockito.mock(TransactionRepositoryAdapter.class);
    private final NowSupplier nowSupplier = new NowSupplier();

    private final MakeDeposit makeDeposit = new MakeDeposit(transactionRepositoryPort, nowSupplier);

    @Test
    public void should_deposit_money() {
        Account account = new Account();
        makeDeposit.execute(account, 500);
        Mockito.verify(transactionRepositoryPort, Mockito.atLeastOnce()).deposit(any(), anyInt(), anyString());
    }
}