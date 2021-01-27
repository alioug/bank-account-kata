package com.alioug.bank.infra.transaction;

import com.alioug.bank.domain.model.Transaction;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

import java.util.LinkedList;
import java.util.List;

public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final List<Transaction> transactions = new LinkedList<>();

    @Override
    public void deposit(int accountId, int amountInCents, String date) {
        Transaction depositTransaction = new Transaction(accountId, amountInCents, date);
        saveTransaction(depositTransaction);
    }

    @Override
    public void withdrawal(int accountId, int amountInCents, String date) {
        Transaction depositTransaction = new Transaction(accountId, -amountInCents, date);
        saveTransaction(depositTransaction);
    }

    private void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
