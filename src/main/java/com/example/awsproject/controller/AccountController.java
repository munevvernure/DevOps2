package com.example.awsproject.controller;

import com.example.awsproject.model.Account;
import com.example.awsproject.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        Iterable<Account> accountList = accountRepository.findAll();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        logger.info("Get account with id: " + id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        logger.info("Account created with id: " + account.getAccid());
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        if (id == null || id == 0 || account == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        existingAccount.setAccid(id);
        existingAccount.setBranch(account.getBranch());
        existingAccount.setBalance(account.getBalance());

        Account updatedAccount = accountRepository.save(existingAccount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        accountRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
