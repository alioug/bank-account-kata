package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.NowSupplierPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

public class MakeWithdrawal {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;
    private final NowSupplierPort nowSupplierPort;

    public MakeWithdrawal(TransactionRepositoryPort transactionRepositoryPort,
                          AccountRepositoryPort accountRepositoryPort,
                          NowSupplierPort nowSupplierPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.nowSupplierPort = nowSupplierPort;
    }

    public void execute(Account account, int amountInCents) {
        account.withdrawal(amountInCents);
        transactionRepositoryPort.withdrawal(account.getId(), amountInCents, nowSupplierPort.get());
        accountRepositoryPort.save(account);
    }
}
