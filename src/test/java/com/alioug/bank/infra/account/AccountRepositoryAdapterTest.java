package com.alioug.bank.infra.account;

import com.alioug.bank.domain.account.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryAdapterTest {

    private AccountRepositoryAdapter accountRepositoryAdapter;

    @Before
    public void init() {
        accountRepositoryAdapter = new AccountRepositoryAdapter();
    }

    @Test
    public void should_save_account() {
        Account account = accountRepositoryAdapter.getAccount();
        account.deposit(new BigDecimal(100));

        accountRepositoryAdapter.save(account);

        Account savedAccount = accountRepositoryAdapter.getAccount();
        assertThat(savedAccount)
                .usingRecursiveComparison()
                .isEqualTo(account);
    }

    @Test
    public void should_not_modify_account_without_save() {
        Account account = accountRepositoryAdapter.getAccount();
        account.deposit(new BigDecimal(100000));

        Account savedAccount = accountRepositoryAdapter.getAccount();

        assertThat(savedAccount.getBalance()).isEqualTo(new BigDecimal(0));
    }
}