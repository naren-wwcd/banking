package com.example.banking.service;

import com.example.banking.exception.AccountInactiveException;
import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.exception.InvalidPinException;
import com.example.banking.model.AccountHolder;
import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountHolderRepository;
import com.example.banking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String checkBalance(String accountNumber,int pin) {


        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account number not exist");
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive");
        }

        if (pin != ac.getPin())
        {
            throw new InvalidPinException("invalid pin");
        }
        return "rupees "+ac.getBalance() +" is your balance";
    }

    @Transactional
    public String depositTransaction(String accountNumber, int pin,Double amount) {

        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account number not exist") ;
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive") ;
        }

        if (pin != ac.getPin())
        {
            throw new InvalidPinException("invalid pin") ;
        }
        if(amount<=0)
        {
            return "you entered invalid amount";
        }
        else
        {
            Double t=ac.getBalance();
            Double temp=t+amount;
            ac.setBalance(temp);
            accountHolderRepository.save(ac);
            Transaction tx = new Transaction(null,null, accountNumber,"DEPOSIT", amount, LocalDateTime.now());
            transactionRepository.save(tx);
            return "Balance after deposit "+ac.getBalance();
        }
    }

    @Transactional
    public String withdrawTransaction(String accountNumber, Double amount,int pin) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account number not exist") ;
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive") ;
        }
        if(pin != ac.getPin())
        {
            throw new InvalidPinException("invalid pin") ;
        }
        else
        {
            Double balance= ac.getBalance();
            if(balance<amount)
            {
                return "insufficient balance";
            }
            Transaction tx = new Transaction(null, null,accountNumber, "WITHDRAW", amount, LocalDateTime.now());
            balance-=amount;
            ac.setBalance(balance);
            accountHolderRepository.save(ac);
            transactionRepository.save(tx);
            return "collect your cash of rupees "+amount+" and your balance is rupees "+ac.getBalance();
        }
    }

    @Transactional
    public String amountTransactionThroughAccount(String accountNumber1, Double amount, int pin,String accountNumber2) {

        AccountHolder senderAccount=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber1);
        AccountHolder receiverAccount=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber2);

        if(senderAccount==null)
        {
            throw new AccountNotFoundException("your account number is invalid") ;
        }
        if(receiverAccount==null)
        {
            throw new AccountNotFoundException("can't able to find the receiver account ") ;
        }
        if(senderAccount.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("your account is inactive") ;
        }
        if(receiverAccount.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("receiver account is inactive") ;
        }

        if (pin != senderAccount.getPin())
        {
            throw new InvalidPinException("invalid pin") ;
        }
        else
        {
            Double temp= senderAccount.getBalance();
            if(temp<amount)
            {
                return "insufficient balance";
            }
            temp-=amount;
            senderAccount.setBalance(temp);
            accountHolderRepository.save(senderAccount);
            Transaction tx = new Transaction(null, accountNumber2, accountNumber1,"TRANSFER", amount, LocalDateTime.now());
            transactionRepository.save(tx);
            Double temp2=receiverAccount.getBalance();
            temp2+=amount;
            receiverAccount.setBalance(temp2);
            accountHolderRepository.save(receiverAccount);

            return "amount transfer is successfully done and your current balance is "+senderAccount.getBalance();

        }


    }

    public List<Transaction> transactionHistory(String accountNumber, int pin) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account number not exist");
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive") ;
        }
        if(pin != ac.getPin())
        {
            throw new InvalidPinException("invalid pin") ;
        }
        else
        {
            return transactionRepository.findByAccountNumber(accountNumber);

        }
    }
}
