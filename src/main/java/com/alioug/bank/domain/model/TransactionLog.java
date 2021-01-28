package com.alioug.bank.domain.model;

import java.util.LinkedList;
import java.util.List;

public class TransactionLog {

    public static final String STATEMENT_HEADER = "date\t\t\t\tamount\t\tbalance\n";
    private final List<String> transactionLog = new LinkedList<>();
    private int balance;

    public TransactionLog(int initialBalance) {
        balance = initialBalance;
    }

    public TransactionLog() {
        this(0);
    }

    public void appendLine(Transaction transaction) {
        balance += transaction.getAmountInCents();
        transactionLog.add( formatLine(transaction, balance) );
    }

    private String formatLine(Transaction transaction, int currentBalance) {
        return transaction.getDate() + "\t" +
                formatDecimal(transaction.getAmountInCents()) + "\t\t" +
                formatDecimal(currentBalance) + "\n";
    }

    private String formatDecimal(int amountInCents) {
        Double amount = amountInCents / 100d;
        return String.format("%.2f", amount);
    }

    public int size() {
        return transactionLog.size();
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
