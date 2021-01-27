package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.Transaction;
import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

import java.util.List;

public class GetAccountHistory {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountHistory(TransactionRepositoryPort transactionRepositoryPort,
                             AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public TransactionLog execute() {
        Account account = accountRepositoryPort.getAccount();
        List<Transaction> transactions = transactionRepositoryPort.listAllTransactions(account);
        TransactionLog transactionLog = new TransactionLog(0);
        for (Transaction transaction : transactions) {
            transactionLog.appendLine(transaction);
        }
        return transactionLog;
    }
}
