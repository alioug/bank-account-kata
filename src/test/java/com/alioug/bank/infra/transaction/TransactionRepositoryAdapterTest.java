package com.alioug.bank.infra.transaction;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionRepositoryAdapterTest {

    private TransactionRepositoryAdapter transactionRepositoryAdapter;

    @Before
    public void init() {
        transactionRepositoryAdapter = new TransactionRepositoryAdapter();
    }

    @Test
    public void should_save_deposit_transaction() {
        Account account = new Account("0");

        transactionRepositoryAdapter.deposit(account.getId(), new BigDecimal(500), "2020-01-01");

        Transaction expectedTransaction = new Transaction(account.getId(), new BigDecimal(500), "2020-01-01");
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsOnly(expectedTransaction);
    }

    @Test
    public void should_save_withdrawal_transaction() {
        Account account = new Account("0");

        transactionRepositoryAdapter.withdrawal(account.getId(), new BigDecimal(500), "2020-01-01");

        Transaction expectedTransaction = new Transaction(account.getId(), new BigDecimal(-500), "2020-01-01");
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsOnly(expectedTransaction);
    }

    @Test
    public void should_keep_transaction_insertion_order() {
        Account account = new Account("0");

        transactionRepositoryAdapter.deposit(account.getId(), new BigDecimal(100), "2020-01-01");
        transactionRepositoryAdapter.deposit(account.getId(), new BigDecimal(200), "2020-01-02");
        transactionRepositoryAdapter.deposit(account.getId(), new BigDecimal(300), "2020-01-03");

        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsExactly(
                new Transaction(account.getId(), new BigDecimal(100), "2020-01-01"),
                new Transaction(account.getId(), new BigDecimal(200), "2020-01-02"),
                new Transaction(account.getId(), new BigDecimal(300), "2020-01-03"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_be_able_to_modify_transactions_outside_repository() {
        Account account = new Account("0");
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        transactions.add(new Transaction(account.getId(), new BigDecimal(10000000), "2020-01-01"));

        List<Transaction> savedTransactions = transactionRepositoryAdapter.listAllTransactions(account);

        assertThat(savedTransactions).isEmpty();
    }
}