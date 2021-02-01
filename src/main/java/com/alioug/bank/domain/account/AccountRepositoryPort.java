package com.alioug.bank.domain.account;

public interface AccountRepositoryPort {

    Account getAccount();
    void save(Account account);
}
