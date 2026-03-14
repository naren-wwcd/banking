package com.example.banking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;           // BIGINT   - auto generated

    @Column(nullable = true)
    private String toAccountNumber;       // null for DEPOSIT/WITHDRAW, filled for TRANSFER

    private String accountNumber;         // VARCHAR  - links to AccountHolder
    private String type;                  // VARCHAR  - DEPOSIT/WITHDRAW/TRANSFER
    private Double amount;                // DOUBLE
    private LocalDateTime transactionDate = LocalDateTime.now(); // DATETIME - auto generated

    public Transaction() {
    }

    public Transaction(Long transactionId, String toAccountNumber, String accountNumber, String type, Double amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.toAccountNumber = toAccountNumber;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}