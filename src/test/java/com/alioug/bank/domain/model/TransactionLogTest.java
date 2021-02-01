package com.alioug.bank.domain.model;

import com.alioug.bank.domain.transaction.Transaction;
import com.alioug.bank.domain.transaction.TransactionLog;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionLogTest {

    private final TransactionLog transactionLog = new TransactionLog();

    @Test
    public void should_append_line() {
        transactionLog.appendLine(new Transaction("0", new BigDecimal(100), "2020-01-01"));

        assertThat(transactionLog.lines()).containsExactly("2020-01-01\t100\t\t100\n");
    }

    @Test
    public void should_print_and_format_lines() {
        transactionLog.appendLine(new Transaction("0", new BigDecimal(100), "2020-01-01"));

        assertThat(transactionLog.toString()).isEqualTo(
                "date\t\t\t\tamount\t\tbalance\n" +
                "2020-01-01\t100\t\t100\n");
    }
}