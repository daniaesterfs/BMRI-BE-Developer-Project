package com.example.bankmandiriproject.controller;


import com.example.bankmandiriproject.model.Transaction;
import com.example.bankmandiriproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public Transaction createNewTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/{accountId}")
    public void getTransactionByAcc(@PathVariable String accountId){
        transactionService.getTransactionsByAcc(accountId);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAllAccount();
    }
}
