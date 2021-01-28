package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

public class GetAccountStatement {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountStatement(TransactionRepositoryPort transactionRepositoryPort,
                               AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public TransactionLog execute() {
        Account account = accountRepositoryPort.getAccount();
        TransactionLog transactionLog = new TransactionLog(0);
        transactionRepositoryPort.listAllTransactions(account)
                .forEach(transactionLog::appendLine);
        return transactionLog;
    }
}
