package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;

public class MakeWithdrawal {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;
    private final NowSupplier nowSupplier;

    public MakeWithdrawal(TransactionRepositoryPort transactionRepositoryPort,
                          AccountRepositoryPort accountRepositoryPort,
                          NowSupplier nowSupplier) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.nowSupplier = nowSupplier;
    }

    public void execute(Account account, int amountInCents) {
        account.withdrawal(amountInCents);
        transactionRepositoryPort.withdrawal(account.getId(), amountInCents, nowSupplier.get());
        accountRepositoryPort.save(account);
    }
}
