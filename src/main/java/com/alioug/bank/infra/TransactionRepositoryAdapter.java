package com.alioug.bank.infra;

import com.alioug.bank.domain.port.TransactionRepositoryPort;
import com.alioug.bank.domain.model.Account;

public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    @Override
    public void deposit(Account account, int amount, String date) {
        // TODO
    }
}
