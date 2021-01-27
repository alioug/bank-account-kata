package com.alioug.bank.domain.port;

import com.alioug.bank.domain.model.Account;
import com.alioug.bank.domain.model.Transaction;

import java.util.List;

public interface TransactionRepositoryPort {

    void deposit(int accountId, int amountInCents, String date);

    void withdrawal(int accountId, int amountInCents, String date);

    List<Transaction> listAllTransactions(Account account);
}
