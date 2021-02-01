package com.alioug.bank.domain.usecase;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.NowSupplierPort;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;

import java.math.BigDecimal;

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

    public void execute(Account account, BigDecimal amount) {
        account.withdrawal(amount);
        transactionRepositoryPort.withdrawal(account.getId(), amount, nowSupplierPort.get());
        accountRepositoryPort.save(account);
    }
}
