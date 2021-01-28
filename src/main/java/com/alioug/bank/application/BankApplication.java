package com.alioug.bank.application;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.TransactionLog;
import com.alioug.bank.domain.port.AccountRepositoryPort;
import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.usecase.GetAccountStatement;
import com.alioug.bank.domain.usecase.MakeDeposit;
import com.alioug.bank.domain.usecase.MakeWithdrawal;
import com.alioug.bank.domain.usecase.NowSupplier;
import com.alioug.bank.infra.account.AccountRepositoryAdapter;
import com.alioug.bank.infra.transaction.TransactionRepositoryAdapter;

public class BankApplication {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final NowSupplier nowSupplier;
    private final MakeDeposit makeDeposit;
    private final MakeWithdrawal makeWithdrawal;
    private final GetAccountStatement getAccountStatement;

    public BankApplication() {
        accountRepositoryPort = new AccountRepositoryAdapter();
        transactionRepositoryPort = new TransactionRepositoryAdapter();
        nowSupplier = new NowSupplier();
        makeDeposit = new MakeDeposit(transactionRepositoryPort, accountRepositoryPort, nowSupplier);
        makeWithdrawal = new MakeWithdrawal(transactionRepositoryPort, accountRepositoryPort, nowSupplier);
        getAccountStatement = new GetAccountStatement(transactionRepositoryPort, accountRepositoryPort);
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
