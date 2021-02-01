package com.alioug.bank.application;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.usecase.GetAccountStatement;
import com.alioug.bank.domain.usecase.MakeDeposit;
import com.alioug.bank.domain.usecase.MakeWithdrawal;
import com.alioug.bank.domain.usecase.NowSupplier;

public class BankApplication {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final NowSupplier nowSupplier;
    private final MakeDeposit makeDeposit;
    private final MakeWithdrawal makeWithdrawal;
    private final GetAccountStatement getAccountStatement;

    public BankApplication(AccountRepositoryPort accountRepositoryPort,
                           TransactionRepositoryPort transactionRepositoryPort,
                           NowSupplier nowSupplier) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.nowSupplier = nowSupplier;
        makeDeposit = new MakeDeposit(this.transactionRepositoryPort, this.accountRepositoryPort, this.nowSupplier);
        makeWithdrawal = new MakeWithdrawal(this.transactionRepositoryPort, this.accountRepositoryPort, this.nowSupplier);
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
