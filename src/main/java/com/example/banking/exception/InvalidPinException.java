package com.example.banking.exception;

public class InvalidPinException extends RuntimeException{
    public InvalidPinException(String message)
    {
        super(message);
    }
}
