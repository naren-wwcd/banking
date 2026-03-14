package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity

public class AccountHolder {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holderId;
    // BIGINT  - auto generated
    @Column(unique = true, nullable = false)
    private String accountNumber;    // VARCHAR - unique
    private String firstName;        // VARCHAR
    private String lastName;
    // VARCHAR
    @Column(unique = true, nullable = false)
    private String email;            // VARCHAR - unique
    private String phone;            // VARCHAR
    private Double balance=0.0;
    // DOUBLE  - default 0.0
    private String status="ACTIVE";


    @JsonIgnore
    private int pin;
    public AccountHolder(Long holderId, String accountNumber, String firstName, String lastName, String email, String phone, Double balance,int pin) {
        this.holderId = holderId;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.pin=pin;
    }

    public AccountHolder() {
    }

    public Long getHolderId() {
        return holderId;
    }

    public void setHolderId(Long holderId) {
        this.holderId = holderId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getBalance() {
        return balance;
    }

    @JsonIgnore
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "holderId=" + holderId +
                ", accountNumber=" + accountNumber +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", phone=" + phone +
                ", balance=" + balance +
                ", status=" +status;
    }
}