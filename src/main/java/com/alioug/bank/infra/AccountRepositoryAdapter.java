package com.alioug.bank.infra;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.port.AccountRepositoryPort;

public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private Account uniqueAccount = new Account();

    @Override
    public Account getAccount() {
        return uniqueAccount;
    }

    @Override
    public void save(Account account) {
        uniqueAccount = account;
    }
}
