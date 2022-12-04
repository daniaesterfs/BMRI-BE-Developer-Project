package com.example.bankmandiriproject.controller;


import com.example.bankmandiriproject.dto.AccountSearchForm;
import com.example.bankmandiriproject.model.Account;
import com.example.bankmandiriproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.findAllAccount();
    }

    @GetMapping("/search")
    public List<Account> searchAccount(@RequestParam(defaultValue = "") String searchNumber,
                         @RequestParam(defaultValue = "") String searchName,
                         @RequestParam(defaultValue = "") String searchType) {
        AccountSearchForm accountSearchForm = new AccountSearchForm(searchNumber, searchName, searchType);
        return accountService.search(accountSearchForm);
    }

    @GetMapping("/{accountId}")
    public Account getAccountByAccountId(@PathVariable String accountId) {
        return accountService.findById(accountId);
    }

    @GetMapping("/{userId}/accounts")
    public List<Account> getAccountByUserId(@PathVariable String userId){
        return accountService.getByUserId(userId);
    }

    @PostMapping
    public Account createNewAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccountById(@PathVariable String accountId){
        accountService.deleteById(accountId);
    }


}
