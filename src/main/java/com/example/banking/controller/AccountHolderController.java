package com.example.banking.controller;

import com.example.banking.model.AccountHolder;
import com.example.banking.model.DetailsUpdate;
import com.example.banking.model.PinRequest;
import com.example.banking.model.ResetPin;
import com.example.banking.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holder")
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @PostMapping("/")
    public String addAccountHolder(@RequestBody AccountHolder accountHolder)
    {
        accountHolderService.addAccountHolder(accountHolder);
        return "added";
    }

    @GetMapping("/")
    public List<AccountHolder> showAllHolders()
    {
        return accountHolderService.showAllHolders();
    }

    @PostMapping("/{accountNumber}")
    public String getHolderDetailsByAccountNumberAndPin(@PathVariable String accountNumber, @RequestBody PinRequest pinRequest)
    {
        return accountHolderService.getHolderDetailsByAccountNumberAndPin(accountNumber,pinRequest.getPin());
    }
    @DeleteMapping("/delete/{accountNumber}")
    public String deleteAccount(@PathVariable String accountNumber,@RequestBody PinRequest pinRequest)
    {

        return accountHolderService.deleteAccount(accountNumber,pinRequest.getPin());
    }
    @PutMapping("/activate/{accountNumber}")
    public String activateAccount(@PathVariable String accountNumber,@RequestBody PinRequest pinRequest)
    {
        return accountHolderService.activateAccount(accountNumber,pinRequest.getPin());
    }
    @PutMapping("/changepin/{accountNumber}")
    public String updatePin(@PathVariable String accountNumber, @RequestBody ResetPin resetPin)
    {
        return accountHolderService.updatePin(accountNumber,resetPin);
    }
    @PutMapping("/updatedetails/{accountNumber}")
    public String updateDetails(@PathVariable String accountNumber, @RequestBody DetailsUpdate detailsUpdate)
    {
        return accountHolderService.updateDetails(accountNumber,detailsUpdate);
    }

}

