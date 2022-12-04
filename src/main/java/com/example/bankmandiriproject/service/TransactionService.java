package com.example.bankmandiriproject.service;

import com.example.bankmandiriproject.model.Account;
import com.example.bankmandiriproject.model.Transaction;
import com.example.bankmandiriproject.repository.AccountRepository;
import com.example.bankmandiriproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    // create Transaction
    public Transaction createTransaction(Transaction transaction){
        Account accSender = accountRepository.findByAccountNumber(transaction.getAccountSender());
        Account accReceiver = accountRepository.findByAccountNumber(transaction.getAccountReceiver());

        if(accSender.getAccountBalance().compareTo(transaction.getAmount()) == 1){
            sendMoney(accSender.getAccountNumber(), accReceiver.getAccountNumber(), transaction.getAmount());
        }

        receiveMoney(accSender.getAccountNumber(), accReceiver.getAccountNumber(), transaction.getAmount());

        return transactionRepository.save(transaction);
    }

    public Transaction sendMoney(String accountSender, String accountReceiver, BigDecimal amount){
        Account accSender = accountRepository.findByAccountNumber(accountSender);

        BigDecimal balanceSender = accSender.getAccountBalance();
        accSender.setAccountBalance(balanceSender.subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(accSender);
        transaction.setAccountSender(accountSender);
        transaction.setAccountReceiver(accountReceiver);
        transaction.setAccActivityType("SEND");

        return transactionRepository.save(transaction);
    }

    public Transaction receiveMoney(String accountSender, String accountReceiver, BigDecimal amount){
        Account accReceiver= accountRepository.findByAccountNumber(accountReceiver);

        BigDecimal balanceReceiver = accReceiver.getAccountBalance();
        accReceiver.setAccountBalance(balanceReceiver.add(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(accReceiver);
        transaction.setAccountSender(accountSender);
        transaction.setAccountReceiver(accountReceiver);
        transaction.setAccActivityType("RECEIVE");

        return transactionRepository.save(transaction);
    }

    public void getTransactionsByAcc(String accountId){
        transactionRepository.findById(accountId);
    }

    public List<Transaction> findAllAccount(){
        return transactionRepository.findAll();
    }

}
