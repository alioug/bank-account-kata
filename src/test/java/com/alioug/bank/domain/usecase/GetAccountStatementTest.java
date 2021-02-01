package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.Transaction;
import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.when;

public class GetAccountStatementTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final TransactionRepositoryPort transactionRepositoryPort = Mockito.mock(TransactionRepositoryAdapter.class);
    private final AccountRepositoryPort accountRepositoryPort = Mockito.mock(AccountRepositoryAdapter.class);

    private GetAccountStatement getAccountStatement;

    @Before
    public void init() {
        getAccountStatement = new GetAccountStatement(transactionRepositoryPort, accountRepositoryPort);
    }

    @Test
    public void should_update_transaction_and_account_repositories_when_deposit() {
        Account account = new Account();
        when(accountRepositoryPort.getAccount()).thenReturn(account);
        when(transactionRepositoryPort.listAllTransactions(account)).thenReturn(List.of(
                new Transaction(account.getId(), 100, "2020-01-01"),
                new Transaction(account.getId(), 500, "2020-01-02"),
                new Transaction(account.getId(), -300, "2020-01-03")
        ));

        TransactionLog accountStatement = getAccountStatement.execute();

        Assertions.assertThat(accountStatement.lines()).containsExactly(
                "2020-01-01\t1,00\t\t1,00\n",
                "2020-01-02\t5,00\t\t6,00\n",
                "2020-01-03\t-3,00\t\t3,00\n");
    }
}