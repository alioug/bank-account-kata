package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.TransactionLog;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;

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
        TransactionLog transactionLog = new TransactionLog();
        transactionRepositoryPort.listAllTransactions(account)
                .forEach(transactionLog::appendLine);
        return transactionLog;
    }
}
