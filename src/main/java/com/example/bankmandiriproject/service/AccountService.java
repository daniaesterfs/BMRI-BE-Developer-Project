package com.example.bankmandiriproject.service;

import com.example.bankmandiriproject.dto.AccountSearchForm;
import com.example.bankmandiriproject.model.Account;
import com.example.bankmandiriproject.model.User;
import com.example.bankmandiriproject.repository.AccountRepository;
import com.example.bankmandiriproject.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserService userService;

    public List<Account> findAllAccount(){
        return accountRepository.findAll();
    }

    public List<Account> search(AccountSearchForm accountSearchForm) {
        AccountSpecification accountSpecification = new AccountSpecification(accountSearchForm);
        List<Account> accounts = accountRepository.findAll(accountSpecification);
        return accounts;
    }

    public Account findById(String id){
        return accountRepository.findById(id).get();
    }

    public Account createAccount(Account account){
        String accNumber = generateAccountNumber();
        account.setAccountNumber(accNumber);
        User user = userService.findById(account.getUser().getUserId());
        account.setAccountHolderName(user.getUserFirstName());
        return accountRepository.save(account);
    }

    public List<Account> getByUserId(String userId) {
        User user = userService.findById(userId);
        return user.getAccounts();
    }

    public void deleteById(String accountId){
        findById(accountId);
        accountRepository.deleteById(accountId);
    }

    public String generateAccountNumber(){
        Random random = new Random();
        Integer accNumber = random.nextInt(1000000000 + 1 - 10000000) + 10000000;
        return accNumber.toString();
    }

}
