package com.example.banking.controller;

import com.example.banking.model.Transaction;
import com.example.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.banking.model.PinRequest;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/checkBalance/{accountNumber}")
    public String checkBalance(@PathVariable String accountNumber,@RequestBody PinRequest pinRequest)
    {
       return transactionService.checkBalance(accountNumber,pinRequest.getPin());
    }
    @PutMapping("/deposit/{accountNumber}/{amount}")
    public String depositTransaction(@PathVariable String accountNumber,@RequestBody PinRequest pinRequest,@PathVariable Double amount)
    {
        return transactionService.depositTransaction(accountNumber,pinRequest.getPin(),amount);
    }
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public String withdrawTransaction(@PathVariable String accountNumber,@PathVariable Double amount,@RequestBody PinRequest pinRequest)
    {
        return transactionService.withdrawTransaction(accountNumber,amount,pinRequest.getPin());
    }
    @PutMapping("/amountTransaction/{accountNumber1}/{accountNumber2}/{amount}")
    public String amountTransactionThroughAccount(@PathVariable String accountNumber1,@PathVariable Double amount,@RequestBody PinRequest pinRequest,@PathVariable String accountNumber2)
    {
        return transactionService.amountTransactionThroughAccount(accountNumber1,amount,pinRequest.getPin(),accountNumber2);
    }
    @GetMapping("/transactionhistory/{accountNumber}")
    public List<Transaction> transactionHistory(@PathVariable String accountNumber, @RequestBody PinRequest pinRequest)
    {
        return transactionService.transactionHistory(accountNumber,pinRequest.getPin());
    }
}
