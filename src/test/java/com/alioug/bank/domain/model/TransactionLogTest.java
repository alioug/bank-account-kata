package com.alioug.bank.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionLogTest {

    private final TransactionLog transactionLog = new TransactionLog();

    @Test
    public void should_append_line() {
        transactionLog.appendLine(new Transaction(0, 100, "2020-01-01"));

        assertThat(transactionLog.size()).isEqualTo(1);
    }

    @Test
    public void should_print_and_format_lines() {
        transactionLog.appendLine(new Transaction(0, 100, "2020-01-01"));

        assertThat(transactionLog.toString()).isEqualTo(
                "date\t\t\t\tamount\t\tbalance\n" +
                "2020-01-01\t1,00\t\t1,00\n");
    }
}