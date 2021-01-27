package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.model.Account;

public class MakeDeposit {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final NowSupplier nowSupplier;

    public MakeDeposit(TransactionRepositoryPort transactionRepositoryPort, NowSupplier nowSupplier) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.nowSupplier = nowSupplier;
    }

    public void execute(Account account, int amount) {
        transactionRepositoryPort.deposit(account.getId(), amount, nowSupplier.get());
    }
}
