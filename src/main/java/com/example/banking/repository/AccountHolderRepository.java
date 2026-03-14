package com.example.banking.repository;

import com.example.banking.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AccountHolderRepository extends JpaRepository<AccountHolder,Long> {
    boolean existsAccountHolderByAccountNumber(String accountNumber);
    AccountHolder findAccountHolderByAccountNumber(String accountNumber);
}
