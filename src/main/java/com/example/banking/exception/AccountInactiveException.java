package com.example.banking.exception;

public class AccountInactiveException extends RuntimeException{
    public AccountInactiveException(String message)
    {
        super(message);
    }
}
