package com.alioug.bank.domain.transaction;

import com.alioug.bank.domain.account.Account;

import java.util.List;

public interface TransactionRepositoryPort {

    void deposit(String accountId, int amountInCents, String date);

    void withdrawal(String accountId, int amountInCents, String date);

    List<Transaction> listAllTransactions(Account account);
}
