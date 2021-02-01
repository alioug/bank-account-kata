package com.alioug.bank.domain.transaction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionLog {

    public static final String STATEMENT_HEADER = "date\t\t\t\tamount\t\tbalance\n";
    private final List<String> transactionLog = new LinkedList<>();
    private BigDecimal balance;

    public TransactionLog(BigDecimal initialBalance) {
        balance = initialBalance;
    }

    public TransactionLog() {
        this(new BigDecimal(0));
    }

    public void appendLine(Transaction transaction) {
        balance = balance.add(transaction.getAmount());
        transactionLog.add( formatLine(transaction, balance) );
    }

    private String formatLine(Transaction transaction, BigDecimal currentBalance) {
        return transaction.getDate() + "\t" +
                transaction.getAmount() + "\t\t" +
                currentBalance + "\n";
    }

    public List<String> lines() {
        return Collections.unmodifiableList(transactionLog);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(STATEMENT_HEADER);
        for (String line : transactionLog) {
            sb.append(line);
        }
        return sb.toString();
    }
}
