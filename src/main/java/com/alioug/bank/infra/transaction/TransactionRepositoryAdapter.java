package com.alioug.bank.infra.transaction;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.transaction.Transaction;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final List<Transaction> transactions = new LinkedList<>();

    @Override
    public void deposit(String accountId, BigDecimal amount, String date) {
        Transaction depositTransaction = new Transaction(accountId, amount, date);
        saveTransaction(depositTransaction);
    }

    @Override
    public void withdrawal(String accountId, BigDecimal amount, String date) {
        Transaction withdrawalTransaction = new Transaction(accountId, amount.negate(), date);
        saveTransaction(withdrawalTransaction);
    }

    @Override
    public List<Transaction> listAllTransactions(Account account) {
        return Collections.unmodifiableList(transactions);
    }

    private void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
