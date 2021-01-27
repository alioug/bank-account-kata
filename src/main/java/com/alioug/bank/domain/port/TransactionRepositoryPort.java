package com.alioug.bank.domain.port;

public interface TransactionRepositoryPort {

    void deposit(int accountId, int amountInCents, String date);
}
