package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.NowSupplierPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

public class MakeDeposit {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;
    private final NowSupplierPort nowSupplierPort;

    public MakeDeposit(TransactionRepositoryPort transactionRepositoryPort,
                       AccountRepositoryPort accountRepositoryPort,
                       NowSupplierPort nowSupplier) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.nowSupplierPort = nowSupplier;
    }

    public void execute(Account account, int amount) {
        account.deposit(amount);
        transactionRepositoryPort.deposit(account.getId(), amount, nowSupplierPort.get());
        accountRepositoryPort.save(account);
    }
}
