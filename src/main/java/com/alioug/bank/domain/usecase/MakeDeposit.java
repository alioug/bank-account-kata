package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

public class MakeDeposit {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;
    private final NowSupplier nowSupplier;

    public MakeDeposit(TransactionRepositoryPort transactionRepositoryPort,
                       AccountRepositoryPort accountRepositoryPort,
                       NowSupplier nowSupplier) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.nowSupplier = nowSupplier;
    }

    public void execute(Account account, int amount) {
        account.deposit(amount);
        transactionRepositoryPort.deposit(account.getId(), amount, nowSupplier.get());
        accountRepositoryPort.save(account);
    }
}
