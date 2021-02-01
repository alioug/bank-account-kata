package com.alioug.bank.domain.transaction;

import com.alioug.bank.domain.account.Account;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepositoryPort {

    void deposit(String accountId, BigDecimal amount, String date);

    void withdrawal(String accountId, BigDecimal amount, String date);

    List<Transaction> listAllTransactions(Account account);
}
