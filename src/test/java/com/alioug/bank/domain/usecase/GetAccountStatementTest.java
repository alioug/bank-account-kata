package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.Transaction;
import com.alioug.bank.domain.transaction.TransactionLog;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
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
        Account account = new Account("0");
        when(accountRepositoryPort.getAccount()).thenReturn(account);
        when(transactionRepositoryPort.listAllTransactions(account)).thenReturn(List.of(
                new Transaction(account.getId(), new BigDecimal(100), "2020-01-01"),
                new Transaction(account.getId(), new BigDecimal(500), "2020-01-02"),
                new Transaction(account.getId(), new BigDecimal(-300), "2020-01-03")
        ));

        TransactionLog accountStatement = getAccountStatement.execute();

        Assertions.assertThat(accountStatement.lines()).containsExactly(
                "2020-01-01\t100\t\t100\n",
                "2020-01-02\t500\t\t600\n",
                "2020-01-03\t-300\t\t300\n");
    }
}