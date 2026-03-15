package com.example.banking.service;

import com.example.banking.exception.AccountInactiveException;
import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.exception.InvalidPinException;
import com.example.banking.model.AccountHolder;
import com.example.banking.model.DetailsUpdate;
import com.example.banking.model.ResetPin;
import com.example.banking.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public void addAccountHolder(AccountHolder accountHolder) {
        accountHolderRepository.save(accountHolder);
    }

    public List<AccountHolder> showAllHolders() {
        return accountHolderRepository.findAll();
    }

    public String getHolderDetailsByAccountNumberAndPin(String accountNumber, int pin) {
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
        else
        {

            return ac.toString();
        }
    }

    public String deleteAccount(String accountNumber, int pin) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account does not exist");
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is already inactive");
        }
        if(pin !=ac.getPin())
        {
            throw new InvalidPinException("invalid pin");
        }
        else
        {
            ac.setStatus("INACTIVE");
            accountHolderRepository.save(ac);
           return "account removed successfully";
        }
    }

    public String activateAccount(String accountNumber, int pin) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account does not exist");
        }
        if(ac.getStatus().equals("ACTIVE"))
        {
            throw new AccountInactiveException("account is already active");
        }
        if(ac.getPin() !=pin)
        {
            throw new InvalidPinException("invalid pin");
        }
        else
        {
            ac.setStatus("ACTIVE");
            accountHolderRepository.save(ac);
            return "your account is activated";
        }
    }

    public String updatePin(String accountNumber, ResetPin resetPin) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account does not exist");
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive");
        }
        if(resetPin.getOldPin() !=ac.getPin())
        {
            throw new InvalidPinException("invalid pin");
        }
        else
        {
            ac.setPin(resetPin.getNewPin());
            accountHolderRepository.save(ac);
            return "pin updated";
        }

    }

    public String updateDetails(String accountNumber, DetailsUpdate detailsUpdate) {
        AccountHolder ac=accountHolderRepository.findAccountHolderByAccountNumber(accountNumber);
        if(ac==null)
        {
            throw new AccountNotFoundException("account does not exist");
        }
        if(ac.getStatus().equals("INACTIVE"))
        {
            throw new AccountInactiveException("account is inactive");
        }
        if(detailsUpdate.getPin() !=ac.getPin())
        {
            throw new InvalidPinException("invalid pin");
        }
        else
        {
            if(detailsUpdate.getEmail() != null)
                ac.setEmail(detailsUpdate.getEmail());
            if(detailsUpdate.getPhone() != null)
                ac.setPhone(detailsUpdate.getPhone());
            accountHolderRepository.save(ac);
            return "details updated";
        }

    }
}
