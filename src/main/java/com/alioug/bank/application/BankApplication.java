package com.alioug.bank.application;

import com.alioug.bank.domain.account.Account;
import com.alioug.bank.domain.account.AccountRepositoryPort;
import com.alioug.bank.domain.transaction.NowSupplierPort;
import com.alioug.bank.domain.transaction.TransactionLog;
import com.alioug.bank.domain.transaction.TransactionRepositoryPort;
import com.alioug.bank.domain.usecase.GetAccountStatement;
import com.alioug.bank.domain.usecase.MakeDeposit;
import com.alioug.bank.domain.usecase.MakeWithdrawal;

public class BankApplication {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final NowSupplierPort nowSupplierPort;
    private final MakeDeposit makeDeposit;
    private final MakeWithdrawal makeWithdrawal;
    private final GetAccountStatement getAccountStatement;

    public BankApplication(AccountRepositoryPort accountRepositoryPort,
                           TransactionRepositoryPort transactionRepositoryPort,
                           NowSupplierPort nowSupplierPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.nowSupplierPort = nowSupplierPort;
        makeDeposit = new MakeDeposit(this.transactionRepositoryPort, this.accountRepositoryPort, this.nowSupplierPort);
        makeWithdrawal = new MakeWithdrawal(this.transactionRepositoryPort, this.accountRepositoryPort, this.nowSupplierPort);
        getAccountStatement = new GetAccountStatement(this.transactionRepositoryPort, this.accountRepositoryPort);
    }

    public void makeADeposit(int amountInCents) {
        Account account = accountRepositoryPort.getAccount();
        makeDeposit.execute(account, amountInCents);
    }

    public void makeAWithdrawal(int amountInCents) {
        Account account = accountRepositoryPort.getAccount();
        makeWithdrawal.execute(account, amountInCents);
    }

    public int getBalance() {
        Account account = accountRepositoryPort.getAccount();
        return account.getBalanceInCents();
    }

    public TransactionLog getAccountStatement() {
        return  getAccountStatement.execute();
    }
}
