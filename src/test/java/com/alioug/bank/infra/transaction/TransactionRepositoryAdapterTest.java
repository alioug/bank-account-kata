package com.alioug.bank.infra.transaction;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.Transaction;
import org.junit.Before;
import org.junit.Test;

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
        Account account = new Account();

        transactionRepositoryAdapter.deposit(account.getId(), 500, "2020-01-01");

        Transaction expectedTransaction = new Transaction(account.getId(), 500, "2020-01-01");
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsOnly(expectedTransaction);
    }

    @Test
    public void should_save_withdrawal_transaction() {
        Account account = new Account();

        transactionRepositoryAdapter.withdrawal(account.getId(), 500, "2020-01-01");

        Transaction expectedTransaction = new Transaction(account.getId(), -500, "2020-01-01");
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsOnly(expectedTransaction);
    }

    @Test
    public void should_keep_transaction_insertion_order() {
        Account account = new Account();

        transactionRepositoryAdapter.deposit(account.getId(), 100, "2020-01-01");
        transactionRepositoryAdapter.deposit(account.getId(), 200, "2020-01-02");
        transactionRepositoryAdapter.deposit(account.getId(), 300, "2020-01-03");

        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        assertThat(transactions).containsExactly(
                new Transaction(account.getId(), 100, "2020-01-01"),
                new Transaction(account.getId(), 200, "2020-01-02"),
                new Transaction(account.getId(), 300, "2020-01-03"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_be_able_to_modify_transactions_outside_repository() {
        Account account = new Account();
        List<Transaction> transactions = transactionRepositoryAdapter.listAllTransactions(account);
        transactions.add(new Transaction(account.getId(), 10000000, "2020-01-01"));

        List<Transaction> savedTransactions = transactionRepositoryAdapter.listAllTransactions(account);

        assertThat(savedTransactions).isEmpty();
    }
}