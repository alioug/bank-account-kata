package com.alioug.bank.domain.port;

import com.alioug.bank.domain.model.Account;

public interface AccountRepositoryPort {

    Account getAccount();
    void save(Account account);
}
