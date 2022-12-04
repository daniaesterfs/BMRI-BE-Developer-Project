package com.example.bankmandiriproject.repository;


import com.example.bankmandiriproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {
    @Query(
            value = "SELECT * FROM mst_account a WHERE a.account_number = :accountNumber",
            nativeQuery = true)
    Account findByAccountNumber(String accountNumber);
}
