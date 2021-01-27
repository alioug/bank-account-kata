package com.alioug.bank.domain.port;

import com.alioug.bank.domain.model.Account;

public interface TransactionRepositoryPort {

    void deposit(Account account, int amount, String date);
}
